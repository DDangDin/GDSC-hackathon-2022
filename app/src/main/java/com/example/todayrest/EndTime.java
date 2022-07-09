package com.example.todayrest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EndTime extends AppCompatActivity {
    TextView text_am;
    TextView text_pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_time);

        text_am = findViewById(R.id.text_Am);
        text_pm = findViewById(R.id.text_Pm);

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

        ImageButton btnF=findViewById(R.id.btn_finsh);
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EndTime.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }



}