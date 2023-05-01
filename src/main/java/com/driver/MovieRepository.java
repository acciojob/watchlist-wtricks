package com.driver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private Map<String, Movie> movies = new HashMap<>();
    private Map<String, Director> directors = new HashMap<>();
    private Map<String, List<String>> movieDirectorMap = new HashMap<>();

    public void addMovie(Movie movie) {
        movies.put(movie.getName(), movie);
    }

    public void addDirector(Director director) {
        directors.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        if (movieDirectorMap.containsKey(directorName)) {
            movieDirectorMap.get(directorName).add(movieName);
        } else {
            movieDirectorMap.put(directorName, List.of(movieName));
        }
    }

    public Movie getMovieByName(String name) {
        return movies.get(name);
    }

    public Director getDirectorByName(String name) {
        return directors.get(name);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        return movieDirectorMap.get(directorName);
    }

    public List<String> findAllMovies() {
        return movies.keySet().stream().collect(Collectors.toList());
    }

    public void deleteDirectorByName(String directorName) {
        if (directors.containsKey(directorName)) {
            List<String> moviesToDelete = movieDirectorMap.get(directorName);
            if (moviesToDelete != null) {
                moviesToDelete.forEach(movieName -> movies.remove(movieName));
            }
            directors.remove(directorName);
            movieDirectorMap.remove(directorName);
        }
    }

    public void deleteAllDirectors() {
        directors.keySet().forEach(this::deleteDirectorByName);
    }

    public void deleteDirectorAndTheirMovies(String name) {
        List<String> movie = movieDirectorMap.get(name);
        if (movie == null) return;

        for(String nm : movie) {
            movies.remove(nm);
        }

        movieDirectorMap.remove(name);
        directors.remove(name);
    }

    public void deleteAllDirector() {
        for(String key : directors.keySet())
            deleteDirectorAndTheirMovies(key);
    }
}
