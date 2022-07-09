package com.example.todayrest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private long backBtnTime = 0;
    BottomNavigationView bottomNavigationView;
    Cal cal;
    MyPage myPage;
    Home home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        cal = new Cal();
        myPage = new MyPage();
        home=new Home();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout,home).commitAllowingStateLoss();

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId()){
                   case R.id.tab1:{
                       getSupportFragmentManager().beginTransaction()
                               .replace(R.id.main_layout,cal).commitAllowingStateLoss();
                       return true;
                       
                   }
                   case R.id.tab2:{
                       getSupportFragmentManager().beginTransaction()
                               .replace(R.id.main_layout,home).commitAllowingStateLoss();
                       return true;

                   }
                   case R.id.tab3:{
                       getSupportFragmentManager().beginTransaction()
                               .replace(R.id.main_layout,myPage).commitAllowingStateLoss();
                       return true;

                   }
                   
                   default:return false;
               }
            }
        });



    }



    @Override
    public void onBackPressed(){
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && 2000 >= gapTime){
            super.onBackPressed();
        }
        else{
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료", Toast.LENGTH_SHORT).show();
        }
    }
}