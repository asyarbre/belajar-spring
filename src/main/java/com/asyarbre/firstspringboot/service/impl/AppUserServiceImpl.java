package com.asyarbre.firstspringboot.service.impl;

import com.asyarbre.firstspringboot.dto.UserDetailResponseDto;
import com.asyarbre.firstspringboot.exception.BadRequestException;
import com.asyarbre.firstspringboot.repository.AppUserRepository;
import com.asyarbre.firstspringboot.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username).orElseThrow(() -> new BadRequestException("User not found"));
    }

    @Override
    public UserDetailResponseDto findUserDetail() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetailResponseDto userDetailResponseDto = new UserDetailResponseDto();
        String username = securityContext.getAuthentication().getName();
        userDetailResponseDto.setUsername(username);
        return userDetailResponseDto;
    }
}
