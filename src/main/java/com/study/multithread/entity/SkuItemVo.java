package com.study.multithread.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SkuItemVo implements Serializable {

    private SkuInfoEntity info;

    private List<SkuImagesEntity> images;

    private List<SkuItemSaleAttrVo> saleAttr;

    private SpuInfoDescEntity desc;

    private List<SpuItemAttrGroupVo> groupAttrs;
}
