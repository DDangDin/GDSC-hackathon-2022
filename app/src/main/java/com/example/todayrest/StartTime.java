package com.example.todayrest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class StartTime extends AppCompatActivity {
    TextView text_am;
    TextView text_pm;
    ImageButton btn_start_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_time);

        text_am = findViewById(R.id.text_Am);
        text_pm = findViewById(R.id.text_Pm);
        btn_start_next = findViewById(R.id.btn_start_next);

        text_am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_am.setTextColor(Color.BLACK);
                text_pm.setTextColor(Color.LTGRAY);
            }
        });

        text_pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_pm.setTextColor(Color.BLACK);
                text_am.setTextColor(Color.LTGRAY);
            }
        });
        btn_start_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartTime.this, EndTime.class);
                startActivity(intent);
            }
        });

    }
}