package com.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.Adapter.BaiHatHotAdapter;
import com.Adapter.DanhsachbaihatAdapter;
import com.Model.Album;
import com.Model.BatHatHot;
import com.Model.PlayList;
import com.Model.QuangCao;
import com.Model.TheLoai;
import com.Service.APIservice;
import com.Service.Dataservice;
import com.example.kmusics.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_danhsachbaihat extends AppCompatActivity {

    QuangCao quangCao;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsach;
    FloatingActionButton floatingActionButton;
    ImageView imgdanhsachcakhuc;
    ArrayList<BatHatHot> arrayListbaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    PlayList playList;
    TheLoai theLoai;
    Album album;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danhsachbaihat);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        anhxa();
        init();
        if(quangCao!=null && !quangCao.getTenBaiHat().equals(""))
        {
            setValueInView(quangCao.getTenBaiHat(),quangCao.getHinhAnhBaiHat());
            getDataQuangcao(quangCao.getIdQuangCao());
        }
        if(playList!=null && !playList.getTen().equals(""))
        {
            setValueInView(playList.getTen(),playList.getIcon());
            GetDataPlaylist(playList.getIdPlayList());
        }
        if(theLoai!=null && !theLoai.getTenTheLoai().equals(""))
        {
            setValueInView(theLoai.getTenTheLoai(),theLoai.getHinhTheLoai());
            GetDataTheLoai(theLoai.getIdTheLoai());
        }
        if(album!=null && !album.getTenAlbum().equals(""))
        {
            setValueInView(album.getTenAlbum(),album.getHinhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
    }

    private void GetDataAlbum(String idalbum) {
        Dataservice dataservice=APIservice.Getservice();
        Call<List<BatHatHot>> callbacl=dataservice.Getdanhsachbaihattheoalbum(idalbum);
        callbacl.enqueue(new Callback<List<BatHatHot>>() {
            @Override
            public void onResponse(Call<List<BatHatHot>> call, Response<List<BatHatHot>> response) {
                arrayListbaihat=(ArrayList<BatHatHot>) response.body();
                danhsachbaihatAdapter=new DanhsachbaihatAdapter(MainActivity_danhsachbaihat.this,arrayListbaihat);
                recyclerViewdanhsach.setLayoutManager(new LinearLayoutManager(MainActivity_danhsachbaihat.this));
                recyclerViewdanhsach.setAdapter(danhsachbaihatAdapter);
                evenclick();
            }

            @Override
            public void onFailure(Call<List<BatHatHot>> call, Throwable t) {

            }
        });
    }

    private void GetDataTheLoai(String idtheloai)
    {
        Dataservice dataservice=APIservice.Getservice();
        Call<List<BatHatHot>> callback=dataservice.Getdanhsachbaihattheoptheloai(idtheloai);
        callback.enqueue(new Callback<List<BatHatHot>>() {
            @Override
            public void onResponse(Call<List<BatHatHot>> call, Response<List<BatHatHot>> response) {

                arrayListbaihat=(ArrayList<BatHatHot>) response.body();
                danhsachbaihatAdapter=new DanhsachbaihatAdapter(MainActivity_danhsachbaihat.this,arrayListbaihat);
                recyclerViewdanhsach.setLayoutManager(new LinearLayoutManager(MainActivity_danhsachbaihat.this));
                recyclerViewdanhsach.setAdapter(danhsachbaihatAdapter);
                evenclick();
            }

            @Override
            public void onFailure(Call<List<BatHatHot>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlaylist(String idplaylist) {
        Dataservice dataservice=APIservice.Getservice();
        Call<List<BatHatHot>> callback=dataservice.Getdanhsachbaihattheoplaylist(idplaylist);
        callback.enqueue(new Callback<List<BatHatHot>>() {
            @Override
            public void onResponse(Call<List<BatHatHot>> call, Response<List<BatHatHot>> response) {
                arrayListbaihat=(ArrayList<BatHatHot>) response.body();
                danhsachbaihatAdapter=new DanhsachbaihatAdapter(MainActivity_danhsachbaihat.this,arrayListbaihat);
                recyclerViewdanhsach.setLayoutManager(new LinearLayoutManager(MainActivity_danhsachbaihat.this));
                recyclerViewdanhsach.setAdapter(danhsachbaihatAdapter);
                evenclick();
            }

            @Override
            public void onFailure(Call<List<BatHatHot>> call, Throwable t) {

            }
        });
    }

    private void getDataQuangcao(String idquangcao) {
        Dataservice dataservice= APIservice.Getservice();
        Call<List<BatHatHot>> callback=dataservice.Getdanhsachbaihattheoquangcao(idquangcao);
        callback.enqueue(new Callback<List<BatHatHot>>() {
            @Override
            public void onResponse(Call<List<BatHatHot>> call, Response<List<BatHatHot>> response) {
                arrayListbaihat= (ArrayList<BatHatHot>) response.body();
                danhsachbaihatAdapter=new DanhsachbaihatAdapter(MainActivity_danhsachbaihat.this,arrayListbaihat);
                recyclerViewdanhsach.setLayoutManager(new LinearLayoutManager(MainActivity_danhsachbaihat.this));
                recyclerViewdanhsach.setAdapter(danhsachbaihatAdapter);
                evenclick();
            }
            @Override
            public void onFailure(Call<List<BatHatHot>> call, Throwable t) {

            }
        });
    }
    private class LoadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream input = connection.getInputStream();
                return BitmapFactory.decodeStream(input);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                // Đặt background cho collapsingToolbarLayout
                BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), result);
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        }
    }

    private void setValueInView(String ten,String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        new LoadImageTask().execute(hinh);
        Picasso.get().load(hinh).into(imgdanhsachcakhuc);
    }

    private void init()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);

    }
    private void anhxa() {
        coordinatorLayout=findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout=findViewById(R.id.collapsingtoolbar);
        toolbar=findViewById(R.id.toolbardanhsach);
        recyclerViewdanhsach=findViewById(R.id.recyclerviewdanhsachbaihat);
        floatingActionButton=findViewById(R.id.floatingactionbuton);
        imgdanhsachcakhuc=findViewById(R.id.imagedanhsachcakhuc);
    }

    private void DataIntent() {
        Intent intent=getIntent();
        if(intent!=null)
        {
            if (intent.hasExtra("banner")) {
                    quangCao= (QuangCao) intent.getSerializableExtra("banner");

            }
            if(intent.hasExtra("itemplaylist"))
            {
                    playList= (PlayList) intent.getSerializableExtra("itemplaylist");
            }
            if(intent.hasExtra("idtheloai"))
            {
                    theLoai= (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if(intent.hasExtra("album"))
            {
                album= (Album) intent.getSerializableExtra("album");

            }
        }
    }
    private void evenclick()
    {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_danhsachbaihat.this, MainActivity_phatnhac.class);
                intent.putExtra("cacbaihat",arrayListbaihat);
                startActivity(intent);
            }
        });
    }
}