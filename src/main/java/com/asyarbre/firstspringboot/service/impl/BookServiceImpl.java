package com.asyarbre.firstspringboot.service.impl;

import com.asyarbre.firstspringboot.domain.Author;
import com.asyarbre.firstspringboot.domain.Book;
import com.asyarbre.firstspringboot.domain.Category;
import com.asyarbre.firstspringboot.domain.Publisher;
import com.asyarbre.firstspringboot.dto.BookCreateRequestDto;
import com.asyarbre.firstspringboot.dto.BookDetailResponseDto;
import com.asyarbre.firstspringboot.dto.BookListResponseDto;
import com.asyarbre.firstspringboot.dto.ResultPageResponseDto;
import com.asyarbre.firstspringboot.exception.BadRequestException;
import com.asyarbre.firstspringboot.repository.BookRepository;
import com.asyarbre.firstspringboot.service.AuthorService;
import com.asyarbre.firstspringboot.service.BookService;
import com.asyarbre.firstspringboot.service.CategoryService;
import com.asyarbre.firstspringboot.service.PublisherService;
import com.asyarbre.firstspringboot.utils.PaginationUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ResultPageResponseDto<BookListResponseDto> findBookList(Integer page, Integer limit, String sortBy, String direction, String publisherName, String bookTitle) {
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(page, limit, sort);
        Page<Book> bookResult = bookRepository.findBookList(bookTitle, publisherName, pageable);
        List<BookListResponseDto> bookListResponseDtos = bookResult.stream().map(book -> {
            BookListResponseDto bookListResponseDto = new BookListResponseDto();
            bookListResponseDto.setAuthorNames(book.getAuthors().stream().map(Author::getName).collect(Collectors.toList()));
            bookListResponseDto.setCategoryCodes(book.getCategories().stream().map(Category::getCode).collect(Collectors.toList()));
            bookListResponseDto.setTitle(book.getTitle());
            bookListResponseDto.setPublisherName(book.getPublisher().getName());
            bookListResponseDto.setDescription(book.getDescription());
            bookListResponseDto.setId(book.getSecureId());
            return bookListResponseDto;
        }).collect(Collectors.toList());
        return PaginationUtil.createResultPageDto(bookListResponseDtos, bookResult.getTotalElements(), bookResult.getTotalPages());
    }
}
