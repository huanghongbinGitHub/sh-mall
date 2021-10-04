package com.sh.mobao.portal.web.controller.api;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sh.mobao.portal.web.controller.api.posttest.SignDTO;
import com.sh.mobao.portal.web.custom.MyGoogleKaptcha;
import com.sh.mobao.portal.web.filter.SignAuthFilter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api-safe")
public class ApiSafeController {


    @RequestMapping("/hello")
    public String hello(){
        return "hello api safe";
    }

    //测试get 方法 参数防篡改
    @RequestMapping("get-test")
    public String getTest(String appId, String name, String sign, long timeStamp, HttpServletRequest request){

        //为了排序，参数写死
        HashMap<String , String> map = new HashMap<>();
//        map.put("appId",appId);
//        map.put("name",name);
//        map.put("timestamp",timeStamp);
        //获取参数
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            //获取name
            String element = parameterNames.nextElement();
            //获取值
            String requestParameter = request.getParameter(element);
            map.put(element,requestParameter);
        }


        //让接口在有效期内访问
//        long time = System.currentTimeMillis() - timeStamp;
//        if (time > 1000 * 30){
//            return "接口过期了";
//        }

        String gSign = CheckUtils.generatorSign(map);
        if (gSign.equals(sign)){
            return "校验通过";
        }else {
            return "校验不通过";
        }
    }

    @PostMapping("/post-test")
    public String postTest(@RequestBody SignDTO signDTO){
        System.out.println("进入controller方法");

//        JSONObject jsonObject = JSONUtil.parseObj(signDTO);
//        //参数赚map
//        Map<String, Object> stringObjectMap = Convert.toMap(String.class, Object.class, signDTO);
//
//        //排序
//        Map<String, Object> stringObjectMap1 = CheckUtils.sortMapByKey(stringObjectMap);
//
//        System.out.println(stringObjectMap1);
//        //map生成签名
//        String sign = CheckUtils.generatorSign(stringObjectMap1);
//
//        //判断签名
//        if (signDTO.getSign().equals(sign)){n 
//            return "校验通过";
//        }else {
//            return "校验不通过";
//        }
        return "success";
    }

}
