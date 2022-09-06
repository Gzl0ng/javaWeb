package com.gzl0ng.filters;

import myssm.basedao.dao.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Author: guozhenglong
 * Date:2022/9/6 15:53
 */
@WebFilter("*.do")
public class OpenSessionViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            TransactionManager.beginTrans();
            System.out.println("开启事务");
            filterChain.doFilter(servletRequest,servletResponse);
            TransactionManager.commit();
            System.out.println("提交事务");
        }catch (Exception e){
            e.printStackTrace();
            try {
                TransactionManager.rollback();
                System.out.println("回滚事务");
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
