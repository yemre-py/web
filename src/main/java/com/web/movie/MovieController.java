package com.web.movie;

import com.web.enums.MovieGenre;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movies/new")
    public String newMovie(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("genres", MovieGenre.values());
        return "add-movie";
    }

    @PostMapping("/movies")
    public String saveMovie(@ModelAttribute @Valid Movie movie, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "add-movie";
        }
        movieRepository.save(movie);
        redirectAttributes.addFlashAttribute("message", "Movie saved successfully");
        return "redirect:/movies/new";
    }


}
