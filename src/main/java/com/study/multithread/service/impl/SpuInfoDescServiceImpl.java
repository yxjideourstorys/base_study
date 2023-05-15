package com.study.multithread.service.impl;

import com.study.multithread.entity.SpuInfoDescEntity;
import com.study.multithread.service.ISpuInfoDescService;
import org.springframework.stereotype.Service;

@Service
public class SpuInfoDescServiceImpl implements ISpuInfoDescService {
    @Override
    public SpuInfoDescEntity getInfoDescBySpuId(Long spuId) {

        SpuInfoDescEntity spuInfoDesc = new SpuInfoDescEntity();
        spuInfoDesc.setName("spuInfoDesc");

        return spuInfoDesc;
    }
}
