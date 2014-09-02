package com.hackers.diary;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hackers.diary.constants.EventEnum;
import com.hackers.diary.database.DbManager;

import java.util.Calendar;

public class EditEventActivity extends Activity
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private TextView dateValue, timeValue;
    private EditText eventData;
    private Button dateButton, timeButton, cancelButton, createButton;

    private int year, month, date, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_event);

        dateButton = (Button) findViewById(R.id.dateButton);
        timeButton = (Button) findViewById(R.id.timeButton);

        cancelButton = (Button) findViewById(R.id.cancelButton);
        createButton = (Button) findViewById(R.id.createButton);

        dateValue = (TextView) findViewById(R.id.dateValue);
        timeValue = (TextView) findViewById(R.id.timeValue);

        eventData = (EditText) findViewById(R.id.eventData);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        date = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        updateDateTimeValue();
    }

    public void onDateSelect(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, year, month, date);
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.show();
    }

    public void onTimeSelect(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, hour, minute, true);
        timePickerDialog.show();
    }

    public void onCancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void onCreate(View view) {
        Bundle bundle = new Bundle();
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, date, hour, minute);
        bundle.putLong(DbManager.KEY_START_TIME, c.getTimeInMillis());
        bundle.putInt(DbManager.KEY_EVENT_TYPE, EventEnum.HOME.ordinal());
        bundle.putString(DbManager.KEY_EVENT_DATA, eventData.getText().toString());
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
        this.year = year;
        this.month = month;
        this.date = date;
        updateDateTimeValue();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        updateDateTimeValue();
    }

    private void updateDateTimeValue() {
        dateValue.setText(year + "-" + month + "-" + date);
        timeValue.setText(hour + ":" + minute);
    }
}
