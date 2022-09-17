package com.example.carwashapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carwashapplication.R;
import com.example.carwashapplication.domain.Car;

import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CompleteListAdapter extends RecyclerView.Adapter<CompleteListAdapter.ViewHolder>{
    private final List<Car> _completeList;

    public CompleteListAdapter(List<Car> _completeList) {
        this._completeList = _completeList;
    }

    @NonNull
    @Override
    public CompleteListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_complete,parent,false);
        return new CompleteListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Car car = _completeList.get(position);

        holder._carPlateNumber.setText(car.getPlateNumber());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        holder._checkInDate.setText(String.format("Check In: %1$s",format.format(car.getCheckInDateTime())));
        holder._checkOutDate.setText(String.format("Check Out: %1$s",format.format(car.getCheckOutDateTime())));
    }

    @Override
    public int getItemCount() {
        return _completeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView _carPlateNumber;
        private final TextView _checkInDate;
        private final TextView _checkOutDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            _carPlateNumber = itemView.findViewById(R.id.carPlateNumber);
            _checkInDate = itemView.findViewById(R.id.checkInDate);
            _checkOutDate = itemView.findViewById(R.id.checkOutDate);
        }
    }
}
