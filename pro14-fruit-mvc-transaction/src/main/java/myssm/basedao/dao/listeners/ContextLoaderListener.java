package myssm.basedao.dao.listeners;

import myssm.basedao.dao.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Author: guozhenglong
 * Date:2022/9/7 10:24
 */
//监听上下文启动，在上下文启动的时候去创建ioc容器，然后将其保存在application作用域
    //后面中央控制器在从application作用域中获取ioc容器
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //1.获取servlet上下文对象
        ServletContext application = servletContextEvent.getServletContext();
        //2.获取上下文的初始化参数
        String path = application.getInitParameter("contextConfigLocation");
        //3.创建ioc容器
        ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext(path);
        //4.ioc容器保存到application作用域
        application.setAttribute("beanFactory",beanFactory);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {


    }
}
