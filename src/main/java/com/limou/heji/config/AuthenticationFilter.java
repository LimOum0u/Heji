package com.limou.heji.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.limou.heji.common.utils.JsonUtils;
import com.limou.heji.model.dto.CustomUser;
import com.limou.heji.utils.RedisUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.limou.heji.common.constant.CacheConstants.REDIS_TOKEN_PREFIX;

/**
 * @author limoum0u
 * @date 24/3/30 22:25
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;

    private final RedisUtils redisUtils;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                RedisUtils redisUtils) {
        this.authenticationManager = authenticationManager;
        this.redisUtils = redisUtils;
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // 从请求体中获取JSON数据
            JsonNode jsonNode = JsonUtils.getObjectMapper().readTree(request.getInputStream());
            String username = jsonNode.get("username").asText();
            String password = jsonNode.get("password").asText();

            // 创建一个认证令牌
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);

            // 让AuthenticationManager进行认证
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse login request", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException,
            ServletException {
        // 生成token
        String token = generateToken(authResult);
        // 获取用户信息
        CustomUser customUser = (CustomUser) authResult.getPrincipal();
//        customUser.setPassword(null);
        String userString = JsonUtils.toJsonString(customUser);

        // 将token存入redis
        redisUtils.setEx(REDIS_TOKEN_PREFIX + token, userString, 60 * 60 * 24 * 7, TimeUnit.SECONDS);
        // 将token返回给前端
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("{\"token\":\"" + token + "\"}");
        response.getWriter().flush();
    }

    private String generateToken(Authentication authentication) {
        // 生成token的逻辑，可以是随机字符串、UUID等
        // 在这个示例中，我们简单地使用用户名作为token
        return UUID.randomUUID().toString();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("{\"error\":\"" + failed.getMessage() + "\"}");
        response.getWriter().flush();
    }
}

