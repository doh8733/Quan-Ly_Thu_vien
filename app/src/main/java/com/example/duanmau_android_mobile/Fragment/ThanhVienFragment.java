package com.example.duanmau_android_mobile.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_android_mobile.DAo.ThanhVienDAO;
import com.example.duanmau_android_mobile.R;
import com.example.duanmau_android_mobile.Adapter.ThanhVienAdapter;
import com.example.duanmau_android_mobile.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ThanhVienFragment extends Fragment {
    ThanhVienDAO thanhVienDAO;
    ArrayList<ThanhVien> listThanhVien = new ArrayList<>();
    private RecyclerView rcvThanhVien;
    private FloatingActionButton fabThemThanhVien;
    ThanhVienAdapter thanhVienAdapter;
    private EditText edIDThanhVien;
    private EditText edTenThanhVien;
    private EditText edNamSinh;
    private Button btnAdd;
    private Button btnDong;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
   return view;
    }

    @Override
    public void onViewCreated(
            @NonNull View view, @Nullable
            Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcvThanhVien = (RecyclerView) view.findViewById(R.id.rcvThanhVien);
        fabThemThanhVien = (FloatingActionButton) view.findViewById(R.id.fabThemThanhVien);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvThanhVien.setLayoutManager(layoutManager);
        thanhVienDAO = new ThanhVienDAO(getContext());
        listThanhVien = thanhVienDAO.getAllThanhVien();

        thanhVienAdapter = new ThanhVienAdapter(listThanhVien);
        rcvThanhVien.setAdapter(thanhVienAdapter);



        fabThemThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogThem();
            }
        });


    }
    private void DialogThem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater =this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add,null);
        builder.setView(view);
        Dialog dialog = builder.create();

        edIDThanhVien = (EditText) view.findViewById(R.id.edIDThanhVien);
        edTenThanhVien = (EditText) view.findViewById(R.id.edTenThanhVien);
        edNamSinh = (EditText)view. findViewById(R.id.edNamSinh);
        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        btnDong = (Button) view.findViewById(R.id.btnDong);
        edIDThanhVien.setEnabled(false);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int nam = calendar.get(Calendar.YEAR);
        int thang = calendar.get(Calendar.MONTH);
        int ngay = calendar.get(Calendar.DAY_OF_MONTH);
        edNamSinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        edNamSinh.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //  int ma = Integer.parseInt(edIDThanhVien.getText().toString());
                String tenTV = edTenThanhVien.getText().toString();
                String date = edNamSinh.getText().toString();
                ThanhVien thanhVien = new ThanhVien();
                thanhVien.setHoTenTV(tenTV);
                thanhVien.setNamSinh(date);
                if (tenTV.length()==0&&date.length()==0){
                    Toast.makeText(getContext(), "khong duoc de trong thong tin", Toast.LENGTH_SHORT).show();
                }else {
                    ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
                    long check = thanhVienDAO.insertThanhVien(thanhVien);
                    if (check > 0){
                        Toast.makeText(getContext(), "Them Thanh Cong", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        listThanhVien.clear();
                        listThanhVien.addAll(thanhVienDAO.getAllThanhVien());
                        thanhVienAdapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(getContext(), "Them Khong Thanh Cong", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}