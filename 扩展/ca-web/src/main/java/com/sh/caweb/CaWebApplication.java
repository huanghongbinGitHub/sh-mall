package com.sh.caweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CaWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaWebApplication.class, args);
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello https";
    }

}
