package com.study.logaop.controller;

import com.study.logaop.annotate.OperationAnnotation;
import com.study.logaop.service.ISyslogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("abc/bcd")
public class SystemLogController {

    @Resource
    private ISyslogService syslogService;

    @OperationAnnotation(content = "查询列表日志记录", sysType = 1, optType = 1, action = "吃饭")
    @GetMapping("/list")
    public Map<String, Object> syslog(){
        return new HashMap<>();
    }

}
