package com.linokhan.spring_boot_blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling requests related to the home page.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * Displays the home page.
     *
     * @return The name of the Thymeleaf template to render.
     */
    @GetMapping("/")
    public String home() {
        return "home"; // Assuming you have a template named "home.html"
    }
}
