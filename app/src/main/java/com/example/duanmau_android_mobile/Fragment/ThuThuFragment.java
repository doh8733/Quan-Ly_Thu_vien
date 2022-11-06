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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_android_mobile.Adapter.ThuThuAdapter;
import com.example.duanmau_android_mobile.DAo.ThuThuDAO;
import com.example.duanmau_android_mobile.R;
import com.example.duanmau_android_mobile.model.ThuThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ThuThuFragment extends Fragment {
    List<ThuThu> listthuThu = new ArrayList<>();
    List<ThuThu> thuThuFind = new ArrayList<>();
    ThuThuAdapter thuThuAdapter;
    private RecyclerView rcvThuThu;
    private FloatingActionButton fabThemThuThu;
    ThuThuDAO thuThuDAO;
    private EditText edFind;
    private Button btnFind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thu_thu, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcvThuThu = (RecyclerView) view.findViewById(R.id.rcvThuThu);
        fabThemThuThu = (FloatingActionButton) view.findViewById(R.id.fabThemThuThu);
        edFind = (EditText) view.findViewById(R.id.edFind);
        btnFind = (Button) view.findViewById(R.id.btnFind);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvThuThu.setLayoutManager(layoutManager);
        thuThuDAO = new ThuThuDAO(getContext());
        listthuThu = thuThuDAO.getAllThuThu();
        thuThuAdapter = new ThuThuAdapter(listthuThu);
        rcvThuThu.setAdapter(thuThuAdapter);


        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = -1;
                String idTTFind = edFind.getText().toString();
                thuThuFind.clear();
                for (int i = 0; i < listthuThu.size(); i++) {
                    if (idTTFind.equalsIgnoreCase(listthuThu.get(i).getMaTT())) {

                        thuThuFind.add(listthuThu.get(i));
                        pos = i;
                    }
                    if (pos != -1) {
                        Toast.makeText(getContext(), "Da Tim thay" + idTTFind, Toast.LENGTH_SHORT).show();
                        thuThuAdapter = new ThuThuAdapter(thuThuFind);
                        rcvThuThu.setAdapter(thuThuAdapter);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        rcvThuThu.setLayoutManager(layoutManager);

                    } else {
                        Toast.makeText(getContext(), "Khong tim thay" + idTTFind, Toast.LENGTH_SHORT).show();
                        thuThuAdapter = new ThuThuAdapter(listthuThu);
                        rcvThuThu.setAdapter(thuThuAdapter);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        rcvThuThu.setLayoutManager(layoutManager);
                    }
                }
            }
        });

        fabThemThuThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogThuThu();
            }
        });

    }

    private EditText edTenThuThu;
    private EditText edMatKhau;
    private Button btnAdd;
    private Button btnDong;
    private EditText edIDThuThu;
    private EditText edtaiKhoanThuThu;

    private void DialogThuThu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_thuthu, null);
        builder.create();
        builder.setView(view);
        Dialog dialog = builder.create();


        edIDThuThu = (EditText) view.findViewById(R.id.edIDThuThu);

        edTenThuThu = (EditText) view.findViewById(R.id.edTenThuThu);
        edMatKhau = (EditText) view.findViewById(R.id.edMatKhau);
        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        btnDong = (Button) view.findViewById(R.id.btnDong);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MaTT = edIDThuThu.getText().toString();
                String TenTT = edTenThuThu.getText().toString();
                String matKhau = edMatKhau.getText().toString();
                ThuThu thuThu = new ThuThu();
                thuThu.setHoTenTT(TenTT);
                thuThu.setMaTT(MaTT);
                thuThu.setMatKhau(matKhau);

                if (MaTT.length() == 0 && TenTT.length() == 0 && matKhau.length() == 0) {
                    edMatKhau.setError("Khong duoc de trong ");
                    edTenThuThu.setError("Khong duoc de trong ");
                    edIDThuThu.setError("Khong duoc de trong ");
                    return;
                }
                if (TenTT.length() == 0) {
                    edTenThuThu.setError("Khong duoc de trong ");
                    return;
                }
                if (matKhau.length() == 0) {
                    edMatKhau.setError("Khong duoc de trong ");
                    return;
                }
                if (!Character.isUpperCase(MaTT.codePointAt(0))) {
                    edIDThuThu.setError("Ghi In hoa chu cai dau tien");
                    return;
                }
                if (MaTT.length() < 5) {
                    edIDThuThu.setError("Ten Nguoi dung phai lon hon 5");
                    return;
                }
                if (TenTT.length() < 5) {
                    edTenThuThu.setError("Ten Nguoi dung phai lon hon 5");
                    return;
                }
                if (TenTT.length() < 15) {
                    edTenThuThu.setError("Ten Nguoi dung phai lon hon 5");
                    return;
                }
                if (!Character.isUpperCase(TenTT.codePointAt(0))) {
                    edTenThuThu.setError("Ghi in hoa chu cai dau tien");
                    return;
                }else {
                    ThuThuDAO thuThuDAO = new ThuThuDAO(getContext());
                    long check = thuThuDAO.insertThuThu(thuThu);
                    if (check > 0) {
                        Toast.makeText(getContext(), "Them :" + thuThu.getMaTT() + "Thanh Cong", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        listthuThu.clear();
                        listthuThu.addAll(thuThuDAO.getAllThuThu());
                        thuThuAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getContext(), "Them That Bai", Toast.LENGTH_SHORT).show();

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