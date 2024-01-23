package com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Activity.MainActivity_dangnhap;
import com.Activity.MainActivity_phatnhac;
import com.Model.BatHatHot;
import com.Service.APIservice;
import com.Service.Dataservice;
import com.example.kmusics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiHatHotAdapter  extends RecyclerView.Adapter<BaiHatHotAdapter.ViewHolder>{
    @NonNull
    Context context;
    ArrayList<BatHatHot> batHatHotArrayList;
    private SharedPreferences pref;

    public BaiHatHotAdapter(@NonNull Context context, ArrayList<BatHatHot> batHatHotArrayList) {
        this.context = context;
        this.batHatHotArrayList = batHatHotArrayList;
        this.pref=context.getSharedPreferences("mypref",Context.MODE_PRIVATE);
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.dong_baihathot,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            BatHatHot batHatHot=batHatHotArrayList.get(position);
            holder.txtten.setText(batHatHot.getTenBaiHat());
            holder.txtcasi.setText(batHatHot.getCaSi());
        Picasso.get().load(batHatHot.getHinhBaiHat()).into(holder.imghinh);
    }
    @Override
    public int getItemCount() {
        return batHatHotArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtten,txtcasi;
        ImageView imghinh,imgluotthich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten=itemView.findViewById(R.id.textviewtenbaihathot);
            txtcasi=itemView.findViewById(R.id.textviewtencasibaihathot);
            imghinh=itemView.findViewById(R.id.imageviewbaihathot);
            imgluotthich=itemView.findViewById(R.id.imageviewluotthich);
            Dataservice dataservice=APIservice.Getservice();
            final String iduser=pref.getString("iduser","0");
            Call<List<BatHatHot>>  callback=dataservice.Getdanhsachbaihatdayeuthich(iduser);
            callback.enqueue(new Callback<List<BatHatHot>>() {
                @Override
                public void onResponse(Call<List<BatHatHot>> call, Response<List<BatHatHot>> response) {
                    ArrayList<BatHatHot> baihatyeuthich= (ArrayList<BatHatHot>) response.body();
                    int position=getPosition();
                    for(int i=0;i<baihatyeuthich.size();i++)
                    {
                        if(position>=0 && imgluotthich.getDrawable().getConstantState().equals(context.getDrawable(R.drawable.iconlove).getConstantState()))
                        {
                            if(batHatHotArrayList.get(getPosition()).getIdBaiHat().equals(baihatyeuthich.get(i).getIdBaiHat()))
                            {
                                imgluotthich.setImageResource(R.drawable.iconloved);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<BatHatHot>> call, Throwable t) {
                    int totalTimeout = 60; // Thời gian chờ tổng cộng là 60 giây
                    call.timeout().timeout(totalTimeout, TimeUnit.SECONDS);

                    // Tái thử lại hoặc thực hiện các xử lý khác nếu cần
                    // Ví dụ: thử lại cuộc gọi API
                    call.clone().enqueue(this);

                }
            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (imgluotthich.getDrawable().getConstantState().equals(context.getDrawable(R.drawable.iconlove).getConstantState())) {
                        imgluotthich.setImageResource(R.drawable.iconloved);
                        Dataservice dataservice = APIservice.Getservice();
                        Call<String> callback = dataservice.UpdateLuotThich("1", batHatHotArrayList.get(getPosition()).getIdBaiHat());
                        Call<String> callbacl1=dataservice.Updatebaihatyeuthich(iduser,batHatHotArrayList.get(getPosition()).getIdBaiHat());
                        callbacl1.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String ketqua = response.body();
                                if (ketqua.equals("Success")) {
                                    Toast.makeText(context, "da ban da thich", Toast.LENGTH_LONG).show();
                                    Call<List<BatHatHot>>  callagain=dataservice.Getdanhsachbaihatdayeuthich(iduser);
                                    callagain.enqueue(new Callback<List<BatHatHot>>() {
                                        @Override
                                        public void onResponse(Call<List<BatHatHot>> call, Response<List<BatHatHot>> response) {
                                            ArrayList<BatHatHot> baihatyeuthich= (ArrayList<BatHatHot>) response.body();
                                            int position=getPosition();

                                        }

                                        @Override
                                        public void onFailure(Call<List<BatHatHot>> call, Throwable t) {

                                        }
                                    });
                                } else {
                                    Toast.makeText(context, "thich that bai", Toast.LENGTH_LONG).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });

                    }
                    else
                    {
                        imgluotthich.setImageResource(R.drawable.iconlove);
                        Dataservice dataservice = APIservice.Getservice();
                        Call<String> callback1=dataservice.Xoabaihatyeuthich(iduser,batHatHotArrayList.get(getPosition()).getIdBaiHat());
                        callback1.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Toast.makeText(context, "da ban xoa bai hat yeu thich", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                        Call<String> callback=dataservice.GiamLuotThich("1", batHatHotArrayList.get(getPosition()).getIdBaiHat());
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String ketqua = response.body();
                                if (ketqua.equals("Success")) {
                                    Toast.makeText(context, "da ban bo thich", Toast.LENGTH_LONG).show();
                                    Call<List<BatHatHot>>  callagain=dataservice.Getdanhsachbaihatdayeuthich(iduser);
                                    callagain.enqueue(new Callback<List<BatHatHot>>() {
                                        @Override
                                        public void onResponse(Call<List<BatHatHot>> call, Response<List<BatHatHot>> response) {
                                            ArrayList<BatHatHot> baihatyeuthich= (ArrayList<BatHatHot>) response.body();
                                            int position=getPosition();

                                        }

                                        @Override
                                        public void onFailure(Call<List<BatHatHot>> call, Throwable t) {

                                        }
                                    });
                                } else {
                                    Toast.makeText(context, "bo thich that bai", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, MainActivity_phatnhac.class);
                    intent.putExtra("cakhuc",batHatHotArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
