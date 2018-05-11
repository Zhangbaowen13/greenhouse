package com.example.pengpeng;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/5/4 0004.
 */

public class ZhuanjiabanFragment extends Fragment {
   private List<String> a=new ArrayList();
    private View view;
    private PendingIntent sender;
   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intent intent = new Intent(getContext(), AlarmCloseShebeiReceiver.class);
        sender = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
        view = inflater.inflate(R.layout.content_frag, container, false);
        //      inita();
        RecyclerView xuanxaingRecycler = (RecyclerView) view.findViewById(R.id.xuanxiang_recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(StaggeredGridLayoutManager.HORIZONTAL);
        xuanxaingRecycler.setLayoutManager(layoutManager);
        //获取activity传来的数据
        Bundle bundle = getArguments();
        if (bundle != null) {
            String greenhouseID = bundle.getString("greenhouseID");
            String userId = bundle.getString("userID");
            //           ContentFragment.ShebeiAdapter adapter=new ContentFragment.ShebeiAdapter(getSheBei(greenhouseID));
            //         xuanxaingRecycler.setAdapter(adapter);
        }
    }*/
}
