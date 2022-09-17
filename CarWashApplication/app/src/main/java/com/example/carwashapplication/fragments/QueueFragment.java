package com.example.carwashapplication.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.carwashapplication.R;
import com.example.carwashapplication.adapters.QueueListAdapter;
import com.example.carwashapplication.domain.Car;
import com.example.carwashapplication.domain.CarQueue;
import com.example.carwashapplication.domain.Queue;
import com.example.carwashapplication.util.BaseFragment;

import java.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QueueFragment extends BaseFragment {
    private static RecyclerView _recyclerView;
    private static QueueListAdapter adapter;

    public static QueueFragment newInstance() {
        return new QueueFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Queue");
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_queue, container, false);

        initialization(view);
        loadData();
        return view;
    }

    private void initialization(View view){
        _recyclerView = view.findViewById(R.id.recyclerView);
        _recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        _recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void update()
    {

        if(adapter!=null) {
            loadData();
        }
    }

    private void loadData()
    {
        Queue<Car> queueCar = CarQueue.getInstance();
        Car[] queueCarArray = new Car[queueCar.getSize()];
        for(int i=0;i<queueCar.getSize();i++)
        {
            queueCarArray[i] = queueCar.getQueue()[i];
        }

        adapter = new QueueListAdapter(queueCarArray);
        _recyclerView.setAdapter(adapter);
    }
}
