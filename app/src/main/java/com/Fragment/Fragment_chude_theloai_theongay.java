package com.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.Activity.MainActivity_danhsachbaihat;
import com.Activity.MainActivity_danhsachtatcachude;
import com.Activity.MainActivity_danhsachtheloaitheochude;
import com.Model.ChuDe;
import com.Model.Chudevatheloai;
import com.Model.PlayList;
import com.Model.TheLoai;
import com.Service.APIservice;
import com.Service.Dataservice;
import com.example.kmusics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_chude_theloai_theongay extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_chudevatheloai,container,false);
        horizontalScrollView=view.findViewById(R.id.HozizontalScrollview);
        txtxemthem=view.findViewById(R.id.txtxemthem);
        txtxemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainActivity_danhsachtatcachude.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice= APIservice.Getservice();
        Call<Chudevatheloai> callback=dataservice.GetChudevaTheloai();
        callback.enqueue(new Callback<Chudevatheloai>() {
            @Override
            public void onResponse(Call<Chudevatheloai> call, Response<Chudevatheloai> response) {
                Chudevatheloai chudevatheloai=response.body();
                final ArrayList<ChuDe> chuDeArrayList=new ArrayList<>();
                chuDeArrayList.addAll(chudevatheloai.getChuDe());
                final ArrayList<TheLoai> TheLoaiArrayList=new ArrayList<>();
                TheLoaiArrayList.addAll(chudevatheloai.getTheLoai());
                LinearLayout linearLayout=new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layout=new LinearLayout.LayoutParams(580,250);
                layout.setMargins(10,20,10,30);
                for(int i=0;i<(chuDeArrayList.size());i++)
                {
                    CardView cardView=new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView=new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(chuDeArrayList.get(i).getHinhChuDe()!=null)
                    {
                        Picasso.get().load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);

                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getActivity(), MainActivity_danhsachtheloaitheochude.class);
                            intent.putExtra("chude",chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                for(int j=0;j<(chuDeArrayList.size());j++)
                {
                    CardView cardView=new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView=new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(TheLoaiArrayList.get(j).getHinhTheLoai()!=null)
                    {
                        Picasso.get().load(TheLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);

                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final  int finalJ=j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getActivity(), MainActivity_danhsachbaihat.class);
                            intent.putExtra("idtheloai",TheLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);

            }

            @Override
            public void onFailure(Call<Chudevatheloai> call, Throwable t) {

            }
        });
    }
}
