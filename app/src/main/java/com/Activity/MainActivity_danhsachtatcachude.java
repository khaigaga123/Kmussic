package com.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.Adapter.DanhsachchudeAdapter;
import com.Model.ChuDe;
import com.Service.APIservice;
import com.Service.Dataservice;
import com.example.kmusics.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_danhsachtatcachude extends AppCompatActivity {

    RecyclerView recyclerViewchude;
    Toolbar toolbarchude;
    DanhsachchudeAdapter danhsachchudeAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danhsachtatcachude);
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice= APIservice.Getservice();
        Call<List<ChuDe>> callback=dataservice.Getdanhsachchude();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> chuDeArrayList= (ArrayList<ChuDe>) response.body();
                danhsachchudeAdapter=new DanhsachchudeAdapter(MainActivity_danhsachtatcachude.this,chuDeArrayList);
                recyclerViewchude.setLayoutManager(new GridLayoutManager(MainActivity_danhsachtatcachude.this,1));
                recyclerViewchude.setAdapter(danhsachchudeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewchude=findViewById(R.id.recyclerviewchude);
        toolbarchude=findViewById(R.id.toolbarchude);
        setSupportActionBar(toolbarchude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả chủ đề");
        toolbarchude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}