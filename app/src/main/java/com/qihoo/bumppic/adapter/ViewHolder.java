package com.qihoo.bumppic.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hacker on 16/8/26.
 */
public class ViewHolder {
    private SparseArray<View>mView;
    private int mPosition;
    private View mConvertView;

    ViewHolder(Context context, ViewGroup parent,int layoutId,int position){
        this.mPosition = position;
        this.mView = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(this);
    }

    static ViewHolder get(Context c,View convertView,ViewGroup parent,int layoutId,int position){
        if (convertView==null){
            return new ViewHolder(c,parent,layoutId,position);
        }else{
            ViewHolder holder=(ViewHolder)convertView.getTag();
            holder.mPosition=position;
            return holder;
        }
    }

    public <T extends View> T getView(int viewId){
        View view = mView.get(viewId);
        if (view==null){
            view = mConvertView.findViewById(viewId);
            mView.put(viewId,view);
        }
        return (T)view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public void setText(int viewId,String text){
        TextView t = getView(viewId);
        t.setText(text);
    }

    public int getPosition() {
        return mPosition;
    }
}
