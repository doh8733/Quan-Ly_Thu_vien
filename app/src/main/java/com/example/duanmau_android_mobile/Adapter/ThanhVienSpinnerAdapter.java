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
import com.example.duanmau_android_mobile.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {

    private Context context;
    private List<ThanhVien> listThanhVienSpinner;
    TextView tvMaThanhVien, tvTenTv;

    public ThanhVienSpinnerAdapter( Context context, List<ThanhVien> listThanhVienSpinner) {
        super(context, 0, listThanhVienSpinner);
        this.context = context;
        this.listThanhVienSpinner = listThanhVienSpinner;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.thanh_vien_item_spinner, null);
        }
        final ThanhVien item = listThanhVienSpinner.get(position);
        if (item != null) {
            tvMaThanhVien = view.findViewById(R.id.textView_idLoaiSach);
            tvTenTv = view.findViewById(R.id.textView_tenLoaiSach);
            tvTenTv.setText(item.getHoTenTV() + ". ");
            tvMaThanhVien.setText(item.getMaTV() + ". ");
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.thanh_vien_item_spinner, null);
        }
        final ThanhVien item = listThanhVienSpinner.get(position);
        if (item != null) {
            tvMaThanhVien = view.findViewById(R.id.textView_idLoaiSach);
            tvTenTv = view.findViewById(R.id.textView_tenLoaiSach);
            tvTenTv.setText(item.getHoTenTV() + ". ");
            tvMaThanhVien.setText(item.getMaTV() + ". ");
        }

        return view;
    }
}
