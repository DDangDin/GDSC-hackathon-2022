package com.example.todayrest;

import static com.example.todayrest.Splash.restTime;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Home extends Fragment{
    ViewGroup viewGroup;
    Context context;

    private RecyclerView recyclerView;
    private MainViewAdapter adapter;
    private ArrayList<HomeItemData> list;
    static Fragment homeFragment;

    private TextView home_total_hour;
    private TextView home_total_minute;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_home, container,false);
        context = container.getContext();



        home_total_hour = viewGroup.findViewById(R.id.home_total_hour);
        home_total_minute = viewGroup.findViewById(R.id.home_total_minute);

        Log.d("HomeTest", restTime+"");
        home_total_hour.setText(String.valueOf(restTime/60));
        home_total_minute.setText(String.valueOf(restTime%60));



        homeFragment = new Home();

        recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recyclerView);

        list = new ArrayList<>();
        HomeItemData homeDB = new HomeItemData(R.drawable.swim);
        HomeItemData homeDB2 = new HomeItemData(R.drawable.nap);
        HomeItemData homeDB3 = new HomeItemData(R.drawable.music);
        HomeItemData homeDB4 = new HomeItemData(R.drawable.play);
        HomeItemData homeDB5 = new HomeItemData(R.drawable.med);
        HomeItemData homeDB6 = new HomeItemData(R.drawable.walk);
        list.add(homeDB);
        list.add(homeDB2);
        list.add(homeDB3);
        list.add(homeDB4);
        list.add(homeDB5);
        list.add(homeDB6);

        recyclerView.setHasFixedSize(true);

        adapter = new MainViewAdapter(getActivity(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

//        MainData mainData = new MainData(R.mipmap.ic_launcher, "SampleName", "SampleContent");
//        arrayList.add(mainData);
//        mainAdapter.notifyDataSetChanged();

        return viewGroup;


    }


}