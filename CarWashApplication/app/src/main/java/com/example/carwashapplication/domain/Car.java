package com.example.carwashapplication.domain;

import java.util.Date;

public class Car {
    private String PlateNumber;
    private  int uniqueNumber;
    private Date checkInDateTime;
    private Date checkOutDateTime;
    private boolean washing = false;

    public Car(String plateNumber) {
        PlateNumber = plateNumber;
        uniqueNumber = getUniqueNumber();
        checkInDateTime = new Date();
    }

    public String getPlateNumber() {
        return PlateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        PlateNumber = plateNumber;
    }

    public int getUniqueNumber() {
        String plateNumber = getPlateNumber();
        int uniqueNumber = 0;
        for(char c:plateNumber.toCharArray())
        {

            uniqueNumber=(uniqueNumber*10)+c;
        }
        return uniqueNumber;
    }

    public Date getCheckInDateTime() {
        return checkInDateTime;
    }

    public Date getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public void setCheckOutDateTime(Date checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

    public boolean isWashing() {
        return washing;
    }

    public void setWashing(boolean washing) {
        this.washing = washing;
    }
}
