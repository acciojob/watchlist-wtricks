package com.driver;

import com.driver.model.Director;
import com.driver.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return new ResponseEntity("success", HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director) {
        movieService.addDirector(director);
        return new ResponseEntity("success", HttpStatus.CREATED);
    }

    @PostMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam String movieName, @RequestParam String directorName) {
        movieService.addMovieToDirector(movieName, directorName);
        return new ResponseEntity("success", HttpStatus.CREATED);

    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable String name) {
        Movie movie = movieService.getMovieByName(name);
        return new ResponseEntity(movie, HttpStatus.OK);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable String name) {
        Director director = movieService.getDirectorByName(name);
        return new ResponseEntity(director, HttpStatus.OK);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable String director) {
        List<String> movies = movieService.getMoviesByDirectorName(director);
        return new ResponseEntity(movies, HttpStatus.OK);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return new ResponseEntity(movies, HttpStatus.OK);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String name) {
        movieService.deleteDirectorAndMovies(name);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors() {
        movieService.deleteAllDirectorsAndMovies();
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
