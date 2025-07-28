package com.asyarbre.firstspringboot.repository;

import com.asyarbre.firstspringboot.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByCode(String code);

    Page<Category> findByNameLikeIgnoreCase(String name, Pageable pageable);

    List<Category> findByCodeIn(List<String> codes);
}
