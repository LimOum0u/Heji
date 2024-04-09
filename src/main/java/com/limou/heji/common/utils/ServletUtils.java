package com.limou.heji.common.utils;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author limoum0u
 * @date 23/12/25 16:10
 */
public class ServletUtils {
    /**
     * 请求格式为json
     */
    private static final String APPLICATION_JSON = "application/json";
    /**
     * 请求格式为xml
     */
    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    /**
     * json后缀
     */
    private static final String JSON_SUFFIX = ".json";
    /**
     * xml后缀
     */
    private static final String XML_SUFFIX = ".xml";
    /**
     * 索引越界
     */
    private static final int INDEX_NOT_FOUND = -1;


    /**
     * 获取String参数
     *
     * @param name 参数名
     * @return String 参数值
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取request
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     *
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     *
     * @return HttpSession
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取Attributes
     *
     * @return ServletRequestAttributes
     */
    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }
}
