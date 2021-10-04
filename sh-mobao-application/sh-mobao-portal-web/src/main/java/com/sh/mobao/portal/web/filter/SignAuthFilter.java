package com.sh.mobao.portal.web.filter;

import cn.hutool.json.JSONObject;
import com.sh.mobao.portal.web.controller.api.CheckUtils;
import com.sh.mobao.portal.web.util.BodyReaderHttpServletRequestWrapper;
import com.sh.mobao.portal.web.util.HttpParamUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.SortedMap;

@Component
public class SignAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 签名的验证
//		HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletRequest request = new BodyReaderHttpServletRequestWrapper((HttpServletRequest)servletRequest);

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取参数.统一get和post，不管url，还是 body
        SortedMap<String, String> allParams = HttpParamUtils.getAllParams(request);

        // 校验签名
        boolean b = CheckUtils.checkSign(allParams);
        System.out.println("校验签名结果："+b);
        if (b){
            filterChain.doFilter(request,response);
        }else {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();

            JSONObject param = new JSONObject();
            param.put("code",-1);
            param.put("message", "签名错了");

            writer.append(param.toJSONString(0));

        }

        System.out.println("filter生效了");
    }

    @Override
    public void destroy() {
        System.out.println("filter销毁");
    }
}
