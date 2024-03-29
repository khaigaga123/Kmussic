package com.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.Activity.MainActivity_danhsachbaihat;
import com.Activity.MainActivity_danhsachplaylist;
import com.Adapter.PlayListAdapter;
import com.Model.PlayList;
import com.Service.APIservice;
import com.Service.Dataservice;
import com.example.kmusics.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_PlayList extends Fragment {
    View view;
    ListView listViewPlayList;
    TextView txtviewPlayList,txtxemthemPlayList;
    PlayListAdapter playListAdapter;
    ArrayList<PlayList> mangplaylist;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_playlist,container,false);
        listViewPlayList=view.findViewById(R.id.ListViewPlayList);
        txtviewPlayList=view.findViewById(R.id.textViewPlayList);
        txtxemthemPlayList=view.findViewById(R.id.textviewmoreplaylist);
        GetData();
        txtxemthemPlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainActivity_danhsachplaylist.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void GetData() {
        Dataservice dataservice= APIservice.Getservice();
        Call<List<PlayList>> callback=dataservice.GetPlayListCurrentDay();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                mangplaylist=(ArrayList<PlayList>) response.body();
                playListAdapter=new PlayListAdapter(getActivity(), android.R.layout.simple_list_item_1,mangplaylist);
                listViewPlayList.setAdapter(playListAdapter);
                setListViewHeightBasedOnChildren(listViewPlayList);
                listViewPlayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(getActivity(), MainActivity_danhsachbaihat.class);
                        intent.putExtra("itemplaylist",mangplaylist.get(position));
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
