package com.sh.mobao.portal.web.controller;

import com.sh.mobao.base.annotations.TokenCheck;
import com.sh.mobao.portal.web.code.ImageCode;
import com.sh.mobao.portal.web.util.JCaptchaUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

//import


@RestController
@RequestMapping("/jcaptcha")
public class JcaptchaController {

    String attrName = "verifyCode";

    /**
     * 生成验证码
     * 使用jcaptcha框架实现半自动化验证码图片
     */
    @GetMapping("/gennerator")
    @TokenCheck(required = false)
    public void genneratorCode(HttpServletRequest request,HttpServletResponse response)  {
        System.out.println("进去请求验证码");
        String id = request.getSession().getId();
        BufferedImage bufferedImage = JCaptchaUtil.getService().getImageChallengeForID(id);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        JPEG JPEGCodec.createJPEGEncoder(out);
//        JPEGCodec
//        JPEGImageEncoder jpegImageEncoder = new


        response.setHeader("Cache-Control","no-store");
        response.setContentType("image/jpeg");

        try {
            ImageIO.write(bufferedImage, "jpeg", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request){
        String s = request.getSession().getAttribute(this.attrName).toString();
        if (verifyCode.equals(s)){
            return "验证码校验通过";
        }
        return "验证码校验不通过";
    }

}
