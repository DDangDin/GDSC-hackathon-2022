package com.example.todayrest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

public class Login extends AppCompatActivity {
    private ImageButton btn_google_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_google_login = findViewById(R.id.btn_google_login);



    }
}