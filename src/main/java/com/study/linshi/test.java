package com.study.linshi;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("abc/cheng")
public class test {

    /**
     * 测试玩登录
     */
    @RequestMapping("/test/login")
    public JSONObject login(){

        JSONObject result = new JSONObject();
        result.put("token", "sdafdasfdsafas");

        return result;
    }


    /**
     * 测试玩登录
     */
    @RequestMapping("/test/out")
    public JSONObject out(@RequestParam Map<String, Object> params){
        JSONObject result = new JSONObject();
        Object token = params.get("token");
        if (null == token){
            result.put("msg", "token没获取到");
            result.put("token", "");
            return result;
        }

        result.put("token", token);
        result.put("msg", "请求成功");

        return result;
    }
}
