package com.asyarbre.firstspringboot.web;

import com.asyarbre.firstspringboot.dto.CategoryCreateRequestDto;
import com.asyarbre.firstspringboot.dto.CategoryListResponseDto;
import com.asyarbre.firstspringboot.dto.ResultPageResponseDto;
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

    @GetMapping("/v1/category")
    public ResponseEntity<ResultPageResponseDto<CategoryListResponseDto>> findAllCategories(
            @RequestParam(name = "pages", defaultValue = "0") Integer pages,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "categoryName", required = false) String categoryName
    ) {
        ResultPageResponseDto<CategoryListResponseDto> response = categoryService.findCategoryList(pages, limit, sortBy, direction, categoryName);
        return ResponseEntity.ok(response);
    }
}
