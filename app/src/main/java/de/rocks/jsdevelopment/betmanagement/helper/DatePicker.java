package de.rocks.jsdevelopment.betmanagement.helper;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Cazza on 24.01.2015.
 */
public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        Calendar c = Calendar.getInstance();
        int startYear = c.get(Calendar.YEAR);
        int startMonth = c.get(Calendar.MONTH);
        int startDay = c.get(Calendar.DAY_OF_MONTH);


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            // Use the current date as the default date in the picker
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, startYear, startMonth, startDay);

            return dialog;
        }

        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            // Do something with the date chosen by the user
            startYear = year;
            startMonth = monthOfYear;
            startDay = dayOfMonth;
           // updateStartDateDisplay();
        }
}
