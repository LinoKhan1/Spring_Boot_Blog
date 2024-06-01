package com.linokhan.spring_boot_blog.repositories;


import com.linokhan.spring_boot_blog.model.Blog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the BlogRepository class.
 */
@DataJpaTest
public class BlogRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BlogRepository blogRepository;

    /**
     * Setup method to create sample data before each test.
     */
    @BeforeEach
    void setUp() {
        Blog blog1 = new Blog(1, "Title 1", "Content 1", "Author 1", LocalDateTime.now(), "slug-1", "status 1");
        Blog blog2 = new Blog(2, "Title 2", "Content 2", "Author 2", LocalDateTime.now(), "slug-2", "status 2");

        entityManager.merge(blog1);
        entityManager.merge(blog2);


    }

    // Cleanup method to clear the database after each test
    @AfterEach
    void tearDown() {
        blogRepository.deleteAll();
    }

    /**
     * Test the findAll method of the BlogRepository.
     * Verifies that the correct number of blogs are retrieved.
     */

    @Test
    public void testFindAll() {
        List<Blog> blogs = blogRepository.findAll();
        assertEquals(2, blogs.size());
    }

    /**
     * Test the findById method of the BlogRepository.
     * Verifies that the correct number of blogs are retrieved.
     */

    @Test
    public void testFindById() {
        Optional<Blog> blogOptional = blogRepository.findById(1);
        assertTrue(blogOptional.isPresent());
        assertEquals("Title 1", blogOptional.get().getTitle());
    }


    /**
     * Test the save method of the BlogRepository.
     * Verifies that the correct blog is retrieved.
     */

    @Test
    public void testSave() {
        Blog newBlog = new Blog(1, "New Title", "New Content", "New Author", LocalDateTime.now(), "New Slug", "New Status");
        blogRepository.save(newBlog);

        List<Blog> blogs = blogRepository.findAll();
        assertEquals(3, blogs.size());
    }

    /**
     * Test the update method of the BlogRepository.
     * Verifies that the correct blog is updated.
     */

    @Test
    public void testUpdate() {
        // Find an existing blog
        Optional<Blog> blogOptional = blogRepository.findById(1);
        assertTrue(blogOptional.isPresent());

        // Update the content of the existing blog
        Blog blog = blogOptional.get();
        blog.setContent("Updated Content");
        blogRepository.save(blog);

        // Retrieve the updated blog
        Optional<Blog> updatedBlogOptional = blogRepository.findById(1);
        assertTrue(updatedBlogOptional.isPresent());
        assertEquals("Updated Content", updatedBlogOptional.get().getContent());
    }

    /**
     * Test the delete method of the BlogRepository.
     * Verifies that the correct blog is deleted.
     */
    @Test
    public void testDeleteById() {
        blogRepository.deleteById(1);

        List<Blog> blogs = blogRepository.findAll();
        assertEquals(1, blogs.size());

    }

}
