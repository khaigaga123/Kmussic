package com.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Adapter.Viewpagerplaylistnhac;
import com.Fragment.Fragment_dianhac;
import com.Fragment.Fragment_play_danhsach_baihat;
import com.Model.BatHatHot;
import com.example.kmusics.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity_phatnhac extends AppCompatActivity {

    Toolbar toolbarplaynhac;
    TextView txttimesong,txtTotalsong;
    SeekBar sktime;
    ImageButton imgplay,imgrepeat,imgnext,imgpre,imgrandom;
    ViewPager viewPagerplaynhac;
    Fragment_dianhac fragmentDianhac;
    Fragment_play_danhsach_baihat fragmentPlayDanhsachBaihat;
    MediaPlayer mediaPlayer;
    int position=0;
    boolean repeat=false;
    boolean checkrandom=false;
    boolean next=false;

    public static ArrayList<BatHatHot> batHatHotArrayList=new ArrayList<>();
    public static Viewpagerplaylistnhac adapternhac;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_phatnhac);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromintent();
        init();
        evenclick();
    }

    private void evenclick() {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapternhac.getItem(1)!=null)
                    if(batHatHotArrayList.size()>0)
                    {
                        fragmentDianhac.Playnhac(batHatHotArrayList.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    }else
                    {
                        handler.postDelayed(this,300);
                    }
            }
        },500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.play);
                }
                else
                {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.pause);
                }

            }
        });
        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repeat==false)
                {
                    if(checkrandom==true)
                    {
                        checkrandom=false;
                        imgrepeat.setImageResource(R.drawable.replayed);
                        imgrandom.setImageResource(R.drawable.random);
                    }
                    imgrepeat.setImageResource(R.drawable.replayed);
                    repeat=true;
                }
                else
                {
                    imgrepeat.setImageResource(R.drawable.replay);
                    repeat=false;
                }
            }
        });
        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkrandom==false)
                {
                    if(repeat==true)
                    {
                        repeat=false;
                        imgrandom.setImageResource(R.drawable.randomed);
                        imgrepeat.setImageResource(R.drawable.replay);
                    }
                    imgrandom.setImageResource(R.drawable.randomed);
                    checkrandom=true;
                }
                else
                {
                    imgrandom.setImageResource(R.drawable.random);
                    checkrandom=false;
                }
            }
        });
        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                    mediaPlayer.seekTo(seekBar.getProgress());

            }
        });
        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(batHatHotArrayList.size()>0)
                {
                    if(mediaPlayer.isPlaying() || mediaPlayer!=null)
                    {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer=null;
                    }
                    if(position<(batHatHotArrayList.size()))
                    {
                        imgplay.setImageResource(R.drawable.pause);
                        position++;
                        if(repeat==true)
                        {
                            if(position==0)
                            {
                                position=batHatHotArrayList.size();
                            }
                            position-=1;
                        }
                        if(checkrandom==true)
                        {
                            Random random=new Random();
                            int index=random.nextInt(batHatHotArrayList.size());
                            if(index==position)
                            {
                                position=index-1;
                            }
                            position=index;
                        }
                        if(position>(batHatHotArrayList.size()-1))
                        {
                            position=0;
                        }
                        new Playmp3().execute(batHatHotArrayList.get(position).getLinkBaiHat());
                        fragmentDianhac.Playnhac(batHatHotArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(batHatHotArrayList.get(position).getTenBaiHat());
                        updatetime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler  handler1=new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);
            }
        });
        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(batHatHotArrayList.size()>0)
                {
                    if(mediaPlayer.isPlaying() || mediaPlayer!=null)
                    {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer=null;
                    }
                    if(position<(batHatHotArrayList.size()))
                    {
                        imgplay.setImageResource(R.drawable.pause);
                        position--;
                        if(position<0)
                        {
                            position=batHatHotArrayList.size()-1;

                        }
                        if(repeat==true)
                        {
                            position+=1;
                        }
                        if(checkrandom==true)
                        {
                            Random random=new Random();
                            int index=random.nextInt(batHatHotArrayList.size());
                            if(index==position)
                            {
                                position=index-1;
                            }
                            position=index;
                        }
                        new Playmp3().execute(batHatHotArrayList.get(position).getLinkBaiHat());
                        fragmentDianhac.Playnhac(batHatHotArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(batHatHotArrayList.get(position).getTenBaiHat());
                        updatetime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler  handler1=new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);
            }
        });
    }

    private void GetDataFromintent() {
        Intent intent=getIntent();
        batHatHotArrayList.clear();
        if(intent!=null) {
            if (intent.hasExtra("cakhuc")) {
                BatHatHot baihat = intent.getParcelableExtra("cakhuc");
                Toast.makeText(this, baihat.getTenBaiHat(), Toast.LENGTH_LONG).show();
                batHatHotArrayList.add(baihat);
            }
            if (intent.hasExtra("cacbaihat")) {
                ArrayList<BatHatHot> mangbaihat = intent.getParcelableArrayListExtra("cacbaihat");
                batHatHotArrayList = mangbaihat;

            }

        }
    }

    private void init()
    {
        toolbarplaynhac=findViewById(R.id.toolbarphatnhac);
        txttimesong=findViewById(R.id.textviewtimesong);
        txtTotalsong=findViewById(R.id.textviewtotaltimesong);
        sktime=findViewById(R.id.seekbarsong);
        imgplay=findViewById(R.id.imagebutonplay);
        imgnext=findViewById(R.id.imagebutonnext);
        imgrepeat=findViewById(R.id.imagebutonrepeat);
        imgpre=findViewById(R.id.imagebutonpreview);
        imgrandom=findViewById(R.id.imagebutonsuffle);
        viewPagerplaynhac=findViewById(R.id.viewpagerphatnhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                batHatHotArrayList.clear();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        fragmentDianhac=new Fragment_dianhac();
        fragmentPlayDanhsachBaihat=new Fragment_play_danhsach_baihat();
        adapternhac=new Viewpagerplaylistnhac(getSupportFragmentManager());
        adapternhac.Addfragmnet(fragmentPlayDanhsachBaihat);
        adapternhac.Addfragmnet(fragmentDianhac);
        viewPagerplaynhac.setAdapter(adapternhac);
        fragmentDianhac= (Fragment_dianhac) adapternhac.getItem(1);
        if(batHatHotArrayList.size()>0)
        {
            getSupportActionBar().setTitle(batHatHotArrayList.get(0).getTenBaiHat());
            new Playmp3().execute(batHatHotArrayList.get(0).getLinkBaiHat());
            imgplay.setImageResource(R.drawable.pause);

        }
    }
    class Playmp3 extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer=new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });

                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mediaPlayer.start();
            TimeSong();
            updatetime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
        txtTotalsong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }
    private void updatetime()
    {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null)
                {
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
                    txttimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next=true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }

            }
        },300);
        Handler handler1=new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next==true)
                {
                    if(position<(batHatHotArrayList.size()))
                    {
                        imgplay.setImageResource(R.drawable.pause);
                        position++;
                        if(repeat==true)
                        {
                            if(position==0)
                            {
                                position=batHatHotArrayList.size();
                            }
                            position-=1;
                        }
                        if(checkrandom==true)
                        {
                            Random random=new Random();
                            int index=random.nextInt(batHatHotArrayList.size());
                            if(index==position)
                            {
                                position=index-1;
                            }
                            position=index;
                        }
                        if(position>(batHatHotArrayList.size()-1))
                        {
                            position=0;
                        }
                        new Playmp3().execute(batHatHotArrayList.get(position).getLinkBaiHat());
                        fragmentDianhac.Playnhac(batHatHotArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(batHatHotArrayList.get(position).getTenBaiHat());

                    }
                imgrepeat.setClickable(false);
                imgnext.setClickable(false);
                Handler  handler1=new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);
                next=false;
                handler1.removeCallbacks(this);
                }
                else
                {
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}