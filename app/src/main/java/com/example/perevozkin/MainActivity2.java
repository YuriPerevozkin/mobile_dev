package com.example.perevozkin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    private String finalSelectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView username_text_view = findViewById(R.id.usernameTextView);
        CalendarView calendar_view = findViewById(R.id.calendarView);
        Button submit_date_button = findViewById(R.id.submit_date);

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1; // Months are 0-indexed
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        finalSelectedDate = currentDay + "/" + currentMonth + "/" + currentYear;

        calendar_view.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                int actual_month = month + 1;
                finalSelectedDate = day + "." + actual_month + "." + year;
            }
        });

        submit_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                intent.putExtra("DATE", finalSelectedDate);
                startActivity(intent);
            }
        });

        String username = getIntent().getStringExtra("USERNAME");
        if (username.isEmpty()) {
            username_text_view.setText("No username was provided");
            return;
        }
        username_text_view.setText("Your username is: " + username);
    }
}