package com.sh.mobao.portal.web.md5;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

public class EncoderOrDecoderTest {

    @Test
    public void md5(){
        String sourceString = "123456";
        String s = DigestUtils.md5DigestAsHex(sourceString.getBytes());
        System.out.println("第一次加密"+s);
        s = DigestUtils.md5DigestAsHex(sourceString.getBytes());
        System.out.println("第二次加密"+s);
    }

    @Test
    public void bcrypt(){
        String sourceString = "123456";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(sourceString);
        System.out.println("第一次加密的值"+encode);
        boolean b = bCryptPasswordEncoder.matches(sourceString,encode);
        System.out.println("第一次验证"+b);
        encode = bCryptPasswordEncoder.encode(sourceString);

        System.out.println("第二次加密的值"+encode);
        b = bCryptPasswordEncoder.matches(sourceString,encode);
        System.out.println("第二次验证"+b);

    }

}
