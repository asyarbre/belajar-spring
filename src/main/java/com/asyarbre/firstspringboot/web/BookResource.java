package com.asyarbre.firstspringboot.web;

import com.asyarbre.firstspringboot.dto.BookCreateRequestDto;
import com.asyarbre.firstspringboot.dto.BookDetailResponseDto;
import com.asyarbre.firstspringboot.dto.BookListResponseDto;
import com.asyarbre.firstspringboot.dto.ResultPageResponseDto;
import com.asyarbre.firstspringboot.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/v1/book/{bookId}")
    public ResponseEntity<BookDetailResponseDto> findBookDetail(@PathVariable("bookId") String bookId) {
        BookDetailResponseDto bookDetailResponseDto = bookService.findBookDetailById(bookId);
        return ResponseEntity.ok().body(bookDetailResponseDto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/v1/book-list")
    public ResponseEntity<ResultPageResponseDto<BookListResponseDto>> findBookList(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(value = "sort_by", defaultValue = "title") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            @RequestParam(value = "publisher_name", required = false) String publisherName,
            @RequestParam(value = "book_title", required = false) String bookTitle
    ) {
        ResultPageResponseDto<BookListResponseDto> bookListResponseDto =
                bookService.findBookList(page, limit, sortBy, direction, publisherName, bookTitle);
        return ResponseEntity.ok().body(bookListResponseDto);
    }
}
