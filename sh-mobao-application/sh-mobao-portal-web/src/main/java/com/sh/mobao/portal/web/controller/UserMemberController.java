package com.sh.mobao.portal.web.controller;

import com.sh.mobao.base.annotations.TokenCheck;
import com.sh.mobao.base.result.ResultWrapper;
import com.sh.mobao.common.util.JwtUtil;
import com.sh.mobao.ums.entity.UmsMember;
import com.sh.mobao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.sh.mobao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.sh.mobao.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user-member")
public class UserMemberController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }

    @Autowired
    UmsMemberService  umsMemberService;

    @PostMapping("/register")
    public ResultWrapper register(@RequestBody @Valid UmsMemberRegisterParamDTO umsMemberRegisterParamDTO){

        return umsMemberService.register(umsMemberRegisterParamDTO);
    }

    @PostMapping("/login")
    public ResultWrapper login(@RequestBody @Valid UmsMemberLoginParamDTO umsMemberLoginParamDTO){
        System.out.println(umsMemberLoginParamDTO);
        return umsMemberService.login(umsMemberLoginParamDTO);
    }

    @PostMapping("/update-userinfo")
    public ResultWrapper updateUser(@RequestBody UmsMemberRegisterParamDTO umsMemberRegisterParamDTO){
        return umsMemberService.updateUser(umsMemberRegisterParamDTO);
    }

    @PostMapping("/udateById")
    @TokenCheck
    public ResultWrapper updateById(@RequestBody UmsMember umsMember){
        boolean bool = umsMemberService.updateById(umsMember);
        return ResultWrapper.getSuccessBuilder().data(umsMember).msg("更新成功").build();
    }

    @GetMapping("/test-verify")
    public String verify(String token){
        String s = JwtUtil.parseToken(token);
        return s;
    }

}
