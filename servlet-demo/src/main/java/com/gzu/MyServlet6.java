package com.gzu;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet(urlPatterns = "/demo6", loadOnStartup = 1)
public class MyServlet6 extends HttpServlet {

    /**
     * 初始化方法
     * 1.调用时机：默认情况下，第一次访问Servlet时，Servlet容器会创建Servlet实例，调用init方法进行初始化
     * 2.调用次数：1次
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        System.out.println("MyServlet6 正在初始化...");
    }

    /**
     * 服务方法
     * 1.调用时机：每次访问Servlet时，Servlet容器会调用service方法处理请求
     * 2.调用次数：多次
     * @param req
     * @param res
     * @throws IOException
     */
    public void service(ServletRequest req, ServletResponse res) throws IOException {
        res.setContentType("text/html;charset=utf-8");
        System.out.println("demo6 is running...");
        res.getWriter().write("demo6 is running...");
    }

    /**
     * 销毁方法
     * 1.调用时机：在Servlet被移除时，Servlet容器会调用destroy方法进行销毁
     * 2.调用次数：1次
     */
    @Override
    public void destroy() {
        System.out.println("MyServlet6 正在被销毁");
    }
}
