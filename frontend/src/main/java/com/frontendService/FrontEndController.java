package com.frontendService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class FrontEndController {

    @GetMapping("/")
    public String home() {
        return "index"; // Esto buscará `students.html` en `src/main/resources/static`
    }

    @GetMapping("/student")
    public String studentPage() {
        return "student"; // Esto buscará `students.html` en `src/main/resources/static/students`
    }

    @GetMapping("/courses")
    public String coursePage() {
        return "courses/index"; // Esto buscará `students.html` en `src/main/resources/static/courses`
    }
}
