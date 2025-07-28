package com.asyarbre.firstspringboot.service;

import com.asyarbre.firstspringboot.domain.Category;
import com.asyarbre.firstspringboot.dto.CategoryCreateRequestDto;
import com.asyarbre.firstspringboot.dto.CategoryListResponseDto;
import com.asyarbre.firstspringboot.dto.ResultPageResponseDto;

import java.util.List;

public interface CategoryService {
    void createAndUpdateCategory(CategoryCreateRequestDto categoryCreateRequestDto);

    ResultPageResponseDto<CategoryListResponseDto> findCategoryList(
            Integer pages,
            Integer limit,
            String sortBy,
            String direction,
            String categoryName
    );

    List<Category> findCategories(List<String> categoryCodeList);
}
