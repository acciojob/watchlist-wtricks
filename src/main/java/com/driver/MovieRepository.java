package com.driver;

import com.driver.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    Map<String,Movie> repo = new HashMap<>();
    public void add(Movie movie) {
        repo.put(movie.getName(), movie);
    }

    public Optional<Movie> getByName(String name) {
        if(repo.containsKey(name))
            return Optional.of(repo.get(name));
        return Optional.empty();

    }

    public List<Movie> getAll() {
        return new ArrayList<>(repo.values());
    }

    public void remove(String movie) {
        repo.remove(movie);
    }
}
