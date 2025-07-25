package com.asyarbre.firstspringboot.web;

import com.asyarbre.firstspringboot.dto.HelloMessageResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

    @GetMapping("/hello")
    public ResponseEntity<HelloMessageResponseDto> helloWorld() {
        HelloMessageResponseDto dto = new HelloMessageResponseDto();
        dto.setMessage("Hello World");
        return ResponseEntity.ok().body(dto);
    }
}
