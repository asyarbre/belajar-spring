package com.asyarbre.firstspringboot.repository;

import com.asyarbre.firstspringboot.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    public Optional<Publisher> findBySecureId(String secureId);
}
