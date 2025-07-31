package com.asyarbre.firstspringboot.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookListResponseDto {
    private String id;

    private String title;

    private String description;

    private String publisherName;

    private List<String> categoryCodes;

    private List<String> authorNames;
}
