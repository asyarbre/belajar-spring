package com.asyarbre.firstspringboot.service.impl;

import com.asyarbre.firstspringboot.domain.Author;
import com.asyarbre.firstspringboot.domain.Book;
import com.asyarbre.firstspringboot.domain.Category;
import com.asyarbre.firstspringboot.domain.Publisher;
import com.asyarbre.firstspringboot.dto.BookCreateRequestDto;
import com.asyarbre.firstspringboot.dto.BookDetailResponseDto;
import com.asyarbre.firstspringboot.exception.BadRequestException;
import com.asyarbre.firstspringboot.repository.BookRepository;
import com.asyarbre.firstspringboot.service.AuthorService;
import com.asyarbre.firstspringboot.service.BookService;
import com.asyarbre.firstspringboot.service.CategoryService;
import com.asyarbre.firstspringboot.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;

    @Override
    public void createNewBook(BookCreateRequestDto bookCreateRequestDto) {
        List<Author> authors = authorService.findAuthors(bookCreateRequestDto.getAuthorIdList());
        List<Category> categories = categoryService.findCategories(bookCreateRequestDto.getCategoryList());
        Publisher publisher = publisherService.findPublisher(bookCreateRequestDto.getPublisherId());

        Book book = new Book();
        book.setAuthors(authors);
        book.setCategories(categories);
        book.setPublisher(publisher);
        book.setTitle(bookCreateRequestDto.getBookTitle());
        book.setDescription(bookCreateRequestDto.getDescription());
        bookRepository.save(book);
    }

    @Override
    public BookDetailResponseDto findBookDetailById(String bookId) {
        Book bookRepo = bookRepository.findBySecureId(bookId).orElseThrow(() -> new BadRequestException("Book not found"));
        BookDetailResponseDto bookDetailResponseDto = new BookDetailResponseDto();

        bookDetailResponseDto.setBookId(bookRepo.getSecureId());
        bookDetailResponseDto.setCategories(categoryService.constructCategoryListResponseDto(bookRepo.getCategories()));
        bookDetailResponseDto.setAuthors(authorService.constructAuthorResponseDto(bookRepo.getAuthors()));
        bookDetailResponseDto.setPublisher(publisherService.constructPublisherResponseDto(bookRepo.getPublisher()));
        bookDetailResponseDto.setBookTitle(bookRepo.getTitle());
        bookDetailResponseDto.setBookDescription(bookRepo.getDescription());
        return bookDetailResponseDto;
    }
}
