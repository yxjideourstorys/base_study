package com.study.multithread.service.impl;

import com.study.multithread.entity.SpuItemAttrGroupVo;
import com.study.multithread.service.IAttrGroupService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttrGroupServiceImpl implements IAttrGroupService {
    @Override
    public List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId) {
        SpuItemAttrGroupVo spuItemAttrGroup = new SpuItemAttrGroupVo();
        spuItemAttrGroup.setName("getAttrGroupWithAttrsBySpuId");

        List<SpuItemAttrGroupVo> list = new ArrayList<>();
        list.add(spuItemAttrGroup);

        return list;
    }
}
