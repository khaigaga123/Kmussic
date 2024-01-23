package com.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kmusics.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_dianhac extends Fragment {
    View view;
    CircleImageView circleImageView;
    ObjectAnimator objectAnimator;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_dianhac,container,false);
        circleImageView=view.findViewById(R.id.imageviewcricle);
        objectAnimator=ObjectAnimator.ofFloat(circleImageView,"rotation",0f,360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
        return view;
    }
    public void Playnhac(String hinhanh) {
        Picasso.get().load(hinhanh).into(circleImageView);
    }
}
