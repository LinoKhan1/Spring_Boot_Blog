package com.linokhan.spring_boot_blog.controllers;

import com.linokhan.spring_boot_blog.model.Blog;
import com.linokhan.spring_boot_blog.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("rest/blogs")
public class BlogController {


    @Autowired
    private BlogService blogService;

    @GetMapping
    public String getAllBlogs(Model model) {
        // Retrieve all blog posts from the service layer
        List<Blog> blogs = blogService.getAllBlogs();

        // Add the list of blog posts to the model
        model.addAttribute("blogs", blogs);

        // Return the name of the Thymeleaf template to render
        return "blogList";
    }

    @GetMapping("/create")
    public String showCreateBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "createBlog";
    }

    @PostMapping
    public String createBlog(@ModelAttribute("blog") Blog blog) {
        // Set publication date to the current data/time
        blog.setPublicationDate(LocalDateTime.now());

        // Set default status to "draft"
        blog.setStatus("draft");

        // Generate slug based on the title
        String title = blog.getTitle();
        String slug = title.toLowerCase().replaceAll("\\s+", "-"); // Replace spaces with hyphens
        blog.setSlug(slug);
        // Call the service method to create a new blog post
        blogService.createBlog(blog);
        return "redirect:/rest/blogs";
    }


    // Handler method to display a specific blog post by ID
    @GetMapping("/blog/{id}")
    public String showBlogPost(@PathVariable Integer id, Model model) {

        // Retrieve the blog post by ID from the service
        Optional<Blog> blogOptional = blogService.getBlogById(id);

        // Check if the blog post exists
        if (blogOptional.isPresent()) {
            // Add the blog post to the model
            model.addAttribute("blogPost", blogOptional.get());
            // return view name
            return "blogDetail";
        } else {
            return "error";
        }
    }

    @GetMapping("/{id}/update")
    public String showUpdateBlogForm(@PathVariable Integer id, Model model) {
        // Retrieve the blog post by ID from the service
        Optional<Blog> blogOptional = blogService.getBlogById(id);

        // Check if the blog post exists
        if (blogOptional.isPresent()) {
            // Add the blog post to the model
            model.addAttribute("blog", blogOptional.get());
            return "editBlog";
        } else {
            // Handle the case where the blog post is not found
            return "error";
        }
    }

    @PostMapping("/{id}/update")
    public String updateBlog(@PathVariable Integer id, @ModelAttribute Blog updatedBlog){

        Blog existingBlog = blogService.getBlogById(id).orElse(null);

        if(existingBlog != null){
            // Update the existing blog post with the data from updatedBlog
            existingBlog.setTitle(updatedBlog.getTitle());
            existingBlog.setContent(updatedBlog.getContent());
            // Save the updated blog post
            blogService.updateBlog(id, existingBlog);
            return "redirect:/rest/blogs";
        }else{
            return "error";
        }
    }



    @GetMapping("/{id}/delete")
    public String showDeleteBlogForm(@PathVariable Integer id, Model model) {
        // Retrieve the blog post by ID from the service
        Optional<Blog> blogOptional = blogService.getBlogById(id);

        // Check if the blog post exists
        if(blogOptional.isPresent()){
            // Add the blog post to the model
            model.addAttribute("blog", blogOptional.get());
            // Return the name of the Thymeleaf template to render
            return "deleteBlog";

        }else{
            // Handle the case where the blog post is not found
            return "error";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteBlog(@PathVariable Integer id) {
        blogService.deleteBlog(id);
        return "redirect:/rest/blogs";
    }
}
