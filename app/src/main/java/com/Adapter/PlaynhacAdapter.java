package com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Model.BatHatHot;
import com.example.kmusics.R;

import java.util.ArrayList;

public class PlaynhacAdapter extends RecyclerView.Adapter<PlaynhacAdapter.ViewHolder>{
    Context context;
    ArrayList<BatHatHot> batHatHotArrayList;

    public PlaynhacAdapter(Context context, ArrayList<BatHatHot> batHatHotArrayList) {
        this.context = context;
        this.batHatHotArrayList = batHatHotArrayList;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_play_baihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            BatHatHot batHatHot=batHatHotArrayList.get(position);
            holder.txttenbaihat.setText(batHatHot.getTenBaiHat());
            holder.txttencasi.setText(batHatHot.getCaSi());
            holder.txtindex.setText(position+1+"");
    }

    @Override
    public int getItemCount() {
        return batHatHotArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtindex,txttenbaihat,txttencasi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtindex=itemView.findViewById(R.id.textviewplaynhacindex);
            txttenbaihat=itemView.findViewById(R.id.textviewplaynhactenbaihat);
            txttencasi=itemView.findViewById(R.id.textviewplaynhactencasi);
        }
    }
}
