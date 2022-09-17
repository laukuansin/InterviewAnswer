package com.example.carwashapplication.util;

import androidx.fragment.app.Fragment;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class BaseFragment extends Fragment {

    protected SweetAlertDialog ErrorAlert(String message, SweetAlertDialog.OnSweetClickListener positiveButtonAction, boolean cancelable) {
        SweetAlertDialog dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE);
        dialog.setTitleText("Error");
        dialog.setContentText(message);
        dialog.setConfirmButton("OK", positiveButtonAction);
        dialog.setCancelable(cancelable);
        return dialog;
    }

    protected SweetAlertDialog SuccessAlert(String message, SweetAlertDialog.OnSweetClickListener positiveButtonAction, boolean cancelable) {
        SweetAlertDialog dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
        dialog.setTitleText("Success");
        dialog.setContentText(message);
        dialog.setConfirmButton("OK", positiveButtonAction);
        dialog.setCancelable(cancelable);
        return dialog;
    }

    protected SweetAlertDialog showProgressDialog(String message) {
        SweetAlertDialog dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        dialog.setContentText(message);
        dialog.setCancelable(false);
        return dialog;
    }
}
