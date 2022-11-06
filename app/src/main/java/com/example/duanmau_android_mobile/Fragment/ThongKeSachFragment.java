package com.example.duanmau_android_mobile.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duanmau_android_mobile.Adapter.ThongKeTop10Adapter;
import com.example.duanmau_android_mobile.DAo.SachDAO;
import com.example.duanmau_android_mobile.DAo.ThongKeDAO;
import com.example.duanmau_android_mobile.Database.DBHelper;
import com.example.duanmau_android_mobile.R;
import com.example.duanmau_android_mobile.model.Top10Sach;

import java.util.ArrayList;
import java.util.List;


public class ThongKeSachFragment extends Fragment {
    private RecyclerView rcViewThongke;

    private List<Top10Sach> topList;
    ThongKeTop10Adapter thongKeTop10Adapter;
    DBHelper dbHelper;
    ThongKeDAO thongKeDAO;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thong_ke_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcViewThongke = (RecyclerView) view.findViewById(R.id.rcViewThongke);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcViewThongke.setLayoutManager(linearLayoutManager);
        thongKeDAO = new ThongKeDAO(getContext());
        topList = thongKeDAO.GetTop();

        thongKeTop10Adapter = new ThongKeTop10Adapter(topList);
        rcViewThongke.setAdapter(thongKeTop10Adapter);
//        SachDAO sachDAO = new SachDAO(getContext());
//        for (int i = 0; i < sachDAO.getAllSach().size(); i++) {
//            Toast.makeText(getContext(), ""+sachDAO.getAllSach().get(i).toString(), Toast.LENGTH_SHORT).show();
//            Log.e("huy",sachDAO.getAllSach().get(i).toString());
//            Log.e("aaa",sachDAO.getId(sachDAO.getAllSach().get(i).getMaSach()+"").getTenSach());
//            Log.e("bbb",thongKeDAO.GetTop().get(i).getTenSach());
//        }
    }
}