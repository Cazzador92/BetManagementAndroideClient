package de.rocks.jsdevelopment.betmanagement.helper;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

import de.rocks.jsdevelopment.betmanagement.R;

/**
 * Created by Cazza on 24.01.2015.
 */
public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    Calendar c = Calendar.getInstance();
    int Year = c.get(Calendar.YEAR);
    int Month = c.get(Calendar.MONTH);
    int Day = c.get(Calendar.DAY_OF_MONTH);


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        // Use the current date as the default date in the picker
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, Year, Month, Day);

        return dialog;
    }


    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear,
                          int dayOfMonth) {
        // TODO Auto-generated method stub
        // Do something with the date chosen by the user
        this.Year = year;
        this.Month = monthOfYear;
        this.Day = dayOfMonth;
       // updateStartDateDisplay();

        EditText start = (EditText) getActivity().findViewById(R.id.betdetails_start);
        String date = dayOfMonth + "." + monthOfYear + "." + year ;
        start.setText(date);
        start.clearFocus();

    }

    /**
     * Date is deprecated, using Calendar.
     * @return cal
     */
    public Calendar getCalendar()
    {
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, Year);
        cal.set(Calendar.MONTH, Month);
        cal.set(Calendar.DAY_OF_MONTH, Day);

        return cal;
    }
}
