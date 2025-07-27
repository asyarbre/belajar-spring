package com.asyarbre.firstspringboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryCreateRequestDto {
    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String description;
}
