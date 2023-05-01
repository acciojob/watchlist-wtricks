package com.driver;

public class Director {
    private String name;
    private int numberOfMovie;
    private double imdbRating;
    public Director(String name, int numberOfMovies, double imdbRating) {
        this.name = name;
        this.imdbRating = imdbRating;
        this.numberOfMovie = numberOfMovies;
    }
    public Director() {}
    public double getImdbRating() {
        return imdbRating;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNumberOfMovie() {
        return numberOfMovie;
    }
    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }
    public void setNumberOfMovie(int numberOfMovie) {
        this.numberOfMovie = numberOfMovie;
    }
    public String getName() {
        return name;
    }
}
