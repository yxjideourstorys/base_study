package com.study.multithread.service;

import com.study.multithread.entity.SpuItemAttrGroupVo;

import java.util.List;

public interface IAttrGroupService {
    List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId);
}
