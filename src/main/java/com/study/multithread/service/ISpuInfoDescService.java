package com.study.multithread.service;

import com.study.multithread.entity.SpuInfoDescEntity;

public interface ISpuInfoDescService {
    SpuInfoDescEntity getInfoDescBySpuId(Long spuId);
}
