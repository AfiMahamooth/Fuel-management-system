package com.example.w1901982;

public class Passenger {

    /*passenger class with firstName,secondName,vehicleNo,litersRequired*/
    private String firstName;
    private String secondName;
    private String vehicleNo;
    private int litersRequired;


    /*generate getters and setters*/
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public int getLitersRequired() {
        return litersRequired;
    }

    public void setLitersRequired(int litersRequired) {
        this.litersRequired = litersRequired;
    }


}
