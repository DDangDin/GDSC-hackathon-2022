package com.example.todayrest;

import static android.app.Activity.RESULT_OK;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.GregorianCalendar;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Cal extends Fragment {
    ViewGroup viewGroup;
    private int mYear = 0, mMonth = 0, mDay = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_cal, container, false);

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), listener, 2013, 9, 22);
        dialog.show();

        dialog.setButton(
                DialogInterface.BUTTON_NEGATIVE,
                getString(R.string.cancel),
                (DatePickerDialog, which) -> {
                    if (which == DialogInterface.BUTTON_NEGATIVE) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, Home.homeFragment).commitAllowingStateLoss();

                    }
                }
        );




        return viewGroup;
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            Toast.makeText(getActivity(), year + "년" + monthOfYear + "월" + dayOfMonth +"일", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, Home.homeFragment).commitAllowingStateLoss();
        }
    };

}