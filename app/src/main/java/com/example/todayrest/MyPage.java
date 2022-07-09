package com.example.todayrest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class MyPage extends Fragment {
    ViewGroup viewGroup;

    private TextView text_nic;
    private ImageView login_im;
    TextView logout_text;
    TextView text_time;

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_mypage, container, false);
        logout_text = viewGroup.findViewById(R.id.logout_text);
        text_time = viewGroup.findViewById(R.id.text_time);
        text_nic = viewGroup.findViewById(R.id.text_nic);
        login_im = viewGroup.findViewById(R.id.login_im);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);
        mAuth = FirebaseAuth.getInstance();


        logout_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // Firebase sign out
                mAuth.signOut();

                // Google sign out
                mGoogleSignInClient.signOut().addOnCompleteListener(getActivity(),
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
//                                Toast.makeText(getActivity().getApplicationContext(), "Complete", Toast.LENGTH_LONG).show();
                                getActivity().finish();
                            }
                        });
            }
        });

        text_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StartTime.class);
                startActivity(intent);
            }
        });
//        if(Splash.acct != null){
        if(Splash.acct == null){
            text_nic.setText(Login.acct2.getDisplayName());
            Glide.with(this).load(Login.acct2.getPhotoUrl()).circleCrop().into(login_im);
        }else{
            text_nic.setText(Splash.acct.getDisplayName());
            Glide.with(this).load(Splash.acct.getPhotoUrl()).circleCrop().into(login_im);

        }
//        }


        return viewGroup;
    }


}