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

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHoler>{
    @NonNull
    Context context;
    ArrayList<BatHatHot> arrayListbaihat;

    public DanhsachbaihatAdapter(@NonNull Context context, ArrayList<BatHatHot> arrayListbaihat) {
        this.context = context;
        this.arrayListbaihat = arrayListbaihat;
    }

    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.dong_danhsachbaihat,parent,false);
        return new ViewHoler(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        BatHatHot batHatHot=arrayListbaihat.get(position);
        holder.txttenbaihat.setText(batHatHot.getTenBaiHat());
        holder.txtcasi.setText(batHatHot.getCaSi());
        holder.txtindex.setText(position+1+"");

    }

    @Override
    public int getItemCount() {
        return arrayListbaihat.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        TextView txtindex,txttenbaihat,txtcasi;
        ImageView imgluotthich;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            txttenbaihat=itemView.findViewById(R.id.textviewtenbaihat);
            txtcasi=itemView.findViewById(R.id.textviewtencasi);
            txtindex=itemView.findViewById(R.id.textviewdanhsachindex);
            imgluotthich=itemView.findViewById(R.id.imageviewluotthichdanhsachbaihat);
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice= APIservice.Getservice();
                    Call<String> callback=dataservice.UpdateLuotThich("1",arrayListbaihat.get(getPosition()).getIdBaiHat());
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, MainActivity_phatnhac.class);
                    intent.putExtra("cakhuc",arrayListbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
