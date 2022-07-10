package com.example.todayrest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SleepTime extends AppCompatActivity {
    TextView text_am;
    TextView text_pm;
    ImageButton btn_start_next;
    EditText sleep_time_hour;
    EditText sleep_time_minute;
    static int sleep_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_time);

        btn_start_next = findViewById(R.id.btn_start_next);
        sleep_time_hour = findViewById(R.id.sleep_time_hour);
        sleep_time_minute = findViewById(R.id.sleep_time_minute);

        btn_start_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SleepTime.this, WorkTime.class);
                if(sleep_time_hour.getText().toString().equals("")
                        || sleep_time_minute.getText().toString().equals("")){
                    Toast.makeText(SleepTime.this, "빈칸을 채워주세요", Toast.LENGTH_SHORT).show();
                }else{
                    sleep_time = Integer.parseInt(sleep_time_hour.getText().toString()) * 60 +
                            Integer.parseInt(sleep_time_minute.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }
}