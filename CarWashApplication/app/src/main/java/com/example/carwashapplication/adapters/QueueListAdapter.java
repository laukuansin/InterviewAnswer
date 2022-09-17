package com.example.carwashapplication.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carwashapplication.R;
import com.example.carwashapplication.domain.Car;

import java.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QueueListAdapter extends RecyclerView.Adapter<QueueListAdapter.ViewHolder>{
    private final Car[] _queueCarArray;

    public QueueListAdapter(Car[] _queueCarArray) {
        this._queueCarArray = _queueCarArray;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_queue,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Car car = _queueCarArray[position];

        holder._carPlateNumber.setText(car.getPlateNumber());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        holder._checkInDate.setText(String.format("Check In: %1$s",format.format(car.getCheckInDateTime())));

        if(car.isWashing())
        {
            holder._status.setText("Status: Washing");
        }
        else{
            holder._status.setText("Status: Queueing");
        }
    }

    @Override
    public int getItemCount() {
        return _queueCarArray.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView _carPlateNumber;
        private final TextView _checkInDate;
        private final TextView _status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            _carPlateNumber = itemView.findViewById(R.id.carPlateNumber);
            _checkInDate = itemView.findViewById(R.id.checkInDate);
            _status = itemView.findViewById(R.id.status);
        }
    }
}
