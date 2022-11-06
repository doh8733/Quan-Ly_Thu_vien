package com.example.duanmau_android_mobile.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.duanmau_android_mobile.DAo.ThongKeDAO;
import com.example.duanmau_android_mobile.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DoanhThuFragment extends Fragment {

    private Button btntungay;
    private Button btnDenNgay;
    private EditText edTuNgay;
    private EditText edDenNgay;
    private TextView tvDoanhThu;
    private Button btnDoanhThu;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int ngay,thang,nam;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doanh_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        edTuNgay = (EditText) view.findViewById(R.id.edTuNgay);
        edDenNgay = (EditText) view.findViewById(R.id.edDenNgay);
        tvDoanhThu = (TextView) view.findViewById(R.id.tvDoanhThu);
        btnDoanhThu = (Button) view.findViewById(R.id.btnDoanhThu);


//admin

        Calendar calendar = Calendar.getInstance();
        nam = calendar.get(Calendar.YEAR);
        thang = calendar.get(Calendar.MONTH);
        ngay = calendar.get(Calendar.DAY_OF_MONTH);
        edTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        edTuNgay.setText(sdf.format(calendar.getTime()));
                    }
                },nam,thang,ngay);
                datePickerDialog.show();
            }
        });
        edDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        edDenNgay.setText(sdf.format(calendar.getTime()));

                    }
                },nam,thang,ngay);
                datePickerDialog.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = edTuNgay.getText().toString();
                String denNgay = edDenNgay.getText().toString();
                ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
                tvDoanhThu.setText("Doanh Thu"+thongKeDAO.getDoanhThu(tuNgay,denNgay)+"VND");
            }
        });

    }
}