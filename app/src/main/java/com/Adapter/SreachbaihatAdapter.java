package com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Activity.MainActivity_phatnhac;
import com.Model.BatHatHot;
import com.Service.APIservice;
import com.Service.Dataservice;
import com.example.kmusics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SreachbaihatAdapter extends RecyclerView.Adapter<SreachbaihatAdapter.Viewholer>{
    Context context;
    ArrayList<BatHatHot> batHatHotArrayList;

    public SreachbaihatAdapter(Context context, ArrayList<BatHatHot> batHatHotArrayList) {
        this.context = context;
        this.batHatHotArrayList = batHatHotArrayList;
    }

    @NonNull
    @Override

    public Viewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.dong_sreachbaihat,parent,false);
        return new Viewholer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholer holder, int position) {
        BatHatHot batHatHot=batHatHotArrayList.get(position);
        holder.txttenbaihat.setText(batHatHot.getTenBaiHat());
        holder.txtcasi.setText(batHatHot.getCaSi());
        Picasso.get().load(batHatHot.getHinhBaiHat()).into(holder.imgbaihat);


    }

    @Override
    public int getItemCount() {
        return batHatHotArrayList.size();
    }

    public class Viewholer extends RecyclerView.ViewHolder {
        TextView txttenbaihat,txtcasi;
        ImageView imgbaihat,imgluotthich;
        public Viewholer(@NonNull View itemView) {
            super(itemView);
            txttenbaihat=itemView.findViewById(R.id.textviewsreachtenbaihat);
            txtcasi=itemView.findViewById(R.id.textviewsreachtencasi);
            imgbaihat=itemView.findViewById(R.id.imgsreachbaihat);
            imgluotthich=itemView.findViewById(R.id.imgsreachlovebaihat);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, MainActivity_phatnhac.class);
                    intent.putExtra("cakhuc",batHatHotArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice= APIservice.Getservice();
                    Call<String> callback=dataservice.UpdateLuotThich("1",batHatHotArrayList.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua=response.body();
                            if(ketqua.equals("Success"))
                            {
                                Toast.makeText(context,"da ban da thich",Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(context,"thich that bai",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });
        }
    }
}
