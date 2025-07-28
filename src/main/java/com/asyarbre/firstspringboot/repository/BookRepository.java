package com.asyarbre.firstspringboot.repository;

import com.asyarbre.firstspringboot.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBySecureId(String secureId);
}
