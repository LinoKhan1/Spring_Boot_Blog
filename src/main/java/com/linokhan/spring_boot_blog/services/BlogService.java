package com.linokhan.spring_boot_blog.services;


import com.linokhan.spring_boot_blog.model.Blog;
import com.linokhan.spring_boot_blog.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    // Create a new blog post
    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    // Retrieve all blog posts
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    // Retrieve a blog post by ID
    public Optional<Blog> getBlogById(Integer id) {
        return blogRepository.findById(id);
    }

    // Update an existing blog post
    public Blog updateBlog(Integer id, Blog updatedBlog) {
        if (!blogRepository.existsById(id)) {
            return null;
        }
        updatedBlog.setId(id);
        return blogRepository.save(updatedBlog);
    }

    // Delete a blog post by ID
    public void deleteBlog(Integer id) {
        blogRepository.deleteById(id);
    }

}
