package com.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Adapter.DanhsachtheloaitheochudeAdapter;
import com.Service.APIservice;
import com.Service.Dataservice;
import com.example.kmusics.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_dangky extends AppCompatActivity {

    TextView txtdktk,txtdkmk,txtnhaplaimk;
    Button btdk;
    EditText tk,mk,nhaplaimk;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dangky);
        txtdktk=findViewById(R.id.txttaikhoandk);
        txtdkmk=findViewById(R.id.txtmatkhaudk);
        txtnhaplaimk=findViewById(R.id.txtnhaplaimk);
        btdk=findViewById(R.id.btdk);
        tk=findViewById(R.id.editTexttk);
        mk=findViewById(R.id.editTextmk);
        nhaplaimk=findViewById(R.id.editTextnhaplaimk);
        btdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan=tk.getText().toString();
                String matkhau=mk.getText().toString();
                String nhaplai=nhaplaimk.getText().toString();
                if(!matkhau.equals(nhaplai))
                {
                    Toast.makeText(MainActivity_dangky.this,"Mật khẩu không giống nhau vui lòng nhập lại !",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Dataservice dataservice= APIservice.Getservice();
                    Call<String> callback=dataservice.RegisterUser(taikhoan,matkhau);
                    Toast.makeText(MainActivity_dangky.this,"Đăng ký thành công !",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity_dangky.this, MainActivity_dangnhap.class);


                    // Bắt đầu hoạt động mới
                    startActivity(intent);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }

            }
        });
    }
}