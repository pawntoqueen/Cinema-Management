package com.sine.sineagol.DesignPatterns;

import com.sine.sineagol.models.Movie;
import com.sine.sineagol.models.Theatre;

//look the Movie class to see the builder usage
public class MovieBuilder {
    public static Movie getMovieOnList(String name, String description, double score, String category, String URLPic){
        return new Movie.MovieBuilder().setName(name).setDescription(description).setScore(score).setCategory(category).setURLPic(URLPic).getMovie();
    }
    public static Movie getMovieOnDetails( String id, String name, String description, Theatre theatre,double score, String category,String URLPic){
        return new Movie.MovieBuilder().setId(id).setName(name).setDescription(description).setTheatre(theatre).setScore(score).setCategory(category).setURLPic(URLPic).getMovie();
    }
    public static Movie getMovieOnUpdate( String id, String name, String description, Theatre theatre,double score, String category,String URLPic){
        return new Movie.MovieBuilder().setId(id).setName(name).setDescription(description).setTheatre(theatre).setScore(score).setCategory(category).setURLPic(URLPic).getMovie();
    }
}
