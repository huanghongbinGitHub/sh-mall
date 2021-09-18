package com.sh.mobao.portal.web.advice;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import com.sh.mobao.base.TokenException;
import com.sh.mobao.base.result.ResultWrapper;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;

@RestControllerAdvice //用这个注解也是可以的

//@ControllerAdvice
//@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultWrapper customException(){
        return ResultWrapper.builder().code(301).msg("统一异常").build();
    }

    /**
     * token异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(TokenException.class)
    public ResultWrapper loginException(Exception e){
        return ResultWrapper.getFailureBuilder().msg(e.getMessage()).build();
    }

    /**
     * 捕获到验证码的异常后统一处理
     * @param kaptchaException
     * @return
     */
    @ExceptionHandler(KaptchaException.class)
    public ResultWrapper kaptchaExceptionHandler(KaptchaException kaptchaException){
        String msg = null;
        if (kaptchaException instanceof KaptchaIncorrectException) {
            msg = "验证码不正确";
        } else if (kaptchaException instanceof KaptchaNotFoundException) {
            msg = "验证码未找到";
        } else if (kaptchaException instanceof KaptchaTimeoutException) {
            msg = "验证码过期";
        } else {
            msg = "验证码渲染失败";
        }
        return ResultWrapper.getFailureBuilder().msg(msg).build();
    }
}
