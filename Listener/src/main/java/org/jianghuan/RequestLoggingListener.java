package org.jianghuan;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Logger;

public class RequestLoggingListener implements ServletRequestListener {

    private static final Logger LOGGER = Logger.getLogger(RequestLoggingListener.class.getName());
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

        // 记录请求开始时间
        long startTime = System.currentTimeMillis();
        String clientIp = getClientIp(request);
        String requestMethod = request.getMethod();
        String requestUri = request.getRequestURI();
        String queryString = request.getQueryString();
        String userAgent = request.getHeader("User-Agent");

        LOGGER.info("Request Start: ");
        LOGGER.info("  Time: " + dateFormat.format(new Date(startTime)));
        LOGGER.info("  Client IP: " + clientIp);
        LOGGER.info("  Method: " + requestMethod);
        LOGGER.info("  URI: " + requestUri);
        LOGGER.info("  Query String: " + (queryString != null ? queryString : "None"));
        LOGGER.info("  User-Agent: " + userAgent);

        // 将开始时间存储在 session 中（如果没有 session 则创建一个）
        HttpSession session = request.getSession(true);
        session.setAttribute("startTime", startTime);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        HttpSession session = request.getSession(false);

        if (session != null) {
            Long startTime = (Long) session.getAttribute("startTime");
            if (startTime != null) {
                long endTime = System.currentTimeMillis();
                long requestProcessingTime = endTime - startTime;

                LOGGER.info("Request End: ");
                LOGGER.info("  Processing Time: " + requestProcessingTime + "ms");
            }
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}