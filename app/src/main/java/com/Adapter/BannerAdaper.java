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
import androidx.viewpager.widget.PagerAdapter;

import com.Activity.MainActivity_danhsachbaihat;
import com.Model.QuangCao;
import com.example.kmusics.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BannerAdaper extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> arrayListbanner;

    public BannerAdaper(Context context, ArrayList<QuangCao> arrayListbanner) {
        this.context = context;
        this.arrayListbanner = arrayListbanner;
    }

    public int getCount() {
        return arrayListbanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_banner,null);
        ImageView imageViewbackgroudbanner=view.findViewById(R.id.imagebackgroudbanner);
        ImageView imageViewsongbanner=view.findViewById(R.id.imageviewbanner);
        TextView txttillersongabanner=view.findViewById(R.id.textviewtillebannerbaihat);
        TextView txtnoidung=view.findViewById(R.id.textviewnoidung);
        Picasso.get().load(arrayListbanner.get(position).getHinhAnh()).into(imageViewbackgroudbanner);
        Picasso.get().load(arrayListbanner.get(position).getHinhAnhBaiHat()).into(imageViewsongbanner);
        txttillersongabanner.setText(arrayListbanner.get(position).getTenBaiHat());
        txtnoidung.setText(arrayListbanner.get(position).getNoiDung());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MainActivity_danhsachbaihat.class);
                intent.putExtra("banner",arrayListbanner.get(position));
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
