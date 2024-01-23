package com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Activity.MainActivity_danhsachtheloaitheochude;
import com.Model.ChuDe;
import com.example.kmusics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachchudeAdapter extends RecyclerView.Adapter<DanhsachchudeAdapter.ViewHolder>{

    Context context;
    ArrayList<ChuDe> chuDeArrayList;

    public DanhsachchudeAdapter(@NonNull Context context, ArrayList<ChuDe> chuDeArrayList) {
        this.context = context;
        this.chuDeArrayList = chuDeArrayList;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_chude,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe=chuDeArrayList.get(position);
        Picasso.get().load(chuDe.getHinhChuDe()).into(holder.imgchude);


    }

    @Override
    public int getItemCount() {
        return chuDeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgchude;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgchude=itemView.findViewById(R.id.imageviewdongchude);
            imgchude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, MainActivity_danhsachtheloaitheochude.class);
                    intent.putExtra("chude",chuDeArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
