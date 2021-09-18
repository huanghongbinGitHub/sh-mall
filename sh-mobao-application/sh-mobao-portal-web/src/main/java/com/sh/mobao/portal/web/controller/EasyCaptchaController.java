package com.sh.mobao.portal.web.controller;


import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import com.sh.mobao.base.annotations.TokenCheck;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/easy-captcha")
public class EasyCaptchaController {

    @GetMapping("/gennerator")
    @TokenCheck(required = false)
    public void genneratorCode(HttpServletRequest request, HttpServletResponse response)  {
        try {
            //基础的验证码
            //CaptchaUtil.out(request,response);

            //算术类型
            /*
            ArithmeticCaptcha arithmeticCaptcha = new ArithmeticCaptcha(200,50);
            String value = arithmeticCaptcha.text(); //结果
            //3个数的运算
            arithmeticCaptcha.setLen(3);
            CaptchaUtil.out(arithmeticCaptcha,request,response);
             */

            //中文的验证码
            ChineseCaptcha chineseCaptcha = new ChineseCaptcha(150,150);
            CaptchaUtil.out(chineseCaptcha,request,response);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request){
        Boolean abool = CaptchaUtil.ver(verifyCode,request);
        if (abool){
            return "验证码校验通过";
        }
        return "验证码校验不通过";
    }


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/gennerator-redis")
    @TokenCheck(required = false)
    public void genneratorCodeRedis(HttpServletRequest request, HttpServletResponse response){

            SpecCaptcha specCaptcha = new SpecCaptcha(100,50);
            String text = specCaptcha.text();
            System.out.println(text);

            String uuid = UUID.randomUUID().toString();

            String sessionId = request.getSession().getId();

            stringRedisTemplate.opsForValue().set(sessionId,text);
            try {
                CaptchaUtil.out(specCaptcha,request,response);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

    }

    @GetMapping("/verify-redis")
    @TokenCheck(required = false)
    public String verifyRedis(String verifyCode, HttpServletRequest request){

        String sessionId = request.getSession().getId();

        String c = stringRedisTemplate.opsForValue().get(sessionId);

        if (verifyCode.equals(c)){
            HappyCaptcha.remove(request);
            return "验证码校验通过";
        }
        return "验证码校验不通过";
    }


    @GetMapping("/gennerator-uuid")
    @TokenCheck(required = false)
    public Map<String, String> genneratorCodeRedisUUID(HttpServletRequest request, HttpServletResponse response){

        SpecCaptcha specCaptcha = new SpecCaptcha(100,50);
        String text = specCaptcha.text();
        System.out.println(text);

        String uuid = UUID.randomUUID().toString();

        String sessionId = request.getSession().getId();

//        stringRedisTemplate.opsForValue().set(sessionId,text);
        stringRedisTemplate.opsForValue().set(uuid,text);

        String s1 = specCaptcha.toBase64();
        System.out.println("base64:"+s1);
        Map<String,String> map = new HashMap<>();
        map.put("uuid",uuid);
        map.put("base64",s1);

        return map;

    }

    @GetMapping("/verify-uuid")
    @TokenCheck(required = false)
    public String verifyRedisUUID(String verifyCode, HttpServletRequest request){

        String sessionId = request.getSession().getId();

        String c = stringRedisTemplate.opsForValue().get(sessionId);

        if (verifyCode.equals(c)){
            HappyCaptcha.remove(request);
            return "验证码校验通过";
        }
        return "验证码校验不通过";
    }



}
