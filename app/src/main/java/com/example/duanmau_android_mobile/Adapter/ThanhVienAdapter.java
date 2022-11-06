package com.example.duanmau_android_mobile.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_android_mobile.DAo.ThanhVienDAO;
import com.example.duanmau_android_mobile.R;
import com.example.duanmau_android_mobile.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder> {

    Context context;
    ThanhVienDAO thanhVienDAO;
    ArrayList<ThanhVien> listTV;


    public ThanhVienAdapter(ArrayList<ThanhVien> listTV) {

        this.listTV = listTV;
        thanhVienDAO = new ThanhVienDAO(context);
    }


    @NonNull
    @Override
    public ThanhVienAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thanh_vien_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienAdapter.ViewHolder holder, int position) {
        final ThanhVien thanhVien1 = listTV.get(position);
        holder.tvMaThanhVien.setText("Ma Thanh Vien: " + String.valueOf(thanhVien1.getMaTV()));
        holder.tvTenThanhVien.setText("Ten Thanh Vien: " + String.valueOf(thanhVien1.getHoTenTV()));
        holder.tvNamSinh.setText("Nam Sinh: " + String.valueOf(thanhVien1.getNamSinh()));
        holder.imgBtnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.openDialog(thanhVien1);

            }
        });
        holder.imgBtnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.DialogUpdate(thanhVien1);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listTV.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardThanhVien;
        private ImageView imgThanhVien;
        private TextView tvMaThanhVien;
        private TextView tvTenThanhVien;
        private TextView tvNamSinh;
        private ImageButton imgBtnSua;
        private ImageButton imgBtnXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBtnXoa = (ImageButton) itemView.findViewById(R.id.imgBtnXoa);

            cardThanhVien = (CardView) itemView.findViewById(R.id.cardThanhVien);
            tvNamSinh = (TextView) itemView.findViewById(R.id.tvNamSinh);
            imgBtnSua = (ImageButton) itemView.findViewById(R.id.imgBtnSua);

            imgThanhVien = (ImageView) itemView.findViewById(R.id.imgThanhVien);
            tvMaThanhVien = (TextView) itemView.findViewById(R.id.tvMaThanhVien);
            tvTenThanhVien = (TextView) itemView.findViewById(R.id.tvTenThanhVien);
            tvNamSinh = (TextView) itemView.findViewById(R.id.tvNamSinh);
            imgBtnSua = (ImageButton) itemView.findViewById(R.id.imgBtnSua);

        }

        public void openDialog(ThanhVien thanhVien) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Xoa");
            builder.setMessage("Ban co muon xoa")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            thanhVienDAO = new ThanhVienDAO(context);
                            if (thanhVienDAO.deleteThanhVien(thanhVien.getMaTV())) {
                                Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                listTV.clear();
                                listTV.addAll(thanhVienDAO.getAllThanhVien());
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(context, "Xoa khong thanh cong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                            dialog.dismiss();
                        }
                    });
            // Create the AlertDialog object and return it
            Dialog dialog = builder.create();
            dialog.show();
        }

        private EditText edIDThanhVien;
        private EditText edUpdateTenThanhVien;
        private EditText edUpdateNamSinh;
        private Button btnUpdate;
        private Button btnClose;

        public void DialogUpdate(ThanhVien thanhVien) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_update, null);
            builder.setView(view);
            Dialog dialog = builder.create();

            edIDThanhVien = (EditText) view.findViewById(R.id.edIDThanhVien);
            edUpdateTenThanhVien = (EditText) view.findViewById(R.id.edUpdateTenThanhVien);
            edUpdateNamSinh = (EditText) view.findViewById(R.id.edUpdateNamSinh);
            btnUpdate = (Button) view.findViewById(R.id.btnUpdate);
            btnClose = (Button) view.findViewById(R.id.btnClose);

            //gan fia chi cua thanh vien cho view
            edUpdateTenThanhVien.setText(thanhVien.getHoTenTV());
            edUpdateNamSinh.setText(String.valueOf(thanhVien.getNamSinh()));
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {// chỗ này nó không biết cái mã cảu thành viên là gì//tr toi xoa duoc ma tu luc lam cai phieu muon la no loi luon ok ndợi
                    thanhVien.setHoTenTV(edUpdateTenThanhVien.getText().toString());
                    thanhVien.setNamSinh(String.valueOf(edUpdateNamSinh.getText().toString()));
                    // là do ông không lấy id cho nó
                    //vì nó sẽ không beiets id là gì lên nó không sxoas không sửa đc
                        thanhVienDAO = new ThanhVienDAO(context);
                    if (thanhVienDAO.updateThanhVien(thanhVien)) {//ôn gén bài này sang cho tôi

                        Toast.makeText(context, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        listTV.clear();
                        listTV.addAll(thanhVienDAO.getAllThanhVien());
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(context, "Sua that bai", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

    }
}
