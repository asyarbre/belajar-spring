package com.asyarbre.firstspringboot.service;

import com.asyarbre.firstspringboot.dto.BookCreateRequestDto;
import com.asyarbre.firstspringboot.dto.BookDetailResponseDto;

public interface BookService {
    void createNewBook(BookCreateRequestDto bookCreateRequestDto);

    BookDetailResponseDto findBookDetailById(String bookId);
}
