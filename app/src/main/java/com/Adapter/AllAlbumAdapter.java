package com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Activity.MainActivity_danhsachbaihat;
import com.Model.Album;
import com.example.kmusics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllAlbumAdapter extends RecyclerView.Adapter<AllAlbumAdapter.ViewHolder>{

    Context context;
    ArrayList<Album> albumArrayList;

    public AllAlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_all_album,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album=albumArrayList.get(position);
        Picasso.get().load(album.getHinhAlbum()).into(holder.imageViewallalbum);
        holder.texttenallalbum.setText(album.getTenAlbum());
        holder.textcasiallalbum.setText(album.getTenCasi());

    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewallalbum;
        TextView texttenallalbum,textcasiallalbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewallalbum=itemView.findViewById(R.id.imgallalbum);
            texttenallalbum=itemView.findViewById(R.id.textviewtenallalbum);
            textcasiallalbum=itemView.findViewById(R.id.textviewtencasiallablum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, MainActivity_danhsachbaihat.class);
                    intent.putExtra("album",albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
