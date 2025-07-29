package com.asyarbre.firstspringboot.repository;

import com.asyarbre.firstspringboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
