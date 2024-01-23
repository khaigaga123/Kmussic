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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHoler>{

    Context context;
    ArrayList<Album> albumArrayList;

    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_album,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        Album album=albumArrayList.get(position);
        holder.txttenalbum.setText(album.getTenAlbum());
        holder.txttencasialbum.setText(album.getTenCasi());
        Picasso.get().load(album.getHinhAlbum()).into(holder.imghinhalbum);
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        ImageView imghinhalbum;
        TextView txttenalbum,txttencasialbum;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            imghinhalbum=itemView.findViewById(R.id.imageviewalbum);
            txttenalbum=itemView.findViewById(R.id.textviewtenalbum);
            txttencasialbum=itemView.findViewById(R.id.textviewtencasialbum);
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
