package com.asyarbre.firstspringboot.web;

import com.asyarbre.firstspringboot.dto.AuthorCreateRequestDto;
import com.asyarbre.firstspringboot.dto.AuthorResponseDto;
import com.asyarbre.firstspringboot.dto.AuthorUpdateRequestDto;
import com.asyarbre.firstspringboot.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
public class AuthorResource {
    private final AuthorService authorService;

    @GetMapping("/v1/author/{id}/detail")
    public ResponseEntity<AuthorResponseDto> findAuthorById(@PathVariable String id) {
        return ResponseEntity.ok().body(authorService.findAuthorById(id));
    }

    @PostMapping("/v1/author")
    public ResponseEntity<Void> createNewAuthor(@RequestBody List<AuthorCreateRequestDto> authorCreateRequestDto) {
        authorService.createNewAuthor(authorCreateRequestDto);
        return ResponseEntity.created(URI.create("/author")).build();
    }

    @PutMapping("/v1/author/{id}")
    public ResponseEntity<Void> updateAuthor(@PathVariable String id, @RequestBody AuthorUpdateRequestDto authorUpdateRequestDto) {
        authorService.updateAuthor(id, authorUpdateRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/v1/author/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable String id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
