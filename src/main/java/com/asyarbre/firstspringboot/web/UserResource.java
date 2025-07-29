package com.asyarbre.firstspringboot.web;

import com.asyarbre.firstspringboot.dto.UserDetailResponseDto;
import com.asyarbre.firstspringboot.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserResource {
    private final AppUserService appUserService;

    @GetMapping("/v1/user")
    public ResponseEntity<UserDetailResponseDto> findUserDetail() {
        return ResponseEntity.ok(appUserService.findUserDetail());
    }
}
