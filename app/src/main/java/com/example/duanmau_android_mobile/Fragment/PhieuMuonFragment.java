package com.example.duanmau_android_mobile.Fragment;

import static java.time.MonthDay.now;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmau_android_mobile.Adapter.PhieuMuonAdapter;
import com.example.duanmau_android_mobile.Adapter.SachpinnerAdapter;
import com.example.duanmau_android_mobile.Adapter.ThanhVienSpinnerAdapter;
import com.example.duanmau_android_mobile.Adapter.ThuThuSpinnerAdapter;
import com.example.duanmau_android_mobile.DAo.PhieuMuonDAO;
import com.example.duanmau_android_mobile.DAo.SachDAO;
import com.example.duanmau_android_mobile.DAo.ThanhVienDAO;
import com.example.duanmau_android_mobile.DAo.ThuThuDAO;
import com.example.duanmau_android_mobile.R;
import com.example.duanmau_android_mobile.model.PhieuMuon;
import com.example.duanmau_android_mobile.model.Sach;
import com.example.duanmau_android_mobile.model.ThanhVien;
import com.example.duanmau_android_mobile.model.ThuThu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class PhieuMuonFragment extends Fragment {

    private List<PhieuMuon> phieuMuonList;
    private RecyclerView rcvPhieuMuon;
    private FloatingActionButton fabThemPhieuMuon;
    private  PhieuMuonAdapter phieuMuonAdapter;
    private  PhieuMuonDAO phieuMuonDAO;
    private Spinner spnMaThuThu;
    private Spinner spnMaTV;
    private Spinner spinerMaSach;
    private CheckBox checkBox;
    private EditText edMapm;
    private EditText edngay;
    private Button btnAdd;
    private Button btndong;
    private AlertDialog alertDialog;

    private List<ThuThu>thuThuList;
    private List<ThanhVien>thanhVienList;
    private List<Sach>sachList;

    private ThuThuDAO thuThuDAO;
    private ThanhVienDAO thanhVienDAO;
    private SachDAO sachDAO;

    private SachpinnerAdapter sachpinnerAdapter;
    private ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    private ThuThuSpinnerAdapter thuThuSpinnerAdapter;

    private int checked;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.phieumuon_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcvPhieuMuon = (RecyclerView) view.findViewById(R.id.rcvPhieuMuon);
        fabThemPhieuMuon = (FloatingActionButton) view.findViewById(R.id.fabThemPhieuMuon);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvPhieuMuon.setLayoutManager(linearLayoutManager);
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        phieuMuonList = phieuMuonDAO.getAllPhieuMuon();

        phieuMuonAdapter = new PhieuMuonAdapter(phieuMuonList, getContext());
        rcvPhieuMuon.setAdapter(phieuMuonAdapter);

        fabThemPhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.dialog_them_phieumuon, null);

                thuThuDAO = new ThuThuDAO(getContext());
                thanhVienDAO = new ThanhVienDAO(getContext());
                sachDAO = new SachDAO(getContext());

                thanhVienList = new ArrayList<>();
                thuThuList = new ArrayList<>();
                sachList = new ArrayList<>();

                thanhVienList = thanhVienDAO.getAllThanhVien();
                thuThuList = thuThuDAO.getAllThuThu();
                sachList = sachDAO.getAllSach();

                sachpinnerAdapter = new SachpinnerAdapter(getContext(),sachList);
                thuThuSpinnerAdapter = new ThuThuSpinnerAdapter(getContext(),thuThuList);
                thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(getContext(),thanhVienList);


                spnMaThuThu = (Spinner) view1.findViewById(R.id.spnMaThuThu);
                spnMaTV = (Spinner) view1.findViewById(R.id.spnMaTV);
                spinerMaSach = (Spinner) view1.findViewById(R.id.spinerMaSach);
                edngay = (EditText) view1.findViewById(R.id.edngay);
                checkBox = (CheckBox) view1.findViewById(R.id.checkBox);
                btnAdd = (Button) view1.findViewById(R.id.btnAdd);
                btndong = (Button) view1.findViewById(R.id.btndong);

                spinerMaSach.setAdapter(sachpinnerAdapter);
                spnMaTV.setAdapter(thanhVienSpinnerAdapter);
                spnMaThuThu.setAdapter(thuThuSpinnerAdapter);

                builder.setView(view1);

                //date pickerdialog
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                int ngay = calendar.get(Calendar.DAY_OF_MONTH);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                edngay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(year,month,dayOfMonth);
                                edngay.setText(sdf.format(calendar.getTime()));
                            }
                        },nam,thang,ngay);
                        datePickerDialog.show();

                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String matt = thuThuList.get(spnMaThuThu.getSelectedItemPosition()).getMaTT();
                        int matv = thanhVienList.get(spnMaTV.getSelectedItemPosition()).getMaTV();
                        int maSach = sachList.get(spinerMaSach.getSelectedItemPosition()).getMaSach();
                        String ngay = edngay.getText().toString();
                        int tienThue = sachList.get(spinerMaSach.getSelectedItemPosition()).getGiaThue();

                        if (checkBox.isChecked()==true){
                            checked =1;
                        }if (checkBox.isChecked()==false){
                            checked = 0;
                        }

                        PhieuMuon phieuMuon = new PhieuMuon();
                        phieuMuon.setMaTT(matt);
                        phieuMuon.setMaTV(matv);
                        phieuMuon.setMaSach(maSach);
                        phieuMuon.setNgay(ngay);
                        phieuMuon.setTienThue(tienThue);
                        phieuMuon.setTraSach(checked);
                        if (matt.length()==0||matt.length()==0||maSach==0||ngay.length()==0||tienThue==0){
                            Toast.makeText(getContext(), "Khong duoc de  trong thong tin", Toast.LENGTH_SHORT).show();
                        }else {
                            long check = phieuMuonDAO.insertPhieuMuon(phieuMuon);
                            if (check>0){
                                Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                                phieuMuonList.clear();
                                phieuMuonList.addAll(phieuMuonDAO.getAllPhieuMuon());
                                alertDialog.dismiss();
                                phieuMuonAdapter.notifyDataSetChanged();

                            }else {
                                Toast.makeText(getContext(), "them that bai", Toast.LENGTH_SHORT).show();
                            }

                        }


                    }
                });
                btndong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }
}



