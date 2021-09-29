package com.sh.mobao.portal.web.controller.api;


import com.sh.mobao.common.util.MD5Util;
import com.sh.mobao.common.util.Sha256Utils;

import java.util.*;

public class CheckUtils<appSecret> {

    //appSecret 和 appId 一一对应
    public static String appSecret = "aaa";

    //根据Map生成签名
    public static String generatorSign(Map<String,Object> map){
        map.remove("sign");

        //排序，因为前段传过来的顺序可能是无序的
        Map<String,Object> sortMap = sortMapByKey(map);

        //转格式 name=张三&age=10,转成新的格式
        Set<Map.Entry<String,Object>> entrySet = sortMap.entrySet();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String,Object> entry:entrySet){
            stringBuilder.append(entry.getKey()+","+entry.getValue()).append("#");
        }
//        stringBuilder.subSequence(0,stringBuilder.length() - 1);
        //组装secret，在参数的后面添加secret
        stringBuilder.append("secret").append(appSecret);
        //生成签名

        return MD5Util.md5(stringBuilder.toString());

        //生成Sha256的签名
//        return Sha256Utils.getSHA256(stringBuilder.toString());
    }

    public static Map<String,Object> sortMapByKey(Map<String,Object> map){
        if (map == null){
            //判断是否为空
//            new Exception("参数为空");
            return null;
        }
        Map<String,Object> sortMap = new TreeMap<>(new MyMapComparator());
        sortMap.putAll(map);
        return sortMap;
    }

    public static class MyMapComparator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    public static void main(String[] args) {
        HashMap<String,Object> map = new HashMap<>();
//        map.put("ap",1);
//        map.put("z2",2);
//        map.put("h",3);
//
//        Map<String,Object> sortMap = sortMapByKey(map);
//        System.out.println(sortMap);

        map.put("appId",1);
        map.put("name",2);
        String sign = generatorSign(map);
//        a3337ee5d708e41ac38bd0d88561b95f
        System.out.println(sign);
    }


}
