package com.example.duanmau_android_mobile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_android_mobile.R;
import com.example.duanmau_android_mobile.model.Top10Sach;

import java.util.ArrayList;
import java.util.List;

public class ThongKeTop10Adapter extends RecyclerView.Adapter<ThongKeTop10Adapter.ThongKeTop10ViewHolder> {
    private Context context;
    private List<Top10Sach> top10SachArrayList;

    public ThongKeTop10Adapter(Context context,List<Top10Sach> top10SachArrayList) {
        this.context = context;

    }

    public ThongKeTop10Adapter(List<Top10Sach> topList) {
        this.top10SachArrayList = topList;
    }


    @NonNull
    @Override
    public ThongKeTop10Adapter.ThongKeTop10ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.thong_ke_top10_row,parent,false);
        ThongKeTop10ViewHolder thongKeTop10ViewHolder = new ThongKeTop10ViewHolder(view);
        return thongKeTop10ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeTop10Adapter.ThongKeTop10ViewHolder holder, int position) {
        final  Top10Sach top = top10SachArrayList.get(position);
        if (top != null) {
            holder.tvSoluong.setText("So Luong: " + String.valueOf(top.getSoLuong()));
            holder.tvTenSach.setText("Ten Sach: " + top.getTenSach());
        }
    }

    @Override
    public int getItemCount() {
        return top10SachArrayList.size();
    }

    public class ThongKeTop10ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenSach;
        private TextView tvSoluong;
        public ThongKeTop10ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSach = (TextView) itemView.findViewById(R.id.tvTenSach);
            tvSoluong = (TextView) itemView.findViewById(R.id.tvSoluong);


        }
    }
}
