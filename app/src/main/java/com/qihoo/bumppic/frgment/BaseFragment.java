package com.qihoo.bumppic.frgment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qihoo.bumppic.R;
import com.qihoo.bumppic.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hacker on 16/8/27.
 */
public class BaseFragment extends Fragment implements AdapterView.OnItemClickListener {
    private List<Integer> datas;
    PullToRefreshListView ptrListView;
    private Context mContext;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null) {
            view = inflater.inflate(R.layout.base_fragment_layout,container,false);     
        }else if(view.getParent()!=null){
            ((ViewGroup)view.getParent()).removeView(view);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        setData();
        setView(view);

    }
    int[] imgs = new int[]{R.drawable.background_picture1,R.drawable.background_picture3,R.drawable.background_picture4,
            R.drawable.background_picture6,R.drawable.background_picture8};

    private void setData(){
        datas = new ArrayList<Integer>();
        for (int i=0;i<imgs.length;i++){
            datas.add(imgs[i]);
        }
    }

    private void setView(View view) {
        ptrListView = (PullToRefreshListView)view.findViewById(R.id.pulltofreshlistview_home);
        ptrListView.setAdapter(new HomeAdapter(mContext,datas));
        ptrListView.setMode(PullToRefreshBase.Mode.BOTH);
        ptrListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onDestroy() {
        if (view != null && view.getview() != null) {
            ((ViewGroup) view.getview()).removeView(view);
        }
        super.onDestroy();
    }
}
