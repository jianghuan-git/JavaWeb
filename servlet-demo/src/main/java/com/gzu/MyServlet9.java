package com.gzu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 获取请求信息
 */
@WebServlet(urlPatterns = "/demo9")
public class MyServlet9 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求的 URI
        String requestURI = request.getRequestURI();

        // 获取请求的完整 URL
        StringBuffer requestURL = request.getRequestURL();

        // 获取请求的上下文路径
        String contextPath = request.getContextPath();

        // 获取调用 Servlet 的路径
        String servletPath = request.getServletPath();

        // 获取请求的额外路径信息
        String pathInfo = request.getPathInfo();

        // 获取请求的查询字符串
        String queryString = request.getQueryString();

        // 获取请求的方法
        String method = request.getMethod();

        // 输出信息
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Path Information</h1>");
        out.println("<p>Request Method: " + method + "</p>");
        out.println("<p>Request URI: " + requestURI + "</p>");
        out.println("<p>Request URL: " + requestURL + "</p>");
        out.println("<p>Context Path: " + contextPath + "</p>");
        out.println("<p>Servlet Path: " + servletPath + "</p>");
        out.println("<p>Path Info: " + pathInfo + "</p>");
        out.println("<p>Query String: " + queryString + "</p>");
        out.println("</body></html>");
    }

}
