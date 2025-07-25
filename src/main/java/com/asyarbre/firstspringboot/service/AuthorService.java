package com.asyarbre.firstspringboot.service;

import com.asyarbre.firstspringboot.dto.AuthorCreateRequestDto;
import com.asyarbre.firstspringboot.dto.AuthorResponseDto;
import com.asyarbre.firstspringboot.dto.AuthorUpdateRequestDto;

import java.util.List;

public interface AuthorService {
    AuthorResponseDto findAuthorById(Long id);

    void createNewAuthor(List<AuthorCreateRequestDto> authorCreateRequestDto);

    void updateAuthor(Long id, AuthorUpdateRequestDto authorUpdateRequestDto);

    void deleteAuthor(Long id);
}
