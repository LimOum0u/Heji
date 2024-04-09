package com.limou.heji.common.handler;

import com.limou.heji.common.domain.Result;
import com.limou.heji.common.utils.JsonUtils;
import jakarta.annotation.Nonnull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author limoum0u
 * @date 23/11/9 10:31
 */
@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    /**
     * 重写supports方法,进行自定义规则拦截
     */
    @Override
    public boolean supports(@Nonnull MethodParameter methodParameter, @Nonnull Class<?
            extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * 全局统一返回处理类 - 核心方法
     * 返回值为Object类型并且返回为空  AbstractMessageConverterMethodProcessor#writeWithMessageConverters 方法
     * 无法触发调用本类的 beforeBodyWrite 处理,所以在Controller层尽量避免直接使用 "Object" 类型返回。
     */
    @Nullable
    @Override
    public Object beforeBodyWrite(Object o, @Nonnull MethodParameter methodParameter, @Nonnull MediaType mediaType,
                                  @Nonnull Class<? extends HttpMessageConverter<?>> aClass,
                                  @Nonnull ServerHttpRequest serverHttpRequest,
                                  @Nonnull ServerHttpResponse serverHttpResponse) {
        // o is null -> return response
        if (o == null) {
            /**当 o 返回类型为 string 并且为null会出现 java.lang.ClassCastException: Result cannot be cast to java.lang.String,
             * 则封装ResultMsg对象并转换为String返回*/
            if (methodParameter.getParameterType().getName().equals("java.lang.String")) {
                return JsonUtils.toJsonString(Result.ofSuccess("操作成功"));
            }
            return Result.ofSuccess();
        }
        //当 o 返回类型为ResultMsg(统一封装返回对象),则直接返回
        if (o instanceof Result) {
            return o;
        }
        // 当 o 为string 则特殊处理 java.lang.ClassCastException: Result cannot be cast to java.lang.String,
        // 封装ResultMsg对象并转换为String返回
        if (o instanceof String) {
            return JsonUtils.toJsonString(Result.ofSuccess(o));
        }
        return Result.ofSuccess(o);
    }

    /**
     * 自定义规则拦截器
     * 过滤: 1.检查过滤包路径 2.检查<类>过滤列表 3.检查忽略注解是否存在于类上 4.检查注解是否存在于方法上
     */
    private Boolean filter(MethodParameter methodParameter) {
        return true;
    }
}
