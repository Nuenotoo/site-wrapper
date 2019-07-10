package com.dean.boot.error;

import com.dean.boot.exception.BizException;
import com.dean.boot.response.ApiResponseCode;
import com.dean.boot.response.DefaultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: luoxy
 * @Date: 2019/5/27
 * @Description: controller出现异常时响应JSON串
 */
@ControllerAdvice
public class CommonExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);

    /**
     * catch所有controller异常，响应json
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public DefaultResponse handleException(Exception e){
        log.error("controller advice,", e);
        return DefaultResponse.builder()
                .glue(ApiResponseCode.UNKNOWN)
                .data(e.getMessage())
                .build();
    }

    /**
     * valid注解校验异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public DefaultResponse handleValidException(BindException e){
        log.error("controller advice,", e);
        String names = "";
        for(FieldError fieldError : e.getFieldErrors()){
            if(!StringUtils.isEmpty(names)){
                names += ", ";
            }
            names += fieldError.getField();
        }
        return DefaultResponse.builder()
                .code(ApiResponseCode.UNKNOWN.getCode())
                .msg("参数错误")
                .data(names)
                .build();
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public DefaultResponse handleBusException(BizException e){
        Throwable root = e;
        while (null != root.getCause()) {
            root = root.getCause();
        }
        log.error("controller advice,", root);
        return DefaultResponse.builder()
                .code(e.getCode())
                .msg(e.getMsg())
                .build();
    }
}
