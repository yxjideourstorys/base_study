package com.study.logaop.aspect;

import com.study.logaop.annotate.OperationAnnotation;
import com.study.logaop.mapper.SysLogMapper;
import com.study.logaop.vo.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志拦截切面
 */
@Aspect
@Component
public class SystemLogAspect {

    @Resource
    private SysLogMapper logMapper;

    /**
     * 请求地址
     */
    private String requestPath = null;
    /**
     * 开始时间
     */
    private long startTimeMillis = 0;
    /**
     * 结束时间
     */
    private long endTimeMillis = 0;
    /**
     * 操作人
     */
    private String user = null;
    /**
     * 请求
     */
    private HttpServletRequest request = null;

    /**
     * 注解Pointcut切入点
     * 定义出一个或一组方法，当执行这些方法时可产生通知
     * 指向你的切面类方法
     * 由于这里使用了自定义注解所以指向你的自定义注解
     */
    @Pointcut("@annotation(com.study.logaop.annotate.OperationAnnotation)")
    public void logPointCut() {
    }

    /**
     * @param joinPoint
     * @Description 前置通知  方法调用前触发   记录开始时间,从session中获取操作人
     */
    @Before(value = "logPointCut()")
    public void before(JoinPoint joinPoint) {
        startTimeMillis = System.currentTimeMillis();
    }

    /**
     * @param joinPoint
     * @return
     * @Description 获取入参方法参数
     */
    public Map<String, Object> getNameAndValue(JoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            if (paramValues[i] instanceof Integer || paramValues[i] instanceof String) {
                param.put(paramNames[i], paramValues[i]);
            }
        }
        return param;
    }

    /**
     * @param joinPoint
     * @Description 后置通知    方法调用后触发   记录结束时间 ,操作人 ,入参等
     */
    @After(value = "logPointCut()")
    public void after(JoinPoint joinPoint) {
        request = getHttpServletRequest();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] methods = targetClass.getMethods();
        String title;
        String action;
        Integer sysType;
        Integer opType;
        Class<?>[] clazzs;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                clazzs = method.getParameterTypes();
                if (clazzs != null && clazzs.length == arguments.length
                        && method.getAnnotation(OperationAnnotation.class) != null) {
                    request = getHttpServletRequest();
                    requestPath = request.getServletPath();
                    HttpSession session = request.getSession();
                    user = session.getAttribute("userName").toString();
                    title = method.getAnnotation(OperationAnnotation.class).content();
                    action = method.getAnnotation(OperationAnnotation.class).action();
                    sysType = method.getAnnotation(OperationAnnotation.class).sysType();
                    opType = method.getAnnotation(OperationAnnotation.class).optType();
                    endTimeMillis = System.currentTimeMillis();

                    SysLog log = new SysLog(user, requestPath,
                            (endTimeMillis - startTimeMillis) + "ms",
                            getNameAndValue(joinPoint).toString(), title, action, sysType, opType);
                    System.out.println("增加参数：" + log);
                    logMapper.insert(log);
//                    break;
                }
            }
        }
    }

    /**
     * @Description: 获取request
     */
    public HttpServletRequest getHttpServletRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }

}
