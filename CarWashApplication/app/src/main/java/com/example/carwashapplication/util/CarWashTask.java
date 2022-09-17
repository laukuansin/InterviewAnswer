package com.example.carwashapplication.util;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.carwashapplication.domain.Car;
import com.example.carwashapplication.domain.CarQueue;
import com.example.carwashapplication.domain.CompleteCarList;
import com.example.carwashapplication.domain.Queue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CarWashTask extends AsyncTask<Void,Void,Void> {
    private Queue<Car> queueCar;
    private List<Car> completeList;

    public CarWashTask() {
        queueCar = CarQueue.getInstance();
        completeList = CompleteCarList.getInstance();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Car carWashing = queueCar.peek();
        if(carWashing == null)
        {
            Log.d("Debug","Queue is empty");
        }
        else{
            if(carWashing.isWashing())
            {
                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                cal1.setTime(new Date());
                cal2.setTime(carWashing.getCheckOutDateTime());
                if(cal1.after(cal2))
                {
                    Car washedCar = queueCar.Dequeue();
                    completeList.add(washedCar);
                }
            }
            else{
                Calendar cal = Calendar.getInstance();
                cal.setTime(carWashing.getCheckInDateTime());
                cal.add(Calendar.MINUTE, 1);
                Date endTime = cal.getTime();
                carWashing.setCheckOutDateTime(endTime);
                carWashing.setWashing(true);
            }
        }

        return null;
    }
}
