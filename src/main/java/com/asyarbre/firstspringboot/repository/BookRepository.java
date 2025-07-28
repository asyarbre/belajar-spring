package com.asyarbre.firstspringboot.repository;

import com.asyarbre.firstspringboot.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}
