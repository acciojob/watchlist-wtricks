package com.driver;

import com.driver.model.Director;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DirectorRepository {
    Map<String, Director> repo = new HashMap<>();
    Map<String, List<String>> directorMovieMap = new HashMap<>();

    public void add(Director director) {
        repo.put(director.getName(),director);
    }

    public Optional<Director> getByName(String directorName) {
        if(repo.containsKey(directorName))
            return Optional.of(repo.get(directorName));
        return Optional.empty();
    }

    public void addDirectorMovieComb(String directorName, String movieName) {
        if(directorMovieMap.containsKey(directorName)) {
            List<String> movies = directorMovieMap.get(directorName);
            movies.add(movieName);
            directorMovieMap.put(directorName, movies);
        } else {
            directorMovieMap.put(directorName, List.of(movieName));
        }
    }


    public List<String> getMoviesForDirector(String directorName) {
        return directorMovieMap.get(directorName);
    }

    public void remove(String directorName) {
        repo.remove(directorName);
        directorMovieMap.remove(directorName);
    }

    public List<Director> getAll() {
        return new ArrayList<>(repo.values());
    }
}
