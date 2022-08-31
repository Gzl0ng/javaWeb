1.设置编码
        tomcat之前，设置编码：
        1）get请求方式
        //如果是get请求发送的中文数据，转码稍微用有点麻烦
        String fname1 = req.getParameter("fname");
        //1.将字符串打散成字节数组
        byte[] bytes = fname1.getBytes(StandardCharsets.ISO_8859_1);
        //2.将字节数组按照设定的编码重新组装成字符串
        fname1 = new String(bytes,"UTF-8");

        2）post请求方式
        req.setCharacterEncoding("UTF-8");


     tomcat8开始，设置编码，只需要针对post方式
            req.setCharacterEncoding("UTF-8");

        //需要注意的是，设置编码（post）这一句代码必须在所有的获取参数动作之前


2.servlet的继承关系
    javax.servlet.servlet接口
        javax.servlet.GenericServlet抽象类
            javax.servlet.http.HttpServlet
    相关方法
        javax.servlet.servlet接口：
            void init（config） - 初始化方法
            void service(ServletRequest var1, ServletResponse var2) - 服务方法
            void destroy() - 销毁方法
        javax.servlet.GenericServlet抽象类：
            public abstract void service - 仍然是抽象的
        javax.servlet.http.HttpServlet抽象子类：
            void service(HttpServletRequest req, HttpServletResponse resp) - 不是抽象的
            1.String method = req.getMethod();  获取请求的发生
            2.各种if判断，根据请求方式不同，决定去调用不同的do方法
            else if (method.equals("POST")) {
                        this.doPost(req, resp);
                    } else if (method.equals("PUT")) {
                        this.doPut(req, resp);
                    } else if (method.equals("DELETE")) {
                        this.doDelete(req, resp);
            3.在HttpServlet这个抽象类中，do方法都差不多
String msg = lStrings.getString("http.method_get_not_supported");
       String protocol = req.getProtocol();
       if (protocol.endsWith("1.1")) {
            resp.sendError(405, msg);
        } else {
            resp.sendError(400, msg);
        }

        小结：
        1）继承关系 HttpServlet->GenericServlet->HttpServlet
        2)Servlet中的核心方法：init(),servie(),destroy
        3）服务方法：当有请求过来时，service会自动响应（其实是tomcat容器响应）
            在HttpServlet中我们会去分析请求的方式：到底是get，post，head还是delete等等
            然后决定调用的是那个do开头的方法
            然后再HttpServlet中这些do方法默认都是405的实现风格-要子类去实现对应的方法，负责报错
        4）因此，我们在新建servlet时，我们才会去考虑请求方法，从而决定重写哪个do方法

3.servlet的生命周期
    1)生命周期：从从生到死亡，对应servlet中的三个方法：init(),service(),destroy()
    2)默认情况下：
        第一次接受请求时，这个Servlet会进行实例化（调用构造方法），初始化（调用init），然后服务（调用service）
        从第二次请求开始，每一次都是服务
        当容器关闭时，其中的所有servlet实例会被销毁，调用销毁方法
    3)提通过案例我们发现：
        -Servlet实例tomcat只会创建一个，所有的请求都是这个实例去响应,
        -默认情况下，第一次请求时，tomcat才回去实例化，初始化，然后再服务。这样的好处是什么?提供系统的启动速度。这样的缺点是什么？第一次请求时耗时较长。
        -因此得出结论：如果需要提高系统的启动速度，当前默认情况就是这样。如果需要提高响应速度，我们应该设置Servlet的初始化时机。
    4）Servlet的初始化时机：
        -默认是第一次发请求时，实例化，初始化
        -我们可以通过web.xml的<load-on-startup>标签来设置servlet启动的先后顺序，数字越小，启动越靠前，最小为0
    5)Servlet在容器中：单例的，线程不安全的
        -单例：所有的请求都是同一个实例去响应
        -线程不安全：一个线程需要根据这个实例中的某个成员变量值去做逻辑判断。但是在之间某个时机，另一个线程改了这个变量值，从而影响第一个逻辑
        -尽量不在servlet定义成员变量；如果需要定义，尽量不要修改成员变量值

