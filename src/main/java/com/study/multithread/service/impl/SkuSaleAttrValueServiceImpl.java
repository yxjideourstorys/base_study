package com.study.multithread.service.impl;

import com.study.multithread.entity.SkuItemSaleAttrVo;
import com.study.multithread.service.ISkuSaleAttrValueService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkuSaleAttrValueServiceImpl implements ISkuSaleAttrValueService {
    @Override
    public List<SkuItemSaleAttrVo> getSaleAttrBySpuId(Long spuId) {
        SkuItemSaleAttrVo skuItemSaleAttr = new SkuItemSaleAttrVo();
        skuItemSaleAttr.setName("getSaleAttrBySpuId");

        List<SkuItemSaleAttrVo> list = new ArrayList<>();
        list.add(skuItemSaleAttr);

        return list;
    }
}
