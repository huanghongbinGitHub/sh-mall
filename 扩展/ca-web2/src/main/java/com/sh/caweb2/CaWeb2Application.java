package com.sh.caweb2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CaWeb2Application {

    public static void main(String[] args) {
        SpringApplication.run(CaWeb2Application.class, args);
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello nigx";
    }

}
