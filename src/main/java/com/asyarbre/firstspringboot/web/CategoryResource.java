package com.asyarbre.firstspringboot.web;

import com.asyarbre.firstspringboot.dto.CategoryCreateRequestDto;
import com.asyarbre.firstspringboot.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@AllArgsConstructor
@RestController
public class CategoryResource {
    private final CategoryService categoryService;

    @PostMapping("/v1/category")
    public ResponseEntity<Void> createCategory(@RequestBody CategoryCreateRequestDto categoryCreateRequestDto) {
        categoryService.createAndUpdateCategory(categoryCreateRequestDto);
        return ResponseEntity.created(URI.create("/v1/category")).build();
    }
}
