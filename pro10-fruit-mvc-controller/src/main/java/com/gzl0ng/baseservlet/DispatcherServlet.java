package com.gzl0ng.baseservlet;

import com.gzl0ng.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: guozhenglong
 * Date:2022/9/1 10:40
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private Map<String,Object> beanMap = new HashMap<>();

    public DispatcherServlet(){

    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init.......");
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            //1.创建DocumentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory =DocumentBuilderFactory.newInstance();
            //2.创建DocumentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //3.创建Document对象
            Document document = documentBuilder.parse(inputStream);

            //4.获取所有的bean节点
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");
                    Class<?> controllerBeanClass = Class.forName(className);
                    Object beanObj = controllerBeanClass.newInstance();

                    beanMap.put(beanId,beanObj);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("server.....");
        //设置编码
        req.setCharacterEncoding("UTF-8");
        //假设URL是：http://localhost:8080/hello.do
        //假设servletPath是：/hello.do
        //我的思路是：
        //第一步：/hello.do -> hello   或者/fruit.do -> fruit
        //第二步： hello -> HelloController  或者  fruit-> FruitController
        String servletPath = req.getServletPath();
        System.out.println("servletPath = "+servletPath);//servletPath = /hello.do

        servletPath = servletPath.substring(1);
        int lastIndexOf = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0,lastIndexOf);

        System.out.println("servletPath123 = "+servletPath);//servletPath123 = hello

        Object controllerBeanObj = beanMap.get(servletPath);

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
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

//常见错误java.lang.IllegalArgumentException: argument type mismatch