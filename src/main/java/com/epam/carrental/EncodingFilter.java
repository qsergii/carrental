package com.epam.carrental;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

//@WebFilter(
//        urlPatterns = "/*",
//        initParams = @WebInitParam(name="encoding", value="UTF-8")
//)
//public class EncodingFilter implements Filter {
//
//    private String encoding;
//
//    @Override
//    public void init(FilterConfig config) {
//        encoding = config.getInitParameter("encoding");
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        request.setCharacterEncoding(encoding);
//        chain.doFilter(request, response);
//    }
//}
