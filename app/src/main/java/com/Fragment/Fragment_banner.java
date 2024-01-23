package com.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.Adapter.BannerAdaper;
import com.Model.QuangCao;
import com.Service.APIservice;
import com.Service.Dataservice;
import com.example.kmusics.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_banner extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdaper bannerAdaper;
    Runnable runnable;
    Handler handler;
    int currenItem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_banner,container,false);
        anhxa();
        GetData();
        return view;
    }

    private void anhxa() {
        viewPager=view.findViewById(R.id.viewpager);
        circleIndicator=view.findViewById(R.id.indicatordefault);

    }

    private void GetData() {
        Dataservice dataservice= APIservice.Getservice();
        Call<List<QuangCao>> callback =dataservice.GetDataBaner();
        callback.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                ArrayList<QuangCao> banners= (ArrayList<QuangCao>) response.body();
                bannerAdaper=new BannerAdaper(getActivity(),banners);
                viewPager.setAdapter(bannerAdaper);
                circleIndicator.setViewPager(viewPager);
                handler=new Handler();
                runnable=new Runnable() {
                    @Override
                    public void run() {
                        currenItem=viewPager.getCurrentItem();
                        currenItem++;
                        if(currenItem>=viewPager.getAdapter().getCount()){
                            currenItem=0;
                        }
                        viewPager.setCurrentItem(currenItem,true);
                        handler.postDelayed(runnable,8000);
                    }
                };
                handler.postDelayed(runnable,8000);
            }
            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {

            }
        });
    }
}
