package com.sh.mobao.portal.web.controller.studyCaptcha;


import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import com.sh.mobao.base.annotations.TokenCheck;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/happy-captcha")
public class HappyCaptchaController {

    @GetMapping("/gennerator")
    @TokenCheck(required = false)
    public void genneratorCode(HttpServletRequest request, HttpServletResponse response)  {

        HappyCaptcha.require(request,response)
                .style(CaptchaStyle.ANIM)
                .type(CaptchaType.ARITHMETIC_ZH)
                .build().finish();

    }


    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request){
        Boolean abool = HappyCaptcha.verification(request,verifyCode,true);
        if (abool){
            return "验证码校验通过";
        }
        return "验证码校验不通过";
    }
}
