package com.dean.boot.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: luoxy
 * @Date: 2019/5/25
 * @Description:
 */
@Setter
@Getter
public class DefaultResponse<T> {
    private int code = 0;
    private String msg = "ok";
    private T data;

    public DefaultResponse(){}
    public DefaultResponse(ResponseBuilder<T> builder){
        this.code = builder.code;
        this.msg = builder.msg;
        this.data = builder.data;
    }

    public static ResponseBuilder builder(){
        return  new ResponseBuilder();
    }

    public static class ResponseBuilder<T>{
        private int code = ApiResponseCode.SUCCESS.getCode();
        private String msg = ApiResponseCode.SUCCESS.getMessage();
        private T data;

        public ResponseBuilder code(int code){
            this.code = code;
            return this;
        }

        public ResponseBuilder msg(String msg){
            this.msg = msg;
            return this;
        }

        public ResponseBuilder data(T data){
            this.data = data;
            return this;
        }

        public ResponseBuilder glue(ApiResponseCode responseCode){
            this.code = responseCode.getCode();
            this.msg = responseCode.getMessage();
            return this;
        }

        public DefaultResponse build(){
            return new DefaultResponse(this);
        }
    }
}
