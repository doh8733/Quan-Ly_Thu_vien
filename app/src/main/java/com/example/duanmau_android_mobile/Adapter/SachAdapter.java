package com.example.duanmau_android_mobile.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_android_mobile.DAo.LoaiSachDAO;
import com.example.duanmau_android_mobile.DAo.SachDAO;
import com.example.duanmau_android_mobile.Database.DBHelper;
import com.example.duanmau_android_mobile.R;
import com.example.duanmau_android_mobile.model.LoaiSach;
import com.example.duanmau_android_mobile.model.Sach;

import java.util.List;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHodler> {
    List<Sach> sachList;
    List<LoaiSach> loaiSachList;
    ArrayAdapter<String> adapterspn;
    SachDAO sachDAO;
    Context context;
    AlertDialog alertDialog;
    LoaiSachDAO loaiSachDAO;
    DBHelper dbHelper;
    LoaiSachAdapter loaiSachAdapter;
    private EditText edIDSach;
    private EditText edTenSach;
    private EditText edGiathue;
    private Spinner spnSach;
    private Button btnAdd;
    private Button btnDong;
    public SachAdapter(List<Sach> sachList) {
        this.sachList = sachList;


    }


    @NonNull
    @Override
    public SachAdapter.SachViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sach_row, parent, false);
        SachViewHodler sachViewHodler = new SachViewHodler(view);
        context = parent.getContext();
        return sachViewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull SachAdapter.SachViewHodler holder, int position) {

        final Sach sach = sachList.get(position);
        dbHelper = new DBHelper(holder.itemView.getContext());
        loaiSachDAO = new LoaiSachDAO(holder.itemView.getContext());
        LoaiSach loaiSach = (loaiSachDAO.getAllTenLoai(sach.getMaLoai()));
        holder.tvMaSach.setText(String.valueOf(sach.getMaSach()));
        holder.tvTenSach.setText(String.valueOf(sach.getTenSach()));
        holder.tvGiaThue.setText(String.valueOf(sach.getGiaThue()));
        holder.tvMaloai.setText(String.valueOf(sach.getMaLoai()));
        holder.tvGiaKhuyenMai.setText("Gia Khuyen Mai"+String.valueOf(sach.getGiaKhuyenMai()));
        holder.tvTLS.setText("Ten loai: " + String.valueOf(loaiSach.getTenLoai()));

        holder.cardThanhVien.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Xoa");
                builder.setMessage("Ban Muon Xoa" + sach.getMaSach() + "?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SachDAO sachDAO = new SachDAO(v.getContext());
                        boolean check = sachDAO.deleteSach(sach.getMaSach());
                        if (check) {
                            Toast.makeText(builder.getContext(), "xoa thanh cong", Toast.LENGTH_SHORT).show();
                            sachList.remove(holder.getAdapterPosition());
                            notifyItemRemoved(holder.getAdapterPosition());
                            notifyItemChanged(holder.getAdapterPosition());
                            notifyDataSetChanged();
                            alertDialog.dismiss();
                        }


                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
                return false;
            }
        });
        holder.cardThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_add_sach, null);
                builder.setView(view);
                Dialog dialog = builder.create();


                edIDSach = (EditText) view.findViewById(R.id.edIDSach);
                edTenSach = (EditText) view.findViewById(R.id.edTenSach);
                edGiathue = (EditText) view.findViewById(R.id.edGiathue);
                spnSach = (Spinner) view.findViewById(R.id.spnSach);
                btnAdd = (Button) view.findViewById(R.id.btnAdd);
                btnDong = (Button) view.findViewById(R.id.btnDong);

                edTenSach.setText(sach.getTenSach());



                loaiSachDAO = new LoaiSachDAO(context);
                loaiSachList = loaiSachDAO.getAllLoaiSach();
                adapterspn = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,loaiSachDAO.getAllLoaiSachspn());
                loaiSachAdapter = new LoaiSachAdapter(loaiSachList);
                spnSach.setAdapter(adapterspn);
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       String tenSach = edTenSach.getText().toString();
                       int giaThue = Integer.parseInt(edGiathue.getText().toString());
                       int maLoai = loaiSachList.get(spnSach.getSelectedItemPosition()).getMaLoai();
                        sachDAO = new SachDAO(context);

                        sach.setTenSach(tenSach);
                        sach.setGiaThue(giaThue);
                        sach.setMaLoai(maLoai);
                        if (sachDAO.updateSach(sach)){
                            Toast.makeText(context, "Sua Thanh Cong", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            sachList.clear();
                            sachList.addAll(sachDAO.getAllSach());
                            notifyDataSetChanged();
                        }
                        else {
                            Toast.makeText(context, "That Bai", Toast.LENGTH_SHORT).show();
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
        });
    }

    @Override
    public int getItemCount() {
        return sachList.size();
    }

    public class SachViewHodler extends RecyclerView.ViewHolder {

        private CardView cardThanhVien;
        private ImageView imgSach;
        private TextView tvMaSach;
        private TextView tvTenSach;
        private TextView tvMaloai;
        private TextView tvGiaThue;
        private ImageButton imgBtnSuaSach;
        private ImageButton imgBtnXoaSach;
        private TextView tvTLS;
        private TextView tvGiaKhuyenMai;
        public SachViewHodler(@NonNull View itemView) {
            super(itemView);


            tvTLS = (TextView) itemView.findViewById(R.id.tvTLS);


            tvGiaKhuyenMai = (TextView) itemView.findViewById(R.id.tvGiaKhuyenMai);

            cardThanhVien = (CardView) itemView.findViewById(R.id.cardThanhVien);
            imgSach = (ImageView) itemView.findViewById(R.id.imgSach);
            tvMaSach = (TextView) itemView.findViewById(R.id.tvMaSach);
            tvTenSach = (TextView) itemView.findViewById(R.id.tvTenSach);
            tvMaloai = (TextView) itemView.findViewById(R.id.tvMaloai);
            tvGiaThue = (TextView) itemView.findViewById(R.id.tvGiaThue);


        }
    }

}
