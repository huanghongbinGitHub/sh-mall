package com.sh.mobao.portal.web.util;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

public class HttpParamUtils {
    
    public static SortedMap<String,String> getAllParams(HttpServletRequest request){
        //获取Url上的参数
        Map<String, String> urlParams = getUrlParams(request);

        //获取body上的参数


        return null;
    }

    /**
     * 获取url中的参数
     * @param request
     * @return
     */
    public static Map<String,String> getUrlParams(HttpServletRequest request){
        String decode = "";
        try {
            decode = URLDecoder.decode(request.getQueryString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String,String> result = new HashMap<>();
        String[] split = decode.split("&");
        for (String s : split){
            int i = s.indexOf("=");
            result.put(s.substring(0,i),s.substring(i+1));
        }
        System.out.println("url的参数 "+ result);
        return result;
    }

}