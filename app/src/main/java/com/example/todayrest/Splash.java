package com.example.todayrest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Room;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Splash extends AppCompatActivity {
    Window window;

    ConstraintLayout splash_screen;

    static FirebaseUser acct;

    DatabaseReference myRef;
    FirebaseDatabase database;
    GoogleSignInAccount acct3;

    static UserDao mUserDao;

    static int restTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        acct = FirebaseAuth.getInstance().getCurrentUser();

//        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        splash_screen = findViewById(R.id.splash_screen);
        Intent loginIntent = new Intent(Splash.this, Login.class);
        Intent mainIntent = new Intent(Splash.this, MainActivity.class);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                int status = NetworkManager.getConnectivityStatus(getApplicationContext());
                if(acct != null){
                    if(acct.getUid() != null){

                        // RoomDB

//                        UserDatabase database = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "room_db")
//                                .fallbackToDestructiveMigration()   // 스키마(DB) 버전 변경 가능
//                                .allowMainThreadQueries()           // Main Thread에 DB에 IO를 가능하게 함
//                                .build();
//                        mUserDao = database.userDao();              // 인터페이스 객체 할당
//
//                        List<User> userList = mUserDao.getUserAll();
//                        restTime = userList.get(0).getRestTime();
//                        // 조회
//                        for (User i : userList) {
//                            Log.d("DB_Test", i.getRestTime() + "\n");
//                        }

//
//                        database = FirebaseDatabase.getInstance();
//                        myRef = database.getReference(acct.getUid());
//
//                        // ex
//                        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                Log.d("dbTest2", snapshot.child(acct.getUid())+ "");
//                                for (DataSnapshot i : snapshot.getChildren()) {
//                                        restTime = Integer.parseInt(i.getValue().toString());
//                                        Log.d("dbTest", restTime + "");
//                                }
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });


                        startActivity(mainIntent);
                    }
                    else{
                        startActivity(loginIntent);
                    }
                }
                else{
                    startActivity(loginIntent);
                }
                finish();
            }
        }, 3000); //딜레이 타임 조절

    }
}