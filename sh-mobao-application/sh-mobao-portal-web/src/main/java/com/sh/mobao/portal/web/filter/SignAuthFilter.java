package com.sh.mobao.portal.web.filter;

import com.sh.mobao.portal.web.util.HttpParamUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SignAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //签名的验证
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取参数,从同一get和post，不管是url还是body，统一获取参数

        HttpParamUtils.getAllParams(request);


        //校验签名

        filterChain.doFilter(request,response);

        System.out.println("filter生效了");
    }

    @Override
    public void destroy() {
        System.out.println("filter销毁");
    }
}
