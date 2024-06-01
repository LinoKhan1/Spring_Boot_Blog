package com.linokhan.spring_boot_blog.controllers;


import com.linokhan.spring_boot_blog.model.Blog;
import com.linokhan.spring_boot_blog.services.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link BlogController} class.
 */
@WebMvcTest(BlogController.class)
@AutoConfigureMockMvc(addFilters = false)

public class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlogService blogService;

    /**
     * Tests the retrieval of all blog posts.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testGetAllBlogs() throws Exception {
        // Mock the service to return a list of blogs
        Blog blog = new Blog();
        blog.setId(1);
        blog.setTitle("Test Blog");
        blog.setContent("Test Content");
        blog.setAuthor("Test Author");
        blog.setPublicationDate(LocalDateTime.now());
        blog.setSlug("test-blog");
        when(blogService.getAllBlogs()).thenReturn(Collections.singletonList(blog));

        // Send a GET request to "/rest/blogs"
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/bl" +
                        "ogs"))
                // Expect status 200 OK
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Expect the blogList view to be rendered
                .andExpect(MockMvcResultMatchers.view().name("blogList"))
                // Expect the "blogs" attribute to contain the list of blogs
                .andExpect(MockMvcResultMatchers.model().attributeExists("blogs"));

    }

    /**
     * Tests to show the blog post create form
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testShowCreateBlogForm() throws Exception {
        // Send a GET request to "/rest/blogs/create"
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/blogs/create"))
                // Expect status 200 OK
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Expect the "createBlog" view to be rendered
                .andExpect(MockMvcResultMatchers.view().name("createBlog"))
                // Expect the "blog" attribute to exist
                .andExpect(MockMvcResultMatchers.model().attributeExists("blog"));

    }

    /**
     * Tests the creation of a new blog post.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testCreateBlog() throws Exception {

        // Mock a blog post
        Blog blog = new Blog();
        blog.setTitle("Test Blog");

        // Send a POST request to "/rest/blogs" with the blog data
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/blogs")
                        .param("title", "Test Blog")
                        .param("content", "Test Content"))
                // Expect status 302 Found (redirect)
                .andExpect(MockMvcResultMatchers.status().isFound())
                // Expect redirected URL to be "/rest/blogs"
                .andExpect(MockMvcResultMatchers.redirectedUrl("/rest/blogs"));
    }

    /**
     * Tests the retrieval of a specific blog post by ID.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testShowBlogPost() throws Exception {
        // Mock a blog post
        Blog blog = new Blog();
        blog.setId(1);
        blog.setTitle("Test Blog");
        blog.setContent("Test Content");

        // Mock the service to return the blog post
        when(blogService.getBlogById(1)).thenReturn(Optional.of(blog));

        // Send a GET request to "/rest/blogs/blog/1"
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/blogs/blog/1"))
                // Expect status 200 OK
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Expect the "blogDetail" view to be rendered
                .andExpect(MockMvcResultMatchers.view().name("blogDetail"))
                // Expect the "blogPost" attribute to contain the blog post
                .andExpect(MockMvcResultMatchers.model().attributeExists("blogPost"));
    }

    /**
     * Tests to show the blog post update form
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testShowUpdateBlogForm() throws Exception {
        // Mock a blog post
        Blog blog = new Blog();
        blog.setId(1);
        blog.setTitle("Test Blog");
        blog.setContent("Test Content");

        // Mock the service to return the blog post
        when(blogService.getBlogById(1)).thenReturn(Optional.of(blog));

        // Send a GET request to "/rest/blogs/1/update"
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/blogs/1/update"))
                // Expect status 200 OK
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Expect the "editBlog" view to be rendered
                .andExpect(MockMvcResultMatchers.view().name("editBlog"))
                // Expect the "blog" attribute to contain the blog post
                .andExpect(MockMvcResultMatchers.model().attributeExists("blog"));
    }

    /**
     * Tests the update of an existing blog post.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testUpdateBlog() throws Exception {
        // Mock a blog post
        Blog existingBlog = new Blog();
        existingBlog.setId(1);
        existingBlog.setTitle("Existing Blog");
        existingBlog.setContent("Existing Content");


        // Mock the service to return the existing blog post
        when(blogService.getBlogById(1)).thenReturn(Optional.of(existingBlog));

        // Mock updated blog data
        Blog updatedBlog = new Blog();
        updatedBlog.setId(1); // Set the ID
        updatedBlog.setTitle("Updated Blog");
        updatedBlog.setContent("Updated Content");


        // Send a POST request to "/rest/blogs/1/update" with the updated blog data
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/blogs/1/update")
                        .param("title", "Updated Blog")
                        .param("content", "Updated Content"))


                // Expect status 302 Found (redirect)
                .andExpect(MockMvcResultMatchers.status().isFound())
                // Expect redirected URL to be "/rest/blogs"
                .andExpect(MockMvcResultMatchers.redirectedUrl("/rest/blogs"));

        // Verify that the updateBlog method in the service is called with the updated blog data
        verify(blogService).updateBlog(1, updatedBlog);
    }

    /**
     * Tests to show the blog post delete form
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testShowDeleteBlogForm() throws Exception {
        // Mock a blog post
        Blog blog = new Blog();
        blog.setId(1);
        blog.setTitle("Test Blog");
        blog.setContent("Test Content");

        // Mock the service to return the blog post
        when(blogService.getBlogById(1)).thenReturn(Optional.of(blog));

        // Send a GET request to "/rest/blogs/1/delete"
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/blogs/1/delete"))
                // Expect status 200 OK
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Expect the "editBlog" view to be rendered
                .andExpect(MockMvcResultMatchers.view().name("deleteBlog"))
                // Expect the "blog" attribute to contain the blog post
                .andExpect(MockMvcResultMatchers.model().attributeExists("blog"));
    }

    /**
     * Tests the deletion of an existing blog post.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testDeleteBlog() throws Exception {

        // Mock a blog post
        Blog blog = new Blog();
        blog.setId(1);
        blog.setTitle("Test Blog");
        blog.setContent("Test Content");

        // Send a DELETE request to "/rest/blogs/1/delete"
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/blogs/1/delete"))
                // Expect status 302 Found (redirect)
                .andExpect(MockMvcResultMatchers.status().isFound())
                // Expect redirected URL to be "/rest/blogs"
                .andExpect(MockMvcResultMatchers.redirectedUrl("/rest/blogs"));
        // Verify that the deleteBlog method in the service is called with the correct ID
        verify(blogService).deleteBlog(1);
    }
}
