package com.limou.heji.filter;


import com.limou.heji.common.domain.Result;
import com.limou.heji.common.enums.ReturnCode;
import com.limou.heji.common.utils.JsonUtils;
import com.limou.heji.model.dto.CustomUser;
import com.limou.heji.utils.RedisUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.limou.heji.common.constant.CacheConstants.REDIS_TOKEN_PREFIX;

/**
 * @author limoum0u
 * @date 23/11/7 16:04
 */
@Component
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String uri = request.getRequestURI();

        if (token != null && !token.isEmpty() && !"/user/login".equals(uri)) {
            String s = redisUtils.get(REDIS_TOKEN_PREFIX + token);
            // 为空直接返回
            if (StringUtils.isEmpty(s)) {
                handleException(response);
                return;
            }
            CustomUser customUser = JsonUtils.parseObject(s, CustomUser.class);
            // 根据token获取用户信息
            if (customUser != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 创建一个认证令牌
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(customUser, null, customUser.getAuthorities());
                // 将认证令牌设置到SecurityContextHolder中
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                // 更新 redis key 过期时间
                redisUtils.expire(REDIS_TOKEN_PREFIX + token, 60 * 60 * 24 * 7, TimeUnit.SECONDS);
            }
            filterChain.doFilter(request, response);

        } else {
            // TODO: 配置不需要认证的接口 以及未登录的返回值
            if (StringUtils.equals("/user/login", uri)) {
                filterChain.doFilter(request, response);
                return;
            }
            log.error("用户访问被拒绝", uri);
            handleException(response);
        }
    }

    private void handleException(HttpServletResponse response) throws IOException {
        //  处理用户未登陆
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        // 构建响应内容
        String responseBody = JsonUtils.toJsonString(Result.ofFail(ReturnCode.USER_ERROR_A0200));
        assert responseBody != null;
        response.getWriter().write(responseBody);
        response.getWriter().flush();
    }
}
