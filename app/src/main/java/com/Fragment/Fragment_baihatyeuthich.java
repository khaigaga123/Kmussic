package com.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Adapter.BaiHatHotAdapter;
import com.Model.BatHatHot;
import com.Model.DataUser;
import com.Service.APIservice;
import com.Service.Dataservice;
import com.example.kmusics.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_baihatyeuthich extends Fragment {
    View view;
    RecyclerView recyclerView;
    BaiHatHotAdapter baiHatHotAdapter;
    private SharedPreferences pref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_baihatyeuthich,container,false);
        recyclerView=view.findViewById(R.id.recyclerbaihatyeuthich);
        pref=getContext().getSharedPreferences("mypref", Context.MODE_PRIVATE);
        GetData();
        return view;
    }

    private void GetData() {
        final String iduser=pref.getString("iduser","0");
        Dataservice dataservice= APIservice.Getservice();
        Call<List<BatHatHot>> callback=dataservice.Getdanhsachbaihatdayeuthich(iduser);
        callback.enqueue(new Callback<List<BatHatHot>>() {
            @Override
            public void onResponse(Call<List<BatHatHot>> call, Response<List<BatHatHot>> response) {
                ArrayList<BatHatHot> batHatHotArrayList= (ArrayList<BatHatHot>) response.body();
                ArrayList<BatHatHot> arrayList= (ArrayList<BatHatHot>) response.body();
                baiHatHotAdapter=new BaiHatHotAdapter(getActivity(),arrayList);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(baiHatHotAdapter);
            }

            @Override
            public void onFailure(Call<List<BatHatHot>> call, Throwable t) {

            }
        });

    }

}
