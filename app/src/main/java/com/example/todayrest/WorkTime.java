package com.example.todayrest;

import static com.example.todayrest.Splash.restTime;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class WorkTime extends AppCompatActivity {
    EditText work_time_hour;
    EditText work_time_minute;
    static int work_time;

    DatabaseReference myRef;
    FirebaseDatabase database;
    GoogleSignInAccount acct3;

    static UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_time);

        work_time_hour = findViewById(R.id.work_time_hour);
        work_time_minute = findViewById(R.id.work_time_minute);


        ImageButton btnF = findViewById(R.id.btn_finsh);
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkTime.this, MainActivity.class);
                if (work_time_hour.getText().toString().equals("")
                        || work_time_minute.getText().toString().equals("")) {
                    Toast.makeText(WorkTime.this, "빈칸을 채워주세요", Toast.LENGTH_SHORT).show();
                } else {
                    work_time = Integer.parseInt(work_time_hour.getText().toString()) * 60 +
                            Integer.parseInt(work_time_minute.getText().toString());
                    Log.d("WorkTime", work_time + "");
                    //
////                    acct3 = GoogleSignIn.getLastSignedInAccount(WorkTime.this);
//                    database = FirebaseDatabase.getInstance();
//                    myRef = database.getReference(userUid);
//
//                    if (SleepTime.sleep_time != 0 || WorkTime.work_time != 0) {
//                        restTime = (24 * 60) - (SleepTime.sleep_time + WorkTime.work_time); // 분 단위
//                        myRef.child("restTime").setValue(restTime);
//                    }

                    restTime = (24 * 60) - (SleepTime.sleep_time + WorkTime.work_time); // 분 단위
                    Log.d("WorkTime", restTime+"");


                    startActivity(intent);
                    finish();
                }
            }
        });

    }

}