package com.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Model.DataUser;
import com.Service.APIservice;
import com.Service.Dataservice;
import com.example.kmusics.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_dangnhap extends AppCompatActivity {

    Button bt,btdangky;
    EditText taikhoan,matkhau;
    ArrayList<DataUser> dataUserArrayList;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dangnhap);
        bt=findViewById(R.id.buttondangnhap);
        btdangky=findViewById(R.id.buttondangky);
        taikhoan=findViewById(R.id.editTextTextusername);
        matkhau=findViewById(R.id.editTextTextmatkhau);
        Dataservice dataservice=APIservice.Getservice();
        Call<List<DataUser>> callback=dataservice.GetDataUser();
        callback.enqueue(new Callback<List<DataUser>>() {
            @Override
            public void onResponse(Call<List<DataUser>> call, Response<List<DataUser>> response) {
                dataUserArrayList= (ArrayList<DataUser>) response.body();
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int dem=0;
                        if(taikhoan.getText().equals("") || matkhau.getText().equals(""))
                        {
                            Toast.makeText(MainActivity_dangnhap.this,"Vui lòng nhập đủ thông tin !!",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            for(int i=0;i<dataUserArrayList.size();i++)
                            {
                                if(dataUserArrayList.get(i).getTaiKhoan().equals(taikhoan.getText().toString()) && dataUserArrayList.get(i).getMatKhau().equals(matkhau.getText().toString()))
                                {
                                    Intent intent = new Intent(MainActivity_dangnhap.this, MainActivity.class);
                                    SharedPreferences sharedPreferences=getSharedPreferences("mypref",MODE_PRIVATE);
                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    editor.putString("iduser",dataUserArrayList.get(i).getIdUser());
                                    editor.apply();
                                    // Bắt đầu hoạt động mới
                                    startActivity(intent);
                                    break;
                                }
                                else if(i==(dataUserArrayList.size()-1))
                                {
                                    Toast.makeText(MainActivity_dangnhap.this,"Sai tài khoản hoặc mật khẩu vui lòng nhập lại",Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                    }
                });
            }

            @Override
            public void onFailure(Call<List<DataUser>> call, Throwable t) {

            }
        });
        btdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_dangnhap.this, MainActivity_dangky.class);


                // Bắt đầu hoạt động mới
                startActivity(intent);
            }
        });

    }

}