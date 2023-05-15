package com.study.multithread.service;

import com.study.multithread.entity.SkuInfoEntity;

public interface ISkuInfoService {

    SkuInfoEntity getSkuById(Long skuId);
}
