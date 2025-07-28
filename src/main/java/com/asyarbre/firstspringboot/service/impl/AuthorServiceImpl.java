package com.asyarbre.firstspringboot.service.impl;

import com.asyarbre.firstspringboot.domain.Author;
import com.asyarbre.firstspringboot.dto.AuthorCreateRequestDto;
import com.asyarbre.firstspringboot.dto.AuthorResponseDto;
import com.asyarbre.firstspringboot.dto.AuthorUpdateRequestDto;
import com.asyarbre.firstspringboot.exception.BadRequestException;
import com.asyarbre.firstspringboot.repository.AuthorRepository;
import com.asyarbre.firstspringboot.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public AuthorResponseDto findAuthorById(String id) {
        Author author = authorRepository.findAuthorBySecureId(id).orElseThrow(() -> new BadRequestException("Author not found"));
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setName(author.getName());
        authorResponseDto.setDescription(author.getDescription());
        return authorResponseDto;
    }

    @Override
    public void createNewAuthor(List<AuthorCreateRequestDto> authorCreateRequestDtos) {
        List<Author> authors = authorCreateRequestDtos.stream().map(authorCreateRequestDto -> {
            Author author = new Author();
            author.setName(authorCreateRequestDto.getName());
            author.setDescription(authorCreateRequestDto.getDescription());
            return author;
        }).collect(Collectors.toList());

        authorRepository.saveAll(authors);
    }

    @Override
    public void updateAuthor(String id, AuthorUpdateRequestDto authorUpdateRequestDto) {
        Author author = authorRepository.findAuthorBySecureId(id).orElseThrow(() -> new BadRequestException("Author not found"));
        if (authorUpdateRequestDto.getName() != null) {
            author.setName(authorUpdateRequestDto.getName());
        }
        if (authorUpdateRequestDto.getDescription() != null) {
            author.setDescription(authorUpdateRequestDto.getDescription());
        }
        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(String id) {
        Author author = authorRepository.findAuthorBySecureId(id).orElseThrow(() -> new BadRequestException("Author not found"));
        authorRepository.delete(author);
    }
}
