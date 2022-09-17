package com.example.carwashapplication.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.carwashapplication.R;
import com.example.carwashapplication.domain.Car;
import com.example.carwashapplication.domain.CarQueue;
import com.example.carwashapplication.domain.CompleteCarList;
import com.example.carwashapplication.domain.Queue;
import com.example.carwashapplication.util.BaseFragment;
import com.example.carwashapplication.util.OnSingleClickListener;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HomeFragment extends BaseFragment {
    private TextInputLayout textInputPlateNumber;
    private Button submitButton;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initialization(view);

        return view;
    }

    private void initialization(View view)
    {
        textInputPlateNumber = view.findViewById(R.id.carPlateTextField);
        submitButton = view.findViewById(R.id.submitButton);
        Queue<Car> carQueue = CarQueue.getInstance();
        List<Car> _completeCarList = CompleteCarList.getInstance();
        submitButton.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                String plateNumber = textInputPlateNumber.getEditText().getText().toString();
                if(plateNumber.isEmpty())
                {
                    textInputPlateNumber.setError("Field cannot be empty");
                    return;
                }
                else{
                    textInputPlateNumber.setError(null);
                }

                if(carQueue.checkIsExists(plateNumber,0,true))
                {
                    ErrorAlert("The car already in the queue", sweetAlertDialog -> {
                        sweetAlertDialog.dismiss();
                    },false).show();
                }
                else{
                    if(checkIfExist(_completeCarList,plateNumber))
                    {
                        ErrorAlert("Your car has been washed. Please check out your car.", sweetAlertDialog -> {
                            sweetAlertDialog.dismiss();
                        },false).show();
                    }
                    else{
                        Car newCar = new Car(plateNumber);
                        carQueue.Enqueue(newCar);
                        String message = String.format("Successful check in the car. Your ticket number is %1$d. Please use this number to check out your car later",newCar.getUniqueNumber());
                        SuccessAlert(message, sweetAlertDialog -> {
                            textInputPlateNumber.getEditText().getText().clear();
                            sweetAlertDialog.dismiss();
                        },false).show();
                    }

                }
            }
        });
    }

    private boolean checkIfExist(List<Car> _completeList,String plateNumber)
    {
        for(Car car:_completeList)
        {
            if(car.getPlateNumber().equalsIgnoreCase(plateNumber))
            {
                return true;
            }
        }

        return false;
    }

}
