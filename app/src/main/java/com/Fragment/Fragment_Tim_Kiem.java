package com.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Adapter.SreachbaihatAdapter;
import com.Model.BatHatHot;
import com.Service.APIservice;
import com.Service.Dataservice;
import com.example.kmusics.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {
    View view;
    Toolbar toolbar;
    RecyclerView recyclerView;
    TextView textView;
    SreachbaihatAdapter sreachbaihatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_tim_kiem,container,false);
        toolbar=view.findViewById(R.id.toolbarsreach);
        recyclerView=view.findViewById(R.id.recyclerviewsreachbaihat);
        textView=view.findViewById(R.id.textviewkhongcobaihat);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.sreach_view,menu);
        MenuItem menuItem=menu.findItem(R.id.menusreach);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Sreachtukhoa(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    private void Sreachtukhoa(String query)
    {
        Dataservice dataservice= APIservice.Getservice();
        Call<List<BatHatHot>> callback =dataservice.GetSreachBaiHat(query);
        callback.enqueue(new Callback<List<BatHatHot>>() {
            @Override
            public void onResponse(Call<List<BatHatHot>> call, Response<List<BatHatHot>> response) {
                ArrayList<BatHatHot> batHatHotArrayList= (ArrayList<BatHatHot>) response.body();
                if(batHatHotArrayList.size()>0)
                {
                    sreachbaihatAdapter=new SreachbaihatAdapter(getActivity(),batHatHotArrayList);
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(sreachbaihatAdapter);
                    textView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
                else
                {
                    recyclerView.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<List<BatHatHot>> call, Throwable t) {

            }
        });
    }
}
