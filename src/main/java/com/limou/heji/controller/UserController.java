package com.limou.heji.controller;

import com.limou.heji.common.domain.Result;
import com.limou.heji.model.dto.HejiUserDto;
import com.limou.heji.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author limoum0u
 * @date 24/3/30 21:33
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<Map<String, String>> login(@RequestBody Map<String, String> loginParam) {
        return userService.login(loginParam);
    }

    /**
     * 获取用户列表
     */
    @GetMapping
    public List<HejiUserDto> getUserList(@RequestParam String username) {
        if (StringUtils.isEmpty(username)) {
            username = "";
        }
        return userService.getUserList(username);
    }

}
