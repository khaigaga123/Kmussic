package com.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.Model.PlayList;
import com.example.kmusics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlayListAdapter extends ArrayAdapter {
    public PlayListAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }
    class ViewHolder{
        TextView txtenplaylist;
        ImageView imagebackgroud,imageplaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null)
        {
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.dong_playlist,null);
            viewHolder =new ViewHolder();
            viewHolder.txtenplaylist=convertView.findViewById(R.id.textviewtenplaylist);
            viewHolder.imageplaylist=convertView.findViewById(R.id.imageviewplaylist);
            viewHolder.imagebackgroud=convertView.findViewById(R.id.imageviewbackgroudplaylist);
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        PlayList playList= (PlayList) getItem(position);
        Picasso.get().load(playList.getIcon()).into(viewHolder.imagebackgroud);
        Picasso.get().load(playList.getIcon()).into(viewHolder.imageplaylist);
        viewHolder.txtenplaylist.setText(playList.getTen());
        return convertView;
    }
}
