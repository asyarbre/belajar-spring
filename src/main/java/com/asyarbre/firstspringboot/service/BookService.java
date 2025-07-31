package com.asyarbre.firstspringboot.service;

import com.asyarbre.firstspringboot.dto.BookCreateRequestDto;
import com.asyarbre.firstspringboot.dto.BookDetailResponseDto;
import com.asyarbre.firstspringboot.dto.BookListResponseDto;
import com.asyarbre.firstspringboot.dto.ResultPageResponseDto;

public interface BookService {
    void createNewBook(BookCreateRequestDto bookCreateRequestDto);

    BookDetailResponseDto findBookDetailById(String bookId);

    ResultPageResponseDto<BookListResponseDto> findBookList(
            Integer page,
            Integer limit,
            String sortBy,
            String direction,
            String publisherName,
            String bookTitle
    );
}
