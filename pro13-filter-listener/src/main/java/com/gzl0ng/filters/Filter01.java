package com.gzl0ng.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Author: guozhenglong
 * Date:2022/9/6 11:03
 */

//@WebFilter("/demo01.do")
    @WebFilter("*.do")
public class Filter01 implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("A");
        //放行,将请求发送给对应servlet
        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("A2");
    }

    @Override
    public void destroy() {

    }
}
