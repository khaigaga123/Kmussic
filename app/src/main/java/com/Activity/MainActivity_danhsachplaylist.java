package com.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.Adapter.DanhsachplaylistAdapter;
import com.Model.BatHatHot;
import com.Model.PlayList;
import com.Service.APIservice;
import com.Service.Dataservice;
import com.example.kmusics.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_danhsachplaylist extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    DanhsachplaylistAdapter danhsachplaylistAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danhsachplaylist);
        anhxa();
        init();
        getdata();
    }

    private void getdata() {
        Dataservice dataservice= APIservice.Getservice();
        Call<List<PlayList>> callback=dataservice.Getdanhsachplaylist();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                ArrayList<PlayList> arrayplaylist=(ArrayList<PlayList>) response.body();
                danhsachplaylistAdapter=new DanhsachplaylistAdapter(MainActivity_danhsachplaylist.this,arrayplaylist);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity_danhsachplaylist.this,2));
                recyclerView.setAdapter(danhsachplaylistAdapter);

            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private  void init()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play lists");
        toolbar.setTitleTextColor(R.color.purple_500);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void anhxa()
    {
        toolbar=findViewById(R.id.toolbardanhsachplaylist);
        recyclerView=findViewById(R.id.recyclerviewdanhsachplaylist);
    }

}