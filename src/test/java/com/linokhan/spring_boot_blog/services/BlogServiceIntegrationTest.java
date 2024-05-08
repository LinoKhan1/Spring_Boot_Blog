package com.linokhan.spring_boot_blog.services;


import com.linokhan.spring_boot_blog.model.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration tests for the {@link BlogService} class.
 * These tests verify the behavior of {@link BlogService} methods in an integrated environment,
 * including interaction with the database through the {@link com.linokhan.spring_boot_blog.repositories.BlogRepository}.
 */
@SpringBootTest
public class BlogServiceIntegrationTest {

    @Autowired
    private BlogService blogService;

    /**
     * Tests the creation of a new blog post and retrieval of all blog posts.
     * Verifies that the created blog post is included in the list of retrieved blog posts.
     */
    @Test
    public void testCreateAndGetAllBlogs() {
        // Create a new blog post
        Blog blog = new Blog();
        blog.setTitle("Integration Test Blog");
        blog.setContent("Integration Test Content");
        blog.setAuthor("Integration Test Author");
        blog.setPublicationDate(LocalDateTime.now());
        blog.setSlug("integration-test-blog");
        blog.setStatus("draft");

        // Save the blog post
        Blog createdBlog = blogService.createBlog(blog);

        // Retrieve all blog posts
        List<Blog> allBlogs = blogService.getAllBlogs();

        // Verify that the created blog post is in the list
        assertTrue(allBlogs.contains(createdBlog));
    }

    /**
     * Tests the retrieval and update of a blog post.
     * Verifies that the retrieved blog post can be updated successfully.
     */
    @Test
    public void testGetAndUpdateBlog() {
        // Create a new blog post
        Blog blog = new Blog();
        blog.setTitle("Integration Test Blog");
        blog.setContent("Integration Test Content");
        blog.setAuthor("Integration Test Author");
        blog.setPublicationDate(LocalDateTime.now());
        blog.setSlug("integration-test-blog");
        blog.setStatus("draft");

        // Save the blog post
        Blog createdBlog = blogService.createBlog(blog);

        // Retrieve the blog post by ID
        Optional<Blog> retrievedBlogOptional = blogService.getBlogById(createdBlog.getId());

        // Check that the blog post was retrieved
        assertTrue(retrievedBlogOptional.isPresent());
        Blog retrievedBlog = retrievedBlogOptional.get();

        // Update the retrieved blog post
        retrievedBlog.setTitle("Updated Integration Test Blog");
        retrievedBlog.setContent("Updated Integration Test Content");

        // Save the updated blog post
        Blog updatedBlog = blogService.updateBlog(retrievedBlog.getId(), retrievedBlog);

        // Verify that the blog post was updated
        assertEquals(retrievedBlog, updatedBlog);
    }

    /**
     * Tests the deletion of a blog post.
     * Verifies that the blog post is deleted successfully.
     */
    @Test
    public void testDeleteBlog() {
        // Create a new blog post
        Blog blog = new Blog();
        blog.setTitle("Integration Test Blog");
        blog.setContent("Integration Test Content");
        blog.setAuthor("Integration Test Author");
        blog.setPublicationDate(LocalDateTime.now());
        blog.setSlug("integration-test-blog");
        blog.setStatus("draft");

        // Save the blog post
        Blog createdBlog = blogService.createBlog(blog);

        // Delete the blog post
        blogService.deleteBlog(createdBlog.getId());

        // Verify that the blog post was deleted
        assertTrue(blogService.getBlogById(createdBlog.getId()).isEmpty());
    }
}