4.http协议
    1）http称之为 超文本传输协议
    2）Http是无状态的
    3）Http请求响应包含二个部分：请求和响应
        -请求：
            请求包含三个部分：1.请求行，2.请求消息头，3.请求消息体
            1)请求行包含三个消息：1.请求的方式，2，请求的URL，3.请求的协议版本
            2)请求消息头包含了很多客户端需要告诉服务器的信息，比如：我的浏览器型号，版本，我能接受的内容类型，我给你发的内容的类型，内容的长度等等
            3)请求体：三种情况
                get方式，没有请求体，但是有一个queryString
                post方式，有请求体，from data
                json格式，有请求体，request payload

            -响应：
                响应也包含三部分：1.响应行；2.响应头；3.响应体
                1）响应行包含三个消息：1.协议 2.响应状态码(200) 3.响应状态(ok)
                2.响应头：包含了服务器的信息；服务器发送给浏览器的信息（内容的媒体类型，编码，内容长度等）
                3).响应体（比如请求add.html页面时，响应的内容是<html><head><body）

5.会话
    1)Http是无状态的
        -HTTP无状态：服务器无法判断这二次请求是同一个客户端发过来的，还是不同的客户端发过来的
        -无状态带来的现实问题：第一次请求是添加商品到购物车，第二次请求是结账；如果这二次请求服务器无法区分是同一个用户的，那么就会导致混乱
        -通过回话跟踪急速来解决无状态的问题
    2)回话跟踪技术
        -客户端第一次发请求给服务器，服务器获取session，获取不到，则创建新的，然后响应给客户端
        -下次客户端给服务器发请求时，会把sessionId带给服务器，那么服务器就能获取到了，那么服务器就判断这一次请求和上次某次请求是同一个客户端，从而能够区分开客户端
        -常用的API：
            request.getSession() -> 获取当前的回话，没有则创建一个
            request.getSession(true) -> 效果和不带参数一样
            request.getSession(false) -> 获取当前回话，没有则返回null，不会创建新的

            session.getId() -> 获取sessionID
            session.isNew() -> 判断当前session是否是新的
            session.getMaxInactiveInterval(); -> session的非激活间隔,默认1800秒
            session.invalidate(); -> 让回话强制失效
            ...
    3) session保存作用域
        -session保存作用域是和具体的每一个session对应的
        -常用的API：
            void session.setAttribute(k,v)
            Object session.getAttribute(k)
            void session.removeAttribute()


    6）服务器内部转发以及客户端重定向
        1)服务器内部转发：request.getRequestDispatcher("...").forward(request,response);
            - 一次请求响应过程，对于客户端而言，内部经过了多少次转发，客户端是不知道的
            - 地址栏没有变化
        2)客户端重定向:response.sendRedirect("...")
            - 二次请求响应过程。客户端肯定知道请求URL有变化
            - 地址栏有变化

7.Thymeleaf - 视图模板技术
    1)添加thymeleaf的jar包
    2）在web.xml文件中添加配置
           - 配置前缀 view-prefix
           - 配置后缀 view-suffix
    3） 新建一个Servlet类ViewBaseServlet
    4） 使得我们的Servlet继承ViewBaseServlet

    5） 根据逻辑视图名称 得到 物理视图名称
    //此处的视图名称是 index
    //那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
    //逻辑视图名称 ：   index
    //物理视图名称 ：   view-prefix + 逻辑视图名称 + view-suffix
    //所以真实的视图名称是：      /       index       .html
    super.processTemplate("index",request,response);
    6） 使用thymeleaf的标签
      th:if   ,  th:unless   , th:each   ,   th:text

//200：正常响应
//404:找不到资源
//405:请求方式不支持
//500：服务器内部错误
//302: 重定向