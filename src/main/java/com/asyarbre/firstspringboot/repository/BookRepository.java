package com.asyarbre.firstspringboot.repository;

import com.asyarbre.firstspringboot.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBySecureId(String secureId);

    @Query("SELECT b FROM Book b INNER JOIN Publisher p ON p.id = b.publisher.id " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :publisherName, '%')) " +
            "AND LOWER(b.title) LIKE LOWER(CONCAT('%', :bookTitle, '%'))")
    Page<Book> findBookList(String bookTitle, String publisherName, Pageable pageable);
}
