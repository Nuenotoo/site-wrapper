package com.dean.boot.response;

import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author: luoxy
 * @Date: 2019/5/27
 * @Description: 统一返回格式
 * 自定义HttpMessageConverter可能会导致返回不是期望的结果
 */
@ControllerAdvice
public class CommonJsonWrapper implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !DefaultResponse.class.isAssignableFrom(returnType.getParameterType()) &&
                !ResponseEntity.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body == null){
            return new Object();
        }
        if(AbstractGenericHttpMessageConverter.class.isAssignableFrom(selectedConverterType)){
            return DefaultResponse.builder().data(body).build();
        } else {
            return JSON.toJSONString(DefaultResponse.builder().data(body).build());
        }

    }
}
