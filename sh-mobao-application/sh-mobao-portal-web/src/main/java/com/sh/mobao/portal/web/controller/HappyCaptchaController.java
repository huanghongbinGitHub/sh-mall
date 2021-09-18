package com.sh.mobao.portal.web.controller;


import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import com.sh.mobao.base.annotations.TokenCheck;
import com.sh.mobao.portal.web.util.JCaptchaUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
