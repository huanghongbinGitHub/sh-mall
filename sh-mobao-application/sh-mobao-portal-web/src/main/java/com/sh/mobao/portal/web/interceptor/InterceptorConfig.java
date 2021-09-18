package com.sh.mobao.portal.web.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/user-member/login")
//                .excludePathPatterns("/jcaptcha/**")
//                .excludePathPatterns("/code/**");
    }

    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }
}
