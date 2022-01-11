package com.sine.sineagol.models;

public class Staff extends Person {

    private double salary;
    private String position;
    private String startDate;


    Staff(){

    }
    Staff(String id, String name, String surname, String phone, String birthdate, String email, double salary, String position, String startDate) {
        super(name, surname, email, phone, birthdate, "Staff");
        this.salary = salary;
        this.position = position;
        this.startDate = startDate;
    }

    public Staff(String id, String name, String surname, String phone, String email, double salary, String position, String startDate) {
        super(name, surname, email,phone, "Staff");
        this.salary = salary;
        this.position = position;
        this.startDate = startDate;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}