package com.gzu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * 获取请求头信息
 */
@WebServlet(urlPatterns = "/demo10")
public class MyServlet10 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Request Headers</h1>");

        // 获取所有请求头的名称
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                // 获取该请求头的所有值
                Enumeration<String> headers = request.getHeaders(headerName);
                while (headers.hasMoreElements()) {
                    String headerValue = headers.nextElement();
                    out.println("<p>" + headerName + ": " + headerValue + "</p>");
                }
            }
        }

        out.println("</body></html>");
    }

}
