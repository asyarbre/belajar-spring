package com.asyarbre.firstspringboot.repository;

import com.asyarbre.firstspringboot.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorById(Long id);

    Optional<Author> findAuthorBySecureId(String secureId);

    Optional<Author> findByIdAndDeletedFalse(Long id);

    Optional<Author> findAuthorByNameLike(String name);

    List<Author> findBySecureIdIn(List<String> secureIds);
}
