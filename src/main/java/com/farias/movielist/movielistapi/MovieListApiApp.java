package com.farias.movielist.movielistapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.farias.movielist.movielistapi.domain.service.MovieService;

@SpringBootApplication
public class MovieListApiApp {

    public static void main(String[] args) {
        var ac = SpringApplication.run(MovieListApiApp.class, args);
        var movieService = ac.getBean(MovieService.class);
        movieService.importMovies();
    }

}
