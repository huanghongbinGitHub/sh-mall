package com.sh.mobao.portal.web.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api-safe")
public class ApiSafeController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello api safe";
    }

    @RequestMapping("get-test")
    public String getTest(String appId,String name,String sign){

        HashMap<String , Object> map = new HashMap<>();
        map.put("appId",appId);
        map.put("name",name);

        String gSign = CheckUtils.generatorSign(map);
        if (gSign.equals(sign)){
            return "校验通过";
        }else {
            return "校验不通过";
        }
    }

}
