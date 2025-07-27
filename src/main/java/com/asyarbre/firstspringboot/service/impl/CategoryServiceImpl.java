package com.asyarbre.firstspringboot.service.impl;

import com.asyarbre.firstspringboot.domain.Category;
import com.asyarbre.firstspringboot.dto.CategoryCreateRequestDto;
import com.asyarbre.firstspringboot.repository.CategoryRepository;
import com.asyarbre.firstspringboot.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public void createAndUpdateCategory(CategoryCreateRequestDto categoryCreateRequestDto) {
        Category category = categoryRepository.findByCode(categoryCreateRequestDto.getCode().toLowerCase()).orElse(new Category());
        if (categoryCreateRequestDto.getCode() == null || categoryCreateRequestDto.getCode().isEmpty()) {
            throw new IllegalArgumentException("Category code cannot be null or empty");
        }
        category.setName(categoryCreateRequestDto.getName());
        category.setCode(categoryCreateRequestDto.getCode());
        category.setDescription(categoryCreateRequestDto.getDescription());

        categoryRepository.save(category);
    }
}
