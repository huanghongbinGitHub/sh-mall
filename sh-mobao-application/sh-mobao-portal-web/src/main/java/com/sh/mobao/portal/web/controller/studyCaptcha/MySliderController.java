package com.sh.mobao.portal.web.controller.studyCaptcha;


import com.sh.mobao.base.annotations.TokenCheck;
import com.sh.mobao.portal.web.util.SliderUtil;
import com.sh.mobao.portal.web.util.VerificationVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/my-slider")
public class MySliderController {

    @GetMapping("/gennerator")
    @TokenCheck(required = false)
    public VerificationVO genneratorCode(HttpServletRequest request, HttpServletResponse response)  {
        return SliderUtil.cut();
    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request){


        return "";
    }



}
