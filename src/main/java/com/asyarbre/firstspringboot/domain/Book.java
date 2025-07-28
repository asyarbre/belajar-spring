package com.asyarbre.firstspringboot.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "book", indexes = {
        @Index(name = "idx_book_secure_id", columnList = "secure_id")
})
public class Book extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToMany()
    @JoinTable(name = "book_author", joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")})
    private List<Author> authors;

    @ManyToMany
    @JoinTable(name = "book_category", joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "category_code", referencedColumnName = "code")})
    private List<Category> categories;
}
