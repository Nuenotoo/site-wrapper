package com.dean.boot.error;

import com.dean.boot.response.DefaultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: luoxy
 * @Date: 2019/5/25
 * @Description: 接口调用出现异常时响应JSON串
 * 请求还未进入controller也会被拦截，比如404
 */
@Controller
@Slf4j
public class BasicResponseErrorController implements ErrorController {
    @Value("${error.path:/error}")
    private String errorPath;

    private final ErrorAttributes errorAttributes;

    @Autowired
    public BasicResponseErrorController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return this.errorPath;
    }

    @RequestMapping(value = "${error.path:/error}")
    @ResponseBody
    public ResponseEntity error(HttpServletRequest request) {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        Throwable exception = this.errorAttributes.getError(servletWebRequest);
        HttpStatus status = getStatus(request);
        if(exception != null){
            log.warn("error occur, status is {}.", status, exception);
        } else {
            log.warn("error occur, http status is {}.", status);
        }

        DefaultResponse response = DefaultResponse.builder()
               .code(status.value()).msg(status.getReasonPhrase())
                .build();
        return new ResponseEntity<>(response, status);

    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            }
            catch (Exception ex) {
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
