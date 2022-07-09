package com.example.todayrest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Window window;
    private long backBtnTime = 0;
    BottomNavigationView bottomNavigationView;
    Cal cal;
    MyPage myPage;
    Home home;

    DatabaseReference myRef;
    FirebaseDatabase database;
    GoogleSignInAccount acct3;

    static int restTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acct3 = GoogleSignIn.getLastSignedInAccount(this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(acct3.getId());

        // ex
        myRef.child("restTime").setValue("6");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot i : snapshot.getChildren()){
                    restTime = Integer.parseInt(i.getValue().toString());
                    Log.d("dbTest", restTime+"");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });













        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // 다크모드 해제

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