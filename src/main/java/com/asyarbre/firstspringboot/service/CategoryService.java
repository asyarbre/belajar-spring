package com.asyarbre.firstspringboot.service;

import com.asyarbre.firstspringboot.dto.CategoryCreateRequestDto;

public interface CategoryService {
    void createAndUpdateCategory(CategoryCreateRequestDto categoryCreateRequestDto);
}
