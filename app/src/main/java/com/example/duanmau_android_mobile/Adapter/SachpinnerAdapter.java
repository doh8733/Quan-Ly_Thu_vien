package com.example.duanmau_android_mobile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duanmau_android_mobile.R;
import com.example.duanmau_android_mobile.model.Sach;
import com.example.duanmau_android_mobile.model.ThuThu;

import java.util.List;

public class SachpinnerAdapter extends ArrayAdapter<Sach> {
    private  Context context;
    private List<Sach> sachList;
    private TextView tvMaSAch;
    private TextView tvtienThue;

    public SachpinnerAdapter(@NonNull Context context, List<Sach> sachList) {
        super(context, 0,sachList);
        this.context = context;
        this.sachList= sachList;
    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View view = convertView;

        if (view ==null){
           LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           view = inflater.inflate(R.layout.sach_item_view,null);
       }
        final  Sach item= sachList.get(position);
        if (item != null){
            tvtienThue = (TextView) view.findViewById(R.id.tvtienThue);
            tvMaSAch = (TextView) view.findViewById(R.id.tvMaSAch);
            tvMaSAch.setText(item.getMaSach()+". ");
            tvtienThue.setText(item.getGiaThue()+". ");
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sach_item_view,null);
        }final Sach item = sachList.get(position);
        if (item != null) {
            tvtienThue = (TextView) view.findViewById(R.id.tvtienThue);
            tvMaSAch = (TextView) view.findViewById(R.id.tvMaSAch);
            tvMaSAch.setText(item.getMaSach()+". ");
            tvtienThue.setText(item.getGiaThue()+". ");
        }
        return view;
    }
}
