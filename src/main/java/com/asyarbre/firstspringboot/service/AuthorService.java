package com.asyarbre.firstspringboot.service;

import com.asyarbre.firstspringboot.domain.Author;
import com.asyarbre.firstspringboot.dto.AuthorCreateRequestDto;
import com.asyarbre.firstspringboot.dto.AuthorResponseDto;
import com.asyarbre.firstspringboot.dto.AuthorUpdateRequestDto;

import java.util.List;

public interface AuthorService {
    AuthorResponseDto findAuthorById(String id);

    void createNewAuthor(List<AuthorCreateRequestDto> authorCreateRequestDto);

    void updateAuthor(String id, AuthorUpdateRequestDto authorUpdateRequestDto);

    void deleteAuthor(String id);

    List<Author> findAuthors(List<String> authorIdList);

    List<AuthorResponseDto> constructAuthorResponseDto(List<Author> authors);
}
