package com.study.base.avoid_resubmit.test;

import com.study.base.avoid_resubmit.annotate.PreventDuplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping("/sayNoDuplication")
    @PreventDuplication(expireSeconds = 8)
    public String sayNoDuplication(@RequestParam("requestNum") String requestNum){
        log.info("sayNoDuplication requestNum：{}", requestNum);
        return "sayNoDuplication".concat(requestNum);
    }

}
