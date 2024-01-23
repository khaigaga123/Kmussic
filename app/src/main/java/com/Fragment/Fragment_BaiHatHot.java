package com.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Adapter.BaiHatHotAdapter;
import com.Model.BatHatHot;
import com.Service.APIservice;
import com.Service.Dataservice;
import com.example.kmusics.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_BaiHatHot extends Fragment {
    View view;
    RecyclerView recyclerView;
    BaiHatHotAdapter baiHatHotAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_baihathot,container,false);
        recyclerView=view.findViewById(R.id.recyclerbaihathot);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice= APIservice.Getservice();
        Call<List<BatHatHot>> callback=dataservice.GetBaiHatHot();
        callback.enqueue(new Callback<List<BatHatHot>>() {
            @Override
            public void onResponse(Call<List<BatHatHot>> call, Response<List<BatHatHot>> response) {
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
