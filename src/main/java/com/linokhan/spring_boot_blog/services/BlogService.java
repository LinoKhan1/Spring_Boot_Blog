package com.linokhan.spring_boot_blog.services;

import com.linokhan.spring_boot_blog.model.Blog;
import com.linokhan.spring_boot_blog.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing blog posts.
 */
@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    /**
     * Creates a new blog post.
     *
     * @param blog The blog post to create.
     * @return The created blog post.
     */
    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    /**
     * Retrieves all blog posts.
     *
     * @return A list of all blog posts.
     */
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    /**
     * Retrieves a blog post by ID.
     *
     * @param id The ID of the blog post to retrieve.
     * @return An Optional containing the blog post, or empty if not found.
     */
    public Optional<Blog> getBlogById(Integer id) {
        return blogRepository.findById(id);
    }

    /**
     * Updates an existing blog post.
     *
     * @param id          The ID of the blog post to update.
     * @param updatedBlog The updated blog post data.
     * @return The updated blog post, or null if the blog post with the given ID does not exist.
     */
    public Blog updateBlog(Integer id, Blog updatedBlog) {
        if (!blogRepository.existsById(id)) {
            return null;
        }
        updatedBlog.setId(id);
        return blogRepository.save(updatedBlog);
    }

    /**
     * Deletes a blog post by ID.
     *
     * @param id The ID of the blog post to delete.
     */
    public void deleteBlog(Integer id) {
        blogRepository.deleteById(id);
    }
}
