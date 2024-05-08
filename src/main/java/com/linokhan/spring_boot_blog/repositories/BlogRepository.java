package com.linokhan.spring_boot_blog.repositories;

import com.linokhan.spring_boot_blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Blog entities.
 * Extends JpaRepository to inherit basic CRUD operations.
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    // No custom methods added yet
}
