package com.study.multithread.service.impl;

import com.study.multithread.entity.*;
import com.study.multithread.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;

@Service
@Slf4j
public class MultithreadCall implements IMultithreadCall{

    @Resource
    private ISkuInfoService skuInfoService;

    @Resource
    private ISkuImagesService skuImagesService;

    @Resource
    private ISkuSaleAttrValueService skuSaleAttrValueService;

    @Resource
    private ISpuInfoDescService spuInfoDescService;

    @Resource
    private IAttrGroupService attrGroupService;

    public SkuItemVo item(Long skuId) {

        // 获取CPU核心数
        int corePoolSize = 2 * Runtime.getRuntime().availableProcessors() + 1;
        int maximumPoolSize = 2 * corePoolSize;

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadPoolExecutor.AbortPolicy());

        SkuItemVo skuItemVo = new SkuItemVo();

        /**
         * 3、4、5需要依赖1的运行结果，需要返回skuInfo后从中获取spuId和catalogId
         * 而2不需要依赖1的运行结果
         */

        //1、sku详细信息 sku_info
        CompletableFuture<SkuInfoEntity> infoFuture = CompletableFuture.supplyAsync(() -> {
            SkuInfoEntity skuInfo = skuInfoService.getSkuById(skuId);
            skuItemVo.setInfo(skuInfo);
            return skuInfo;
        }, threadPool);

        //2、sku 图片信息 sku_img  2不需要等待上边1的执行结果
        CompletableFuture<Void> imageFuture = CompletableFuture.runAsync(() -> {
            List<SkuImagesEntity> images = skuImagesService.getImagesBySkuId(skuId);
            skuItemVo.setImages(images);
        }, threadPool);

        //下边的3、4、5都需要上边1的执行结果
        //所以下边的3、4、5都是基于上边1的执行结果 infoFuture 开始的
        //都是以infoFuture.thenAcceptAsync(skuInfo -> {})开始的
        CompletableFuture<Void> saleAttrFuture = infoFuture.thenAcceptAsync(skuInfo -> {
            //3、spu 销售属性组合
            List<SkuItemSaleAttrVo> saleAttr = skuSaleAttrValueService.getSaleAttrBySpuId(skuInfo.getSpuId());
            skuItemVo.setSaleAttr(saleAttr);
        }, threadPool);

        CompletableFuture<Void> descFuture = infoFuture.thenAcceptAsync(skuInfo -> {
            //4、spu 的介绍
            SpuInfoDescEntity spuInfoDesc = spuInfoDescService.getInfoDescBySpuId(skuInfo.getSpuId());
            skuItemVo.setDesc(spuInfoDesc);
        }, threadPool);

        CompletableFuture<Void> attrGroupFuture = infoFuture.thenAcceptAsync(skuInfo -> {
            //5、spu 规格参数信息
            List<SpuItemAttrGroupVo> groupAttrs = attrGroupService.getAttrGroupWithAttrsBySpuId(skuInfo.getSpuId(), skuInfo.getCatalogId());
            skuItemVo.setGroupAttrs(groupAttrs);
        }, threadPool);

        //等待所有任务完成
        try {
            CompletableFuture.allOf(saleAttrFuture, descFuture, attrGroupFuture, imageFuture).get();
        } catch (InterruptedException e) {
            log.error("查询商品详情异步编排错误: ");
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }finally {
            //线程池用完，程序结束，关闭线程池
            threadPool.shutdown();  //（为确保关闭，将关闭方法放入到finally中）
        }

        return skuItemVo;
    }
}
