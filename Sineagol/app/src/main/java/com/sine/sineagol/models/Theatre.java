package com.sine.sineagol.models;

import java.util.ArrayList;
import java.util.List;

public class Theatre {

    private String id;
    private String name;
    private final int capacity=28;
    private List<Seat> seats;


    public Theatre(String name) {
        this.name=name;
        seats=new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            seats.add(new Seat(name));
            if(i<=3) seats.get(i).setCode("A"+"-"+(i+1));
            if(i<=7 && i>3)seats.get(i).setCode("B"+"-"+(i-4+1));
            if(i<=11 && i>7)seats.get(i).setCode("C"+"-"+(i-8+1));
            if(i<=15&& i>11)seats.get(i).setCode("D"+"-"+(i-12+1));
            if(i<=19&& i>15)seats.get(i).setCode("E"+"-"+(i-16+1));
            if(i<=23&& i>19)seats.get(i).setCode("F"+"-"+(i-20+1));
            if(i<=27&& i>23)seats.get(i).setCode("G"+"-"+(i-24+1));
            seats.get(i).setStatus(false);
        }
    }
    public Theatre(){

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

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
    public Seat getSoloSeat(int id){
        return seats.get(id);
    }
    public Seat getSoloSeatByCode(String code){
        for (int i = 0; i <seats.size(); i++) {
            if(this.seats.get(i).getCode().equals(code))
                return this.seats.get(i);
        }
        return null;
    }
    public List<String> availableSeats(){
        List<String> s = new ArrayList<String>();
        for (int i = 0; i <seats.size(); i++) {
            if(seats.get(i).isStatus()==false){
                s.add(seats.get(i).getCode());
            }
        }
        return s;
    }
    public int getCapacity() {
        return capacity;
    }


}