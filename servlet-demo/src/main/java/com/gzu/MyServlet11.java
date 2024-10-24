package com.gzu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

/**
 * 获取GET请求参数信息
 */
@WebServlet(urlPatterns = "/demo11")
public class MyServlet11 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Request Parameters</h1>");

        // 使用 getParameterNames()
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            out.println("<p>" + paramName + ": ");
            for (String value : paramValues) {
                out.println(value + " ");
            }
            out.println("</p>");
        }

        // 使用 getParameterMap()
        out.println("<h2>Using getParameterMap()</h2>");
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String paramName = entry.getKey();
            String[] paramValues = entry.getValue();
            out.println("<p>" + paramName + ": ");
            for (String value : paramValues) {
                out.println(value + " ");
            }
            out.println("</p>");
        }

        out.println("</body></html>");
    }
}
