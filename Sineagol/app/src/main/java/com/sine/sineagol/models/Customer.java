package com.sine.sineagol.models;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Customer extends Person {

    private ArrayList<Movie> watchedMovies;
    public Customer(){

    }
    public Customer(String name,String surname,String email,String phone){
        super(name,surname,email,phone,"Customer");
        this.watchedMovies = new ArrayList<Movie>();
    }
    public Customer( String name, String surname, String email, String phone, String birthdate, String type) {
        super( name, surname, email ,phone, birthdate, type);
        this.watchedMovies = new ArrayList<Movie>();
    }

    @NonNull
    @Override
    public String toString() {
        return this.getName()+" "+this.getSurname()+" "+this.getPhone();
    }

}