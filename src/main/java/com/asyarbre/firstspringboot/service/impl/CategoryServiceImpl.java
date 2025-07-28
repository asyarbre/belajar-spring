package com.asyarbre.firstspringboot.service.impl;

import com.asyarbre.firstspringboot.domain.Category;
import com.asyarbre.firstspringboot.dto.CategoryCreateRequestDto;
import com.asyarbre.firstspringboot.dto.CategoryListResponseDto;
import com.asyarbre.firstspringboot.dto.ResultPageResponseDto;
import com.asyarbre.firstspringboot.repository.CategoryRepository;
import com.asyarbre.firstspringboot.service.CategoryService;
import com.asyarbre.firstspringboot.utils.PaginationUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ResultPageResponseDto<CategoryListResponseDto> findCategoryList(Integer pages, Integer limit, String sortBy, String direction, String categoryName) {
        categoryName = categoryName == null ? "%" : "%" + categoryName + "%";
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);
        Page<Category> pageResult = categoryRepository.findByNameLikeIgnoreCase(categoryName, pageable);
        List<CategoryListResponseDto> categoryListResponses = pageResult.stream().map(category -> {
            CategoryListResponseDto responseDto = new CategoryListResponseDto();
            responseDto.setCode(category.getCode());
            responseDto.setName(category.getName());
            responseDto.setDescription(category.getDescription());
            return responseDto;
        }).collect(Collectors.toList());

        return PaginationUtil.createResultPageDto(categoryListResponses, pageResult.getTotalElements(), pageResult.getTotalPages());

    }

    @Override
    public List<Category> findCategories(List<String> categoryCodeList) {
        List<Category> categories = categoryRepository.findByCodeIn(categoryCodeList);
        if (categories.isEmpty()) {
            throw new IllegalArgumentException("No categories found for the provided codes");
        }
        return categories;
    }

    @Override
    public List<CategoryListResponseDto> constructCategoryListResponseDto(List<Category> categories) {
        return categories.stream().map(category -> {
            CategoryListResponseDto responseDto = new CategoryListResponseDto();
            responseDto.setCode(category.getCode());
            responseDto.setName(category.getName());
            responseDto.setDescription(category.getDescription());
            return responseDto;
        }).collect(Collectors.toList());
    }
}
