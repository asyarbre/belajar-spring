package com.asyarbre.firstspringboot.web;

import com.asyarbre.firstspringboot.dto.BookCreateRequestDto;
import com.asyarbre.firstspringboot.dto.BookDetailResponseDto;
import com.asyarbre.firstspringboot.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@AllArgsConstructor
@RestController
public class BookResource {
    private final BookService bookService;

    @PostMapping("/v1/book")
    public ResponseEntity<Void> createNewBook(@RequestBody BookCreateRequestDto bookCreateRequestDto) {
        bookService.createNewBook(bookCreateRequestDto);
        return ResponseEntity.created(URI.create("/v1/book")).build();
    }

    @GetMapping("/v1/book/{bookId}")
    public ResponseEntity<BookDetailResponseDto> findBookDetail(@PathVariable("bookId") String bookId) {
        BookDetailResponseDto bookDetailResponseDto = bookService.findBookDetailById(bookId);
        return ResponseEntity.ok().body(bookDetailResponseDto);
    }
}
