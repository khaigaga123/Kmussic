package com.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Activity.MainActivity_phatnhac;
import com.Adapter.PlaynhacAdapter;
import com.example.kmusics.R;

public class Fragment_play_danhsach_baihat extends Fragment {
    View view;
    RecyclerView recyclerView;
    PlaynhacAdapter playnhacAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_play_danhsach_baihat,container,false);
        recyclerView=view.findViewById(R.id.recyclerplaybaihat);
        if(MainActivity_phatnhac.batHatHotArrayList.size()>0) {
            playnhacAdapter = new PlaynhacAdapter(getActivity(), MainActivity_phatnhac.batHatHotArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(playnhacAdapter);
        }
        return view;
    }
}
