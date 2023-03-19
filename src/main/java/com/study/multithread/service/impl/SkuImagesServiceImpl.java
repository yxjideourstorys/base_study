package com.study.multithread.service.impl;

import com.study.multithread.entity.SkuImagesEntity;
import com.study.multithread.service.ISkuImagesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkuImagesServiceImpl implements ISkuImagesService {
    @Override
    public List<SkuImagesEntity> getImagesBySkuId(Long skuId) {
        SkuImagesEntity skuImages = new SkuImagesEntity();
        skuImages.setName("getSaleAttrBySpuId");

        List<SkuImagesEntity> list = new ArrayList<>();
        list.add(skuImages);

        return list;
    }
}
