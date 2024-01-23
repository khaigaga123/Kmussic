package com.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.Adapter.AllAlbumAdapter;
import com.Model.Album;
import com.Service.APIservice;
import com.Service.Dataservice;
import com.example.kmusics.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_danhsachtatcaalbum extends AppCompatActivity {

    Toolbar toolbartatcaalbum;
    RecyclerView recyclerViewtatcaalbum;
    AllAlbumAdapter allAlbumAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danhsachtatcaalbum);
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice= APIservice.Getservice();
        Call<List<Album>> callback=dataservice.Getdanhsachalbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList= (ArrayList<Album>) response.body();
                allAlbumAdapter=new AllAlbumAdapter(MainActivity_danhsachtatcaalbum.this,albumArrayList);
                recyclerViewtatcaalbum.setLayoutManager(new GridLayoutManager(MainActivity_danhsachtatcaalbum.this,2));
                recyclerViewtatcaalbum.setAdapter(allAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init()
    {
        recyclerViewtatcaalbum=findViewById(R.id.recycleviewalbum);
        toolbartatcaalbum=findViewById(R.id.toolbartatcaalbum);
        setSupportActionBar(toolbartatcaalbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả các album");
        toolbartatcaalbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}