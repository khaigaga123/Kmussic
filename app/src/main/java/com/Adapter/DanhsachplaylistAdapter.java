package com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Activity.MainActivity_danhsachbaihat;
import com.Activity.MainActivity_danhsachplaylist;
import com.Model.PlayList;
import com.example.kmusics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachplaylistAdapter extends RecyclerView.Adapter<DanhsachplaylistAdapter.ViewHolder>{
        Context context;
        ArrayList<PlayList> playListArrayList;

    public DanhsachplaylistAdapter(Context context, ArrayList<PlayList> playListArrayList) {
        this.context = context;
        this.playListArrayList = playListArrayList;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  =LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_danh_sach_playlsit,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList playList=playListArrayList.get(position);
        Picasso.get().load(playList.getIcon()).into(holder.imghinhnen);
        holder.txttenplaylist.setText(playList.getTen());
    }

    @Override
    public int getItemCount() {
        return playListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imghinhnen;
        TextView txttenplaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhnen=itemView.findViewById(R.id.imageviewdanhsachplaylist);
            txttenplaylist=itemView.findViewById(R.id.textviewtendanhsachcacplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, MainActivity_danhsachbaihat.class);
                    intent.putExtra("itemplaylist",playListArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
