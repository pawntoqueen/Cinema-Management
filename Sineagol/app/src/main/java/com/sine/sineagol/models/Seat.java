package com.sine.sineagol.models;

public class Seat {

    private String id;
    private String code;
    private boolean status;
    private String theatreName;

    public Seat(String theatreName){
        this.id = id;
        this.code =null;
        this.status = false;
        this.theatreName=theatreName;
    }
    public Seat(){
        this.code=null;
        this.status=false;
        this.theatreName="";
    }

    public String getTheatreName(){
        return  theatreName;
    }
    public void setTheatreName(String theatreName){
        this.theatreName=theatreName;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id=id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code=code;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status=status;
    }


}
