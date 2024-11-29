package com.liguo.demo.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

/**
 * HttpServletRequest Util接口
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/29 16:42
 * @since 0.0.1
 */
@Api(tags = "HttpServletRequest Util接口")
@Slf4j
@RestController
@RequestMapping("/reqUtil")
public class RequestUtilController {

    @ApiOperation("获取请求信息")
    @PostMapping("/getReq")
    public void getReq() throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 1 获取单个请求头
        String userName = request.getHeader("__sf.beic.USER_NAME__");
        log.info("====获取单个请求头=====");
        log.info("user:{}", userName);

        // 2 获取所有请求头
        log.info("====获取所有请求头=====");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            log.info(headerName + ": " + headerValue);
        }

        // 3 获取请求体数据
        log.info("====获取请求体数据=====");
        BufferedReader reader = request.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        log.info(requestBody.toString());

        // 4 获取请求 URL 和 URI
        log.info("====获取请求 URL 和 URI=====");
        StringBuffer requestURL = request.getRequestURL();
        log.info(requestURL.toString());
        String requestURI = request.getRequestURI();
        log.info(requestURI);

        // 5 获取请求方法
        log.info("====获取请求方法=====");
        String method = request.getMethod();
        log.info(method);

        // 6 获取客户端信息
        log.info("====获取客户端信息=====");
        String clientIP = request.getRemoteAddr();
        log.info("Client IP: " + clientIP);
        int clientPort = request.getRemotePort();
        log.info("Client Port: " + clientPort);

        // 7 获取服务器信息
        log.info("====获取客户端信息=====");
        String serverName = request.getServerName();
        log.info("Server Name: " + serverName);
        int serverPort = request.getServerPort();
        log.info("Server Port: " + serverPort);

        // 8 获取上下文路径
        log.info("====获取上下文路径=====");
        String contextPath = request.getContextPath();
        log.info("Context Path: " + contextPath);

        // 9 获取Session对象
        log.info("====获取Session对象=====");
        String session =  (String) request.getSession().getAttribute("__sf.beic.USER_LANG__");
        log.info("lang:{}", session);

        // 10 获取和设置属性
        log.info("====获取和设置属性=====");
        // 设置属性
        request.setAttribute("attrName", "attrValue");
        // 获取属性
        Object attrValue = request.getAttribute("attrName");
        log.info("attrValue:{}", attrValue);

        // 11 获取请求协议
        log.info("====获取请求协议=====");
        String protocol = request.getProtocol();
        log.info(protocol);

        // 12 获取请求的 Referer
        log.info("====获取请求的 Referer=====");
        String referer = request.getHeader("Referer");
        log.info("Referer: " + referer);

        // 获取所有Cookie
        log.info("====获取所有Cookie=====");
        String cookieAll = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            StringBuilder cookieStr = new StringBuilder();
            for (Cookie cookie : cookies) {
                cookieStr.append("Name: ").append(cookie.getName())
                        .append(", Value: ").append(cookie.getValue()).append("\n");
            }
            cookieAll =  cookieStr.toString();
            log.info("cookie:{}", cookieAll);
        }
        // 获取特定的需要遍历
    }
}
