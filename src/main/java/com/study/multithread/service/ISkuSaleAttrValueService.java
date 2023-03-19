package com.study.multithread.service;

import com.study.multithread.entity.SkuItemSaleAttrVo;

import java.util.List;

public interface ISkuSaleAttrValueService {
    List<SkuItemSaleAttrVo> getSaleAttrBySpuId(Long spuId);
}
