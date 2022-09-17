package com.example.carwashapplication.domain;

import java.util.ArrayList;
import java.util.List;

public class CompleteCarList{
    private static List<Car> carList = new ArrayList<>();

    public CompleteCarList() {
    }

    public static List<Car>  getInstance()
    {
        return carList;
    }

}
