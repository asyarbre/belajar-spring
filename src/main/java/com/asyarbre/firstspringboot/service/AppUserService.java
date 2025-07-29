package com.asyarbre.firstspringboot.service;

import com.asyarbre.firstspringboot.dto.UserDetailResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    UserDetailResponseDto findUserDetail();
}
