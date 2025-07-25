package com.asyarbre.firstspringboot.dto;

import lombok.Data;

@Data
public class AuthorUpdateRequestDto {
    private String name;
    private String description;
}
