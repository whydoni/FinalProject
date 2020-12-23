package com.example.mybtpns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Calendar;
import java.util.TimeZone;

public class MutasiActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private MaterialButton mDatePickerBtn;
    private TextView mSelectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutasi);
         initView();

//--------------------   Bottom Navigation   -----------------------------
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.mutasi:
                    return true;
                case R.id.pulsa:
                    startActivity(new Intent(getApplicationContext(), PulsaActivity.class));
                    overridePendingTransition(1,1);
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(1,1);
                    return true;
                }
            return false;
            }
        });

//---------------------   Date Picker    ---------------------------
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Jakarta"));
        calendar.clear();
        Long today = MaterialDatePicker.todayInUtcMilliseconds();
        calendar.setTimeInMillis(today);

        //CalenderConstraint
//        CalendarConstraints.Builder consCalender = new CalendarConstraints.Builder();
//        consCalender.setValidator(DateValidatorPointBackward.)
//        consCalender.setValidator(Date.)
//        consCalender.setStart(c);
//        consCalender.setEnd();

        //MaterialDatePicker
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Pilih tanggal : ");
        builder.setSelection(today);
//        builder.setCalendarConstraints(consCalender.build());
        final MaterialDatePicker materialDatePicker = builder.build();

        mDatePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                mSelectedDate.setText("Selected Date : " + materialDatePicker.getHeaderText());
            }
        });
}

    private void initView(){
        mSelectedDate = findViewById(R.id.selectedDate);
        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setSelectedItemId(R.id.mutasi);
        mDatePickerBtn = findViewById(R.id.mDatebtn);
    }
}