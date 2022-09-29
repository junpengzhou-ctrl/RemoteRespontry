package com.suqianbft.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component //这切面 两个注解必须存在
public class LogApect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //切面方法  相当于拦截  拦截当前目录下的所有的类，拦截web下面的所有类所有所有方法
    @Pointcut("execution(* com.suqianbft.web.*.*(..))")
    public void log(){

    }
   //前置通知
    @Before("log()")   //在切面之前 获取请求对象
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() +"." +joinPoint.getSignature().getName();
        Object[]args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request:{}",requestLog);
    }
   //无论如何都会通知，通常放在finally 里面
    @After("log()")  //在切面之后
    public void doAfter(){
//        logger.info("-----doAfter--------");
    }
    //后置通知
    @AfterReturning(returning = "result",pointcut = "log()")  //在切面之后返回结果
    public void doAfterReturn(Object result){
        logger.info("Result:{}"+result);
    }

    // 注意：：：@Before 一定先执行 @AfterReturning 先执行于 @After


    private class RequestLog{
        private String url;  //请求的url
        private String ip;  //请求的ip地址
        private String classMethod; //请求的类方法
        private Object[] args;   //请求参数  封装成对象数组

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
