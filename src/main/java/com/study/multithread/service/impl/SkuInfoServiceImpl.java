package com.study.multithread.service.impl;

import com.study.multithread.entity.SkuInfoEntity;
import com.study.multithread.service.ISkuInfoService;
import org.springframework.stereotype.Service;

@Service
public class SkuInfoServiceImpl implements ISkuInfoService {
    @Override
    public SkuInfoEntity getSkuById(Long skuId) {
        SkuInfoEntity skuInfo = new SkuInfoEntity();
        skuInfo.setId(1L);
        skuInfo.setCatalogId(2L);
        skuInfo.setSpuId(3L);
        return skuInfo;
    }
}
