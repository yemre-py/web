package com.web.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    public MovieService(MovieRepository movieRepository) {
        List<Movie> movies = movieRepository.findAll();
    }
}
