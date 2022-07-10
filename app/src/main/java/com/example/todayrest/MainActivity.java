package com.example.todayrest;

import static com.example.todayrest.Splash.acct;
import static com.example.todayrest.Splash.restTime;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.room.Room;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

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

    private UserDao mUserDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



// RoomDB

        UserDatabase database = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "room_db")
                .fallbackToDestructiveMigration()   // 스키마(DB) 버전 변경 가능
                .allowMainThreadQueries()           // Main Thread에 DB에 IO를 가능하게 함
                .build();
        mUserDao = database.userDao();              // 인터페이스 객체 할당

        User user = new User();

        List<User> userList = mUserDao.getUserAll();
        // 조회
        if(restTime != 0) {
            for (User i : userList) {
                if(i!=null){
                    user.setId(1);
                    user.setRestTime(restTime);
                    mUserDao.setUpdateUser(user);
                }else{
                    // 삽입
                    user.setRestTime(restTime);
                    mUserDao.setInsertUser(user);
                }
            }
        }

        // 수정
//        User user2 = new User();
//        user2.setId(1);             // 수정 때는 필요
//        user2.setName("testName_re");
//        user2.setAge("20");
//        user2.setPhoneNumber("01000000000");
//        mUserDao.setUpdateUser(user2);

        // 삭제
//        User user3 = new User();
//        user3.setId(4);
//        mUserDao.setDeleteUser(user3);


        // RoomDB


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

//    @Override
//    public void onResume() {
//        super.onResume();
//
//        acct3 = GoogleSignIn.getLastSignedInAccount(this);
//        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference(acct.getUid());
//
//
//        if (SleepTime.sleep_time != 0 || WorkTime.work_time != 0) {
//
//        } else if(myRef != null) {
//            // ex
//            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    for (DataSnapshot i : snapshot.getChildren()) {
//                        if (i.getValue() != null) {
//                            restTime = Integer.parseInt(i.getValue().toString());
//                            Log.d("dbTest", restTime + "");
//                        }
//                    }
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//
//            myRef.child("restTime").setValue(restTime);
//        }
//    }
}