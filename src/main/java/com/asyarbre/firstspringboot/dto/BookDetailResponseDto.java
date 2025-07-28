package com.asyarbre.firstspringboot.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookDetailResponseDto {
    private String bookId;

    private List<AuthorResponseDto> authors;

    private List<CategoryListResponseDto> categories;

    private PublisherResponseDto publisher;

    private String bookTitle;
    
    private String bookDescription;
}
