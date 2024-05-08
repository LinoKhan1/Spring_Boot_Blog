package com.linokhan.spring_boot_blog.services;


import com.linokhan.spring_boot_blog.model.Blog;
import com.linokhan.spring_boot_blog.repositories.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link BlogService} class.
 */
@SpringBootTest
public class BlogServiceTest {

    @Mock
    private BlogRepository blogRepository;

    @InjectMocks
    private BlogService blogService;

    private Blog testBlog;

    @BeforeEach
    public void setUp() {
        // Create a sample blog post for testing
        testBlog = new Blog();
        testBlog.setId(1);
        testBlog.setTitle("Test Blog");
        testBlog.setContent("Test Content");
        testBlog.setAuthor("Test Author");
        testBlog.setPublicationDate(LocalDateTime.now());
        testBlog.setSlug("test-blog");
        testBlog.setStatus("draft");
    }
    /**
     * Test for the {@link BlogService#createBlog(Blog)} method.
     */
    @Test
    public void testCreateBlog() {
        // Arrange
        when(blogRepository.save(any(Blog.class))).thenReturn(testBlog);

        // Act
        Blog createdBlog = blogService.createBlog(testBlog);

        // Assert
        assertNotNull(createdBlog);
        assertEquals(testBlog.getId(), createdBlog.getId());
        assertEquals(testBlog.getTitle(), createdBlog.getTitle());
        assertEquals(testBlog.getContent(), createdBlog.getContent());
        assertEquals(testBlog.getAuthor(), createdBlog.getAuthor());
        assertEquals(testBlog.getPublicationDate(), createdBlog.getPublicationDate());
        assertEquals(testBlog.getSlug(), createdBlog.getSlug());
        assertEquals(testBlog.getStatus(), createdBlog.getStatus());

        verify(blogRepository, times(1)).save(any(Blog.class));
    }


    /**
     * Test for the {@link BlogService#getAllBlogs()} method.
     */
    @Test
    public void testGetAllBlogs() {
        // Arrange
        when(blogRepository.findAll()).thenReturn(List.of(testBlog));

        // Act
        var retrievedBlogs = blogService.getAllBlogs();

        // Assert
        assertNotNull(retrievedBlogs);
        assertEquals(1, retrievedBlogs.size());
        assertEquals(testBlog, retrievedBlogs.get(0));

        verify(blogRepository, times(1)).findAll();
    }

    /**
     * Test for the {@link BlogService#getBlogById(Integer)} method.
     */
    @Test
    public void testGetBlogById() {
        // Arrange
        when(blogRepository.findById(testBlog.getId())).thenReturn(Optional.of(testBlog));

        // Act
        var retrievedBlog = blogService.getBlogById(testBlog.getId());

        // Assert
        assertTrue(retrievedBlog.isPresent());
        assertEquals(testBlog, retrievedBlog.get());

        verify(blogRepository, times(1)).findById(testBlog.getId());
    }

    /**
     * Test for the {@link BlogService#updateBlog(Integer, Blog)} method.
     */
    @Test
    public void testUpdateBlog() {
        // Arrange
        Blog updatedBlog = new Blog();
        updatedBlog.setId(testBlog.getId());
        updatedBlog.setTitle("Updated Title");
        updatedBlog.setContent("Updated Content");
        updatedBlog.setAuthor("Updated Author");
        updatedBlog.setPublicationDate(testBlog.getPublicationDate());
        updatedBlog.setSlug(testBlog.getSlug());
        updatedBlog.setStatus(testBlog.getStatus());

        when(blogRepository.existsById(testBlog.getId())).thenReturn(true);
        when(blogRepository.save(updatedBlog)).thenReturn(updatedBlog);

        // Act
        var result = blogService.updateBlog(testBlog.getId(), updatedBlog);

        // Assert
        assertNotNull(result);
        assertEquals(updatedBlog, result);

        verify(blogRepository, times(1)).existsById(testBlog.getId());
        verify(blogRepository, times(1)).save(updatedBlog);
    }


    /**
     * Test for the {@link BlogService#deleteBlog(Integer)} method.
     */
    @Test
    public void testDeleteBlog() {
        // Arrange
        int blogId = testBlog.getId();

        // Act
        blogService.deleteBlog(blogId);

        // Assert
        verify(blogRepository, times(1)).deleteById(blogId);
    }
}
