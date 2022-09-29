package com.suqianbft.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//统一异常处理类 该类拦截所有的错误
@ControllerAdvice  //拦截含有Controller注解的类
public class ControllerExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());  //获取logger对象
    @ExceptionHandler(Exception.class)    //加上该注解才能异常处理 Exception.class级别的
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        //记录异常信息
        logger.error("Request URL : {},Exception :{}",request.getRequestURL(),e);
        //用注解工具 如果这个异常类上有响应状态 就放出这个异常
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class )!=null){
            throw e;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }
}
