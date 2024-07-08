package com.web.movie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MovieController {

    @GetMapping("/movie")
    public String movie() {
        return "movie";
    }

    @PostMapping("/movie")
    public String addMovie(@ModelAttribute("movie") Movie movie) {
        return "movie";
    }
}
