package com.sise.oj.util;

import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentParser;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 客户端工具类
 *
 * @author Cijee
 * @version 1.0
 */
@Slf4j
public class ClientUtils {

    public static String getClientIPAddress(HttpServletRequest request) {
        // 获取HTTP代理字段
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            // Apache http服务器 请求字段
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            // Apache http服务器 请求字段
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            // 可能有多个IP以,分隔，一般取第一个IP
            return ip.split(",")[0];
        }
        if ("127.0.0.1".equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(e.getMessage(), e);
            }
        }
        return ip;
    }

    public static String getBrowser(HttpServletRequest request) {
        UserAgent userAgent = UserAgentParser.parse(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }

}