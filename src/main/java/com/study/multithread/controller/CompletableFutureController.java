package com.study.multithread.controller;

import com.alibaba.fastjson.JSON;
import com.study.multithread.entity.SkuItemVo;
import com.study.multithread.service.impl.MultithreadCall;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/test")
public class CompletableFutureController {

    @Resource
    private MultithreadCall multithreadCall;

    @GetMapping(value = "/duo")
    public void test(){

        SkuItemVo item = multithreadCall.item(1L);

    }

}
