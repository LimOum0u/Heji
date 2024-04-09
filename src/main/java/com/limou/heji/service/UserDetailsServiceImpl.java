package com.limou.heji.service;

import com.limou.heji.model.dto.CustomUser;
import com.limou.heji.model.po.HejiUser;
import com.limou.heji.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * @author limoum0u
 * @date 23/11/7 16:31
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HejiUser user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException(
                "用户名或密码错误"));
        return new CustomUser(user.getUserId(), user.getUsername(), user.getPassword(), user.getPhonenumber(),
                null, Collections.emptyList());
    }
}
