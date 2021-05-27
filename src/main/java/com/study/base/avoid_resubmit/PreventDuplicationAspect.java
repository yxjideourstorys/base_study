package com.study.base.avoid_resubmit;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONObject;
import com.study.base.avoid_resubmit.annotate.PreventDuplication;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 *  方案：
 *      1、基于JAVA 注解 + AOP 切面方式实现防止重复提交，一般需要自定义JAVA注解，采用AOP切面解析注解。
 *      2、实现接口首次请求提交时，将接口请求标记（由接口签名、请求token、请求客户端ip等组成）存储至redis，并设置超时时间T （T时间之后 redis 清除接口请求标记）。
 *      3、接口每次请求都先检查redis中接口标记，若存在接口请求标记，则判定为接口重复提交，进行拦截返回处理。
 */
@Aspect
@Component
public class PreventDuplicationAspect {

    @Resource
    private RedisTemplate redisTemplate;

    @Pointcut("@annotation(com.study.base.avoid_resubmit.annotate.PreventDuplication)")
    public void preventDuplication(){}

    @Around(value = "preventDuplication()")
    public Object before(ProceedingJoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Assert.notNull(request, "request cannot be null.");

        // 获取执行方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 获取防重复提交注解
        PreventDuplication annotation = method.getAnnotation(PreventDuplication.class);

        // 获取token 以及方法标记，生成redisKey 和 redisValue
        String token = request.getHeader(IdempotentConstant.TOKEN);
        String redisKey = IdempotentConstant.PREVENT_DUPLICATION_PREFIX.concat(token).concat(getMethodSign(method, joinPoint.getArgs()));
        String redisValue = redisKey.concat(annotation.value()).concat("submit duplication");

        if (!redisTemplate.hasKey(redisKey)){

            // 设置过期时间
            redisTemplate.opsForValue().set(redisKey, redisValue, annotation.expireSeconds(), TimeUnit.SECONDS);

            try {
                // 正常执行方法返回
                // ProceedingJoinPoint 类型参数可以决定是否执行目标方法，且环绕通知必须要有返回值，返回值即为目标方法返回值。
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                // 确保方法执行异常实时释放限时标记（异常后置通知）
                redisTemplate.delete(redisKey);
                throw new RuntimeException(throwable);
            }

        } else {
            throw new RuntimeException("请勿重复提交");
        }
    }

    /**
     * 生成方法标记：采用数字签名算法SHA1对方法签名字符串加签
     *
     * @param method
     * @param args
     * @return
     */
    private String getMethodSign(Method method, Object... args) {
        StringBuilder sb = new StringBuilder(method.toString());
        for (Object arg : args) {
            sb.append(toString(arg));
        }
        return DigestUtils.sha1DigestAsHex(sb.toString());
    }

    private String toString(Object arg) {
        if (Objects.isNull(arg)) {
            return "null";
        }
        if (arg instanceof Number) {
            return arg.toString();
        }
        return JSONObject.toJSONString(arg);
    }
}
