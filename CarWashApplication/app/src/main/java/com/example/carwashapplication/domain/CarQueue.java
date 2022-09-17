package com.example.carwashapplication.domain;

import android.util.Log;

public class CarQueue extends Queue<Car>{
    private static CarQueue instance = new CarQueue();

    public static CarQueue getInstance()
    {
        return instance;
    }

    public CarQueue() {
        super(Car.class);
    }

    @Override
    public Car[] Enqueue(Car car) {
        return super.Enqueue(car);
    }

    @Override
    public Car Dequeue() {
        return super.Dequeue();
    }

    @Override
    public Car[] getQueue() {
        return super.getQueue();
    }

    @Override
    public int getSize() {
        return super.getSize();
    }

    @Override
    public Car peek() {
        return super.peek();
    }

    @Override
    public boolean checkIsExists(String plateNumber,int ticketNumber,boolean isPlate) {
        for(int i=0;i<size;i++)
        {
            Car c = array[i];
            if(isPlate)
            {
                if(c.getPlateNumber().equalsIgnoreCase(plateNumber))
                {
                    return true;
                }
            }
            else{
                if(c.getUniqueNumber()==ticketNumber)
                {
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    public Car returnExists(String plateNumber,int ticketNumber,boolean isPlate) {
        for(int i=0;i<size;i++)
        {
            Car c = array[i];
            if(isPlate)
            {
                if(c.getPlateNumber().equalsIgnoreCase(plateNumber))
                {
                    return c;
                }
            }
            else{
                if(c.getUniqueNumber()==ticketNumber)
                {
                    return c;
                }
            }
        }
        return null;
    }
}
