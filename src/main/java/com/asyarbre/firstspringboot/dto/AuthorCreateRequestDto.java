package com.asyarbre.firstspringboot.dto;

import lombok.Data;

@Data
public class AuthorCreateRequestDto {
    private String name;
    private String description;
}
