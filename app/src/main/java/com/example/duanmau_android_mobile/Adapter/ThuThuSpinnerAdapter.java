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
import com.example.duanmau_android_mobile.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuSpinnerAdapter extends ArrayAdapter<ThuThu> {
    private  Context context ;
    private TextView tvMaThuThu;
    private TextView tvTenThuThu;
    private List<ThuThu> thuThuSpinnerAdapter;

    public ThuThuSpinnerAdapter(@NonNull Context context, List<ThuThu> thuThuSpinnerAdapter) {
        super(context,0, thuThuSpinnerAdapter);
        this.context = context;
        this.thuThuSpinnerAdapter = thuThuSpinnerAdapter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      View view = convertView;
      if (view == null){
          LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          view = inflater.inflate(R.layout.thuthu_spinner_item,null);
      }
      final  ThuThu item = thuThuSpinnerAdapter.get(position);
      if (item != null){


          tvMaThuThu = (TextView) view.findViewById(R.id.tvMaThuThu);
          tvMaThuThu.setText(item.getMaTT()+". ");
          tvTenThuThu = (TextView) view.findViewById(R.id.tvTenThuThu);
          tvTenThuThu.setText(item.getHoTenTT()+". ");

      }
      return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      View view = convertView;
        if (view == null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.thuthu_spinner_item,null);
        }
        final  ThuThu item = thuThuSpinnerAdapter.get(position);
        if (item != null){


            tvMaThuThu = (TextView) view.findViewById(R.id.tvMaThuThu);
            tvMaThuThu.setText(item.getMaTT()+". ");
            tvTenThuThu = (TextView) view.findViewById(R.id.tvTenThuThu);
            tvTenThuThu.setText(item.getHoTenTT()+". ");

        }
        return view;

    }
}
