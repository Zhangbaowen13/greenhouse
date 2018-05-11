package com.example.pengpeng;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class DatePickerFragment extends DialogFragment {
    private DatePicker mDatePicker;
    public static final String EXTRA_DATE="com.bignerdrance.android.criminalintent.date";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        View v= LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date,null);
        mDatePicker=(DatePicker)v.findViewById(R.id.dialog_date_date_picker);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("请选择时间：")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year=mDatePicker.getYear();
                        int month=mDatePicker.getMonth();
                        int day=mDatePicker.getDayOfMonth();
                        Date date=new GregorianCalendar(year,month,day).getTime();
                        sendResult(Activity.RESULT_OK,date);
                    }
                })
                .create();
    }
    private void sendResult(int resultCode,Date date){
        if(getTargetFragment()==null){return;}
        Intent intent=new Intent();
        intent.putExtra(EXTRA_DATE,date);
        getTargetFragment()
                .onActivityResult(getTargetRequestCode(),resultCode,intent);
    }
    public static DatePickerFragment newInstance(Date date){
        Bundle args=new Bundle();
        args.putSerializable("date",date);

        DatePickerFragment fragment=new DatePickerFragment();
        fragment.setArguments(args);
        return  fragment;
    }
}
