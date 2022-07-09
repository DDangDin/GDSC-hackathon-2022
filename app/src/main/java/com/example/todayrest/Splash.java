package com.example.todayrest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;

import com.google.firebase.auth.FirebaseAuth;

public class Splash extends AppCompatActivity {
    Window window;

    ConstraintLayout splash_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


//        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        splash_screen = findViewById(R.id.splash_screen);
        Intent loginIntent = new Intent(Splash.this, Login.class);
        Intent mainIntent = new Intent(Splash.this, MainActivity.class);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int status = NetworkManager.getConnectivityStatus(getApplicationContext());
                if(FirebaseAuth.getInstance().getCurrentUser().getUid() != null && status == NetworkManager.TYPE_WIFI){
                    startActivity(mainIntent);
                }else{
                    startActivity(loginIntent);
                }
                finish();
            }
        }, 3000); //딜레이 타임 조절

    }
}