package com.asyarbre.firstspringboot.service;

import com.asyarbre.firstspringboot.dto.BookCreateRequestDto;

public interface BookService {
    void createNewBook(BookCreateRequestDto bookCreateRequestDto);
}
