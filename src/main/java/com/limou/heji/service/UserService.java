package com.limou.heji.service;

import com.limou.heji.common.constant.CacheConstants;
import com.limou.heji.common.domain.Result;
import com.limou.heji.common.utils.JsonUtils;
import com.limou.heji.model.dto.CustomUser;
import com.limou.heji.model.dto.HejiUserDto;
import com.limou.heji.repository.UserRepository;
import com.limou.heji.utils.RedisUtils;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author limoum0u
 * @date 24/3/30 21:34
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    private final RedisUtils redisUtils;

    @Resource
    private AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, RedisUtils redisUtils) {
        this.userRepository = userRepository;
        this.redisUtils = redisUtils;
    }


    public Result<Map<String, String>> login(Map<String, String> loginParam) {
        // 将表单数据封装到 UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginParam.get("username"), loginParam.get("password"));
        // authenticate方法会调用loadUserByUsername
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }

        String token = UUID.randomUUID().toString().replace("-", "");
        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        // 保存到 Redis
        CustomUser customUser = (CustomUser) authenticate.getPrincipal();
        String jsonString = JsonUtils.toJsonString(customUser);
        redisUtils.setEx(CacheConstants.REDIS_TOKEN_PREFIX + token, jsonString, 60 * 60 * 24 * 7, TimeUnit.SECONDS);

        return Result.ofSuccess(map);
    }

    public List<HejiUserDto> getUserList(String username) {
        return userRepository.findByUsernameLike(username);
    }
}
