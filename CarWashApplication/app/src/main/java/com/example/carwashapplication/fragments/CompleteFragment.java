package com.example.carwashapplication.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.carwashapplication.R;
import com.example.carwashapplication.adapters.CompleteListAdapter;
import com.example.carwashapplication.adapters.QueueListAdapter;
import com.example.carwashapplication.domain.Car;
import com.example.carwashapplication.domain.CarQueue;
import com.example.carwashapplication.domain.CompleteCarList;
import com.example.carwashapplication.domain.Queue;
import com.example.carwashapplication.util.BaseFragment;
import com.example.carwashapplication.util.OnSingleClickListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CompleteFragment extends BaseFragment {
    private static RecyclerView _recyclerView;
    private static CompleteListAdapter adapter;
    private List<Car> _completeList;
    public static CompleteFragment newInstance() {
        return new CompleteFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Completed");
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complete, container, false);

        initialization(view);
        loadData();
        return view;
    }

    private void initialization(View view)
    {
        _recyclerView = view.findViewById(R.id.recyclerView);
        _recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        _recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void loadData(){
        adapter = new CompleteListAdapter(CompleteCarList.getInstance());
        _recyclerView.setAdapter(adapter);
    }

    public void update()
    {
        if(adapter!=null)
        {
            loadData();
        }
    }

    public void popUpBottomDialog()
    {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(),R.style.Base_Theme_Material3_Light_BottomSheetDialog);
        bottomSheetDialog.setContentView(R.layout.bottom_dialog);
        Queue<Car> queueCar = CarQueue.getInstance();

        TextInputLayout textInputTicketNumber = bottomSheetDialog.findViewById(R.id.ticketTextField);
        Button submitButton = bottomSheetDialog.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                int ticketNumber = Integer.parseInt(textInputTicketNumber.getEditText().getText().toString());
                if(textInputTicketNumber.getEditText().getText().toString().isEmpty())
                {
                    textInputTicketNumber.setError("Field cannot be empty");
                    return;
                }
                else{
                    textInputTicketNumber.setError(null);
                }

                if(checkIfExist(_completeList,ticketNumber))
                {

                    _completeList.remove(returnExist(_completeList,ticketNumber));
                    adapter.notifyDataSetChanged();
                    SuccessAlert("Successful check out the car. Thank you!", sweetAlertDialog -> {
                        sweetAlertDialog.dismiss();
                        bottomSheetDialog.cancel();
                    },false).show();
                }
                else{
                    if(queueCar.checkIsExists("",ticketNumber,false))
                    {
                        Car c = queueCar.returnExists("",ticketNumber,false);
                        if(c.isWashing())
                        {
                            ErrorAlert("The car is being washed.", sweetAlertDialog -> {
                                sweetAlertDialog.dismiss();
                            },false).show();
                        }
                        else{
                            ErrorAlert("The car still in the queue", sweetAlertDialog -> {
                                sweetAlertDialog.dismiss();
                            },false).show();
                        }
                    }
                    else{
                        ErrorAlert("The car did not in the queue", sweetAlertDialog -> {
                            sweetAlertDialog.dismiss();
                        },false).show();
                    }
                }
            }
        });
        bottomSheetDialog.show();
    }
    private boolean checkIfExist(List<Car> _completeList,int ticketNumber)
    {
        for(Car car:_completeList)
        {
            if(car.getUniqueNumber()==ticketNumber)
            {
                return true;
            }
        }

        return false;
    }

    private Car returnExist(List<Car> _completeList,int ticketNumber)
    {
        for(Car car:_completeList)
        {
            if(car.getUniqueNumber()==ticketNumber)
            {
                return car;
            }
        }

        return null;
    }
}
