package com.gzl0ng.baseservlet;

import com.gzl0ng.util.StringUtil;
import myssm.basedao.dao.DispatcherServletException;
import myssm.basedao.dao.ioc.BeanFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Author: guozhenglong
 * Date:2022/9/1 10:40
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;
    public DispatcherServlet(){

    }

    @Override
    public void init() throws ServletException {
        super.init();
        //此前是在此处主动创建ioc容器的
        //现在优化为从application作用域去获取
//        beanFactory = new ClassPathXmlApplicationContext();
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");
        if (beanFactoryObj!=null){
            beanFactory = (BeanFactory) beanFactoryObj;
        }else {
            throw new RuntimeException("IOc容器获取失败!");
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("server.....");
        //设置编码
//        req.setCharacterEncoding("UTF-8");
        //假设URL是：http://localhost:8080/hello.do
        //假设servletPath是：/hello.do
        //我的思路是：
        //第一步：/hello.do -> hello   或者/fruit.do -> fruit
        //第二步： hello -> HelloController  或者  fruit-> FruitController
        String servletPath = req.getServletPath();
//        System.out.println("servletPath = "+servletPath);//servletPath = /hello.do

        servletPath = servletPath.substring(1);
        int lastIndexOf = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0,lastIndexOf);

//        System.out.println("servletPath123 = "+servletPath);//servletPath123 = hello

        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operate = req.getParameter("operate");
        if (StringUtil.isEmpty(operate)){
            operate = "index";
        }

        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (operate.equals(method.getName())){
                    //1.统一获取请求参数

                    //1.1获取当前方法的参数,返回参数数组
                    Parameter[] parameters = method.getParameters();
                    //1.2parameterValues依赖承载参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();

                        //如果参数名为request，response，session，那么就不是通过请求中获取参数
                        if ("request".equals(parameterName)){
                            parameterValues[i]=req;
                        }else if ("response".equals(parameterName)){
                            parameterValues[i]=resp;
                        }else if ("session".equals(parameterName)){
                            parameterValues[i] = req.getSession();
                        }else {
                            //从请求中获取参数值
                            String parameterValue = req.getParameter(parameterName);
                            String typeName = parameter.getType().getName();

                            Object parameterObj = parameterValue;

                            //这里参数只用到了int和string，如果要处理其它入long和double可以多写一些判断
                            if (parameterObj!=null){
                                if ("java.lang.Integer".equals(typeName)){
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }

                            parameterValues[i] = parameterObj;//“2” 而不是2
                        }
                    }

                    //2.controller组件中的方法调用
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, parameterValues);

                    //3.视图处理
                    String methodReturnStr = (String)returnObj;
                    if (methodReturnStr.startsWith("redirect:")){//比如：redirect:fruit.do
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        resp.sendRedirect(redirectStr);
                    }else {
                        super.processTemplate(methodReturnStr,req,resp);//比如：edit
                    }
                }
            }

//            }else {
//                throw new RuntimeException("operate值非法");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DispatcherServletException("DispatcherServlet出错了");
        }
    }
}

//常见错误java.lang.IllegalArgumentException: argument type mismatch