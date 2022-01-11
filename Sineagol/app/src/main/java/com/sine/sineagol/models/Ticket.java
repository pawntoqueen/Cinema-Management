package com.sine.sineagol.models;


public class Ticket {

    private String id;
    private int ticketPrice;
    private Customer customer;
    private Seat seat;
    public Ticket(){

    }
    public Ticket(Customer customer,Seat seat,int price) {
        this.customer = customer;
        this.seat=seat;
        ticketPrice=price;

    }

    public void setId(String id){
        this.id=id;
    }
    public String getId(){
        return this.id;
    }
    public int getPrice(){
        return ticketPrice;
    }
    public void setPrice(int price){
        this.ticketPrice=price;
    }
    public Customer getCustomer(){
        return this.customer;
    }
    public void setCustomer(Customer customer){
        this.customer=customer;
    }
    public Seat getSeat(){
        return this.seat;
    }
    public void setSeat(Seat seat){
        this.seat=seat;
    }




}

