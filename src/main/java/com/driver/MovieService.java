package com.driver;


import com.driver.model.Director;
import com.driver.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    DirectorRepository directorRepository;
    public void addMovie(Movie movie) {
        movieRepository.add(movie);
    }

    public void addDirector(Director director) {
        directorRepository.add(director);
    }

    public void addMovieToDirector(String movieName, String directorName) {
        Optional<Movie> movie = movieRepository.getByName(movieName);
        Optional<Director> director = directorRepository.getByName(directorName);
        if(movie.isPresent() && director.isPresent()) {
            directorRepository.addDirectorMovieComb(directorName, movieName);
        }
    }

    public Movie getMovieByName(String name) {
        Optional<Movie> movie = movieRepository.getByName(name);
        if(movie.isPresent()) {
            return movie.get();
        }
        return new Movie();
    }

    public Director getDirectorByName(String name) {
        Optional<Director> director = directorRepository.getByName(name);
        if(director.isPresent()) {
            return director.get();
        }
        return new Director();
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        List<String> movies = directorRepository.getMoviesForDirector(directorName);
        return movies;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.getAll();
        return movies;
    }

    public void deleteDirectorAndMovies(String directorName) {
        List<String> movies = directorRepository.getMoviesForDirector(directorName);
        directorRepository.remove(directorName);
        for (String movie: movies) {
            movieRepository.remove(movie);
        }
    }

    public void deleteAllDirectorsAndMovies() {
        List<Director> directors = directorRepository.getAll();
        for(Director director:directors) {
            List<String> movieNames = getMoviesByDirectorName(director.getName());
            for(String movie: movieNames) {
                movieRepository.remove(movie);
            }
        }
    }
}
