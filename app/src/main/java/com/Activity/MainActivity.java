package com.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.Adapter.MainViewPagerAdapter;
import com.Fragment.Fragment_Tim_Kiem;
import com.Fragment.Fragment_Trang_Chu;
import com.Fragment.Fragment_baihatyeuthich;
import com.Model.DataUser;
import com.example.kmusics.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();
    }
    private void init()
    {
        MainViewPagerAdapter mainViewPagerAdapter=new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(),"Trang Chủ");
        mainViewPagerAdapter.addFragment(new Fragment_baihatyeuthich(),"Bài hát đã thích");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(),"Tìm Kiếm");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconloved);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconsearch);
    }
    private void anhxa()
    {
        tabLayout=(TabLayout) findViewById(R.id.myTabLayout);
        viewPager=(ViewPager) findViewById(R.id.myViewPager);
    }
}