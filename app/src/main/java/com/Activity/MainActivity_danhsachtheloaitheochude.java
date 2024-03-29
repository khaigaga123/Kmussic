package com.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.Adapter.DanhsachtheloaitheochudeAdapter;
import com.Model.ChuDe;
import com.Model.TheLoai;
import com.Service.APIservice;
import com.Service.Dataservice;
import com.example.kmusics.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_danhsachtheloaitheochude extends AppCompatActivity {
    ChuDe chuDe;
    Toolbar toolbartheloaitheochude;
    RecyclerView recyclerViewtheloaitheochude;
    DanhsachtheloaitheochudeAdapter danhsachtheloaitheochudeAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danhsachtheloaitheochude);
        GetIntent();
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice= APIservice.Getservice();
        Call<List<TheLoai>> callback=dataservice.Getdanhsachtheloaitheochude(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> theLoaiArrayList= (ArrayList<TheLoai>) response.body();
                danhsachtheloaitheochudeAdapter=new DanhsachtheloaitheochudeAdapter(MainActivity_danhsachtheloaitheochude.this,theLoaiArrayList);
                recyclerViewtheloaitheochude.setLayoutManager(new GridLayoutManager(MainActivity_danhsachtheloaitheochude.this,2));
                recyclerViewtheloaitheochude.setAdapter(danhsachtheloaitheochudeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private  void init()
    {
        recyclerViewtheloaitheochude=findViewById(R.id.recyclerviewtheloaitheochude);
        toolbartheloaitheochude=findViewById(R.id.toolbartheloaitheochude);
        setSupportActionBar(toolbartheloaitheochude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbartheloaitheochude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void GetIntent() {
        Intent intent=getIntent();
        if(intent.hasExtra("chude"))
        {
            chuDe= (ChuDe) intent.getSerializableExtra("chude");
            Toast.makeText(this,chuDe.getTenChuDe(),Toast.LENGTH_LONG).show();
        }
    }

}