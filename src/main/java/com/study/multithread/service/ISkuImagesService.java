package com.study.multithread.service;

import com.study.multithread.entity.SkuImagesEntity;

import java.util.List;

public interface ISkuImagesService {
    List<SkuImagesEntity> getImagesBySkuId(Long skuId);
}
