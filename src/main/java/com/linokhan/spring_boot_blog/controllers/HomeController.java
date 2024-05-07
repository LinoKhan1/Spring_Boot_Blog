// controllers/HomeController.java
package com.linokhan.spring_boot_blog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // Assuming you have a template named "home.html"
    }
}
