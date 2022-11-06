package com.example.duanmau_android_mobile.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.SpinnerAdapter;
//import android.widget.Toast;
//
//import com.example.duanmau_android_mobile.Adapter.LoaiSachAdapter;
//import com.example.duanmau_android_mobile.Adapter.SachAdapter;
//import com.example.duanmau_android_mobile.DAo.LoaiSachDAO;
//import com.example.duanmau_android_mobile.DAo.SachDAO;
//import com.example.duanmau_android_mobile.R;
//import com.example.duanmau_android_mobile.model.LoaiSach;
//import com.example.duanmau_android_mobile.model.Sach;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class SachFragment extends Fragment {
//    List<Sach>saches = new ArrayList<>();
//    List<LoaiSach> loaiSachList = new ArrayList<>();
//    private RecyclerView rcvSach;
//    private FloatingActionButton fabThemSach;
//    private EditText edIDSach;
//    private EditText edTenSach;
//    private EditText edGiathue;
//    private EditText edSpinnerSach;
//    private Spinner spnSach;
//    private Button btnAdd;
//    private Button btnDong;
//    SachAdapter sachAdapter;
//    LoaiSachAdapter loaiSachAdapter;
//    LoaiSachDAO loaiSachDAO;
//    SachDAO sachDAO;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_sach, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        rcvSach = (RecyclerView) view.findViewById(R.id.rcvSach);
//        fabThemSach = (FloatingActionButton) view.findViewById(R.id.fabThemSach);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        rcvSach.setLayoutManager(linearLayoutManager);
//        sachDAO = new SachDAO(getContext());
//        saches = sachDAO.getAllSach();
//
//        sachAdapter = new SachAdapter(getContext(),saches);
//        rcvSach.setAdapter(sachAdapter);
//
//        fabThemSach.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogThemSach();
//            }
//        });
//    }
//    private  void DialogThemSach(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        LayoutInflater inflater = this.getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_add_sach,null);
//        builder.setView(view);
//        Dialog dialog = builder.create();
//
//        edIDSach = (EditText) view.findViewById(R.id.edIDSach);
//        edTenSach = (EditText) view.findViewById(R.id.edTenSach);
//        edGiathue = (EditText) view.findViewById(R.id.edGiathue);
//        edSpinnerSach = (EditText) view.findViewById(R.id.edSpinnerSach);
//        spnSach = (Spinner) view.findViewById(R.id.spnSach);
//        btnAdd = (Button) view.findViewById(R.id.btnAdd);
//        btnDong = (Button) view.findViewById(R.id.btnDong);
//
//        loaiSachDAO = new LoaiSachDAO(getContext());
//        loaiSachList = loaiSachDAO.getAllLoaiSach();
//        loaiSachAdapter = new LoaiSachAdapter(getContext(), (ArrayList<LoaiSach>) loaiSachList);
//        spnSach.setAdapter((SpinnerAdapter) loaiSachAdapter);
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String tenSach = edTenSach.getText().toString();
//                int giathue =Integer.parseInt(edGiathue.getText().toString());
//                int spnSach = Integer.parseInt(edSpinnerSach.getText().toString());
//                Sach sach = new Sach();
//                sach.setTenSach(tenSach);
//                sach.setGiaThue(giathue);
//                sach.setMaLoai(spnSach);
//
//                SachDAO sachDAO = new SachDAO(getContext());
//                long check = sachDAO.insertSach(sach);
//                if (check > 0){
//                    Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
//                    dialog.dismiss();
//                    saches.clear();
//                    saches.addAll(sachDAO.getAllSach());
//                    sachAdapter.notifyDataSetChanged();
//                }else {
//                    Toast.makeText(getContext(), "Them that bai", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//        btnDong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//
//    }
//}