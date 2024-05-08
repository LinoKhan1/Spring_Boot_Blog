package com.linokhan.spring_boot_blog.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a blog post entity.
 */
@Data
@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot be longer than 100 characters")
    private String title;
    @NotBlank(message = "Content is required")
    @Column(columnDefinition = "TEXT") // Storing large text content
    private String content;
    @NotBlank(message = "Author is required")
    @Size(max = 50, message = "Author name cannot be longer than 50 characters")
    private String author;
    @CreationTimestamp // Automatic population of creation timestamp
    private LocalDateTime publicationDate;
    @NotBlank(message = "Slug is required")
    @Size(max = 100, message = "Slug cannot be longer than 100 characters")
    private String slug;
    private String status;

}
