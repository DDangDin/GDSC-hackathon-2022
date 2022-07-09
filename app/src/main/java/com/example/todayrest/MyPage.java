package com.example.todayrest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.BreakIterator;
import java.util.ArrayList;


public class MyPage extends Fragment{
    ViewGroup viewGroup;

    private TextView text_nic;
    private ImageView login_im;
    TextView logout_text;
    TextView text_time;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_mypage,container,false);
        logout_text = viewGroup.findViewById(R.id.logout_text);
        text_time = viewGroup.findViewById(R.id.text_time);
        text_nic = viewGroup.findViewById(R.id.text_nic);
        login_im = viewGroup.findViewById(R.id.login_im);



        logout_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        text_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StartTime.class);
                startActivity(intent);

            }
        });

        text_nic.setText(Splash.acct.getDisplayName());
        Glide.with(this).load(Splash.acct.getPhotoUrl()).circleCrop().into(login_im);
        /*Glide.with(this).load(Splash.acct.getStringExtra("profileImg")).into(logout_text);

        findViewById(R.id.button).setOnClickListener(v -> {
            UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                @Override
                public void onCompleteLogout() { //로그아웃 성공
                    finish();
                }
            });*/
        return viewGroup;
    }


}