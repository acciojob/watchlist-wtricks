package com.driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(Movie movie) {
        movieRepository.addMovie(movie);
        return "Movie added successfully";
    }

    public String addDirector(Director director) {
        movieRepository.addDirector(director);
        return "Director added successfully";
    }

    public String addMovieDirectorPair(String movieName, String directorName) {
        Movie movie = movieRepository.getMovieByName(movieName);
        Director director = movieRepository.getDirectorByName(directorName);

        if (movie == null) {
            return "Movie with name " + movieName + " not found";
        }

        if (director == null) {
            return "Director with name " + directorName + " not found";
        }

        movieRepository.addMovieDirectorPair(movieName, directorName);

        return "Movie-Director pair added successfully";
    }

    public Movie getMovieByName(String name) {
        return movieRepository.getMovieByName(name);
    }

    public Director getDirectorByName(String name) {
        return movieRepository.getDirectorByName(name);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        if (movieRepository.getDirectorByName(directorName) == null) {
            return new ArrayList<>();
        }

        return movieRepository.getMoviesByDirectorName(directorName);
    }

    public List<String> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public String deleteDirectorByName(String name) {
        Director director = movieRepository.getDirectorByName(name);

        if (director == null) {
            return "Director with name " + name + " not found";
        }

        movieRepository.deleteDirectorAndTheirMovies(name);
        return "Director and all associated movies deleted successfully";
    }

    public String deleteAllDirectors() {
        movieRepository.deleteAllDirector();
        return "All directors and their associated movies deleted successfully";
    }
}