package com.sine.sineagol.models;

public abstract class Person {

    private String id;
    private String name;
    private String surname;
    private String phone;
    private String birthdate;
    private String email;
    private String type;

    Person(){

    }

    Person(String name, String surname,String email, String phone, String birthdate, String type) {
        this.name=name;
        this.surname = surname;
        this.phone = phone;
        this.birthdate = birthdate;
        this.email = email;
        this.type = type;

    }
    Person(String name,String surname,String email,String phone,String type) {

        this.name = name;
        this.surname=surname;
        this.email=email;
        this.type=type;
        this.phone =phone;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}