package com.study.multithread.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkuInfoEntity implements Serializable {

    private Long id;

    private Long spuId;

    private Long catalogId;
}
