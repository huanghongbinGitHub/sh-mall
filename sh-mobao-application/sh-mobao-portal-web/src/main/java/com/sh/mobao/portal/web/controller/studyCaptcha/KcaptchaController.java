package com.sh.mobao.portal.web.controller.studyCaptcha;


import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.sh.mobao.base.annotations.TokenCheck;
import com.sh.mobao.portal.web.custom.MyGoogleKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/kcaptcha")
public class KcaptchaController {

    @Autowired
    private Kaptcha kaptcha;

    @GetMapping("/gennerator")
    @TokenCheck(required = false)
    public void genneratorCode(HttpServletRequest request, HttpServletResponse response)  {
        kaptcha.render();
    }


    /**
     * 为什么在校验的时候设置过期时间：
     *  中间件的思维，用验证码生成的时间，减去
     * @param verifyCode
     * @param request
     * @return
     */
    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request){
        Boolean abool = false;
        try {
             abool = kaptcha.validate(verifyCode, 60);
        }catch (KaptchaNotFoundException exception){
            return "验证码校验不通过";
        }
        if (abool){
            return "验证码校验通过";
        }
        return "验证码校验不通过";
    }


    @Autowired
    private MyGoogleKaptcha myGoogleKaptcha;

    @GetMapping("/gennerator-my")
    @TokenCheck(required = false)
    public void genneratorCodeMy(HttpServletRequest request, HttpServletResponse response)  {
        myGoogleKaptcha.render();
    }


    @GetMapping("/verify-my")
    @TokenCheck(required = false)
    public String verifyMy(String verifyCode, HttpServletRequest request){
        Boolean abool = false;
        try {
            abool = myGoogleKaptcha.validate(verifyCode, 60);
        }catch (KaptchaNotFoundException exception){
            return "验证码校验不通过";
        }
        if (abool){
            return "验证码校验通过";
        }
        return "验证码校验不通过";
    }

}
