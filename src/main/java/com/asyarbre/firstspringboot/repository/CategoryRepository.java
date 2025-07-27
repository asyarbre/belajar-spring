package com.asyarbre.firstspringboot.repository;

import com.asyarbre.firstspringboot.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByCode(String code);
}
