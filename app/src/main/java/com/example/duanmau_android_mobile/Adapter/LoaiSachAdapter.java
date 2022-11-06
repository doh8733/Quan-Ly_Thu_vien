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
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_android_mobile.DAo.LoaiSachDAO;
import com.example.duanmau_android_mobile.R;
import com.example.duanmau_android_mobile.model.LoaiSach;
import com.example.duanmau_android_mobile.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.LoaiSachViewHolder> {
    Context context;
    LoaiSachDAO loaiSachDAO;
    List<LoaiSach> listLoaiSach;
    AlertDialog alertDialog;

    public LoaiSachAdapter(List<LoaiSach> listLoaiSach) {

        this.listLoaiSach = listLoaiSach;
    }


    @NonNull
    @Override
    public LoaiSachAdapter.LoaiSachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loai_sach_row, parent, false);
        LoaiSachViewHolder loaiSachViewHolder = new LoaiSachViewHolder(view);
        return loaiSachViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSachAdapter.LoaiSachViewHolder holder, int position) {
        final LoaiSach loaiSach = listLoaiSach.get(position);
        context = holder.itemView.getContext();
        holder.tvMaLoaiSach.setText(String.valueOf(loaiSach.getMaLoai()));
        holder.tvTenLoaiSach.setText(String.valueOf(loaiSach.getTenLoai()));

        holder.imgBtnXoaLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Xoa");
                builder.setMessage("Ban Muon Xoa" + loaiSach.getMaLoai() + "?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(v.getContext());
                        loaiSachDAO.deleteLoaiSach(loaiSach);
                        listLoaiSach.remove(holder.getAdapterPosition());
                        notifyItemChanged(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                        notifyDataSetChanged();
                        alertDialog.dismiss();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
        holder.imgBtnSuaLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.DialogUpdate(loaiSach);
//
            }
        });

    }

    @Override
    public int getItemCount() {
        return listLoaiSach.size();
    }


    private EditText edIDUpdateLoaiSach;
    private EditText edUpdateTenSach;
    private Button btnUpdateLoaiSach;
    private Button btnDongUpdateLoaiSach;

    public class LoaiSachViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView2;
        private TextView tvMaLoaiSach;
        private TextView tvTenLoaiSach;
        private ImageButton imgBtnSuaLoaiSach;
        private ImageButton imgBtnXoaLoaiSach;

        public LoaiSachViewHolder(@NonNull View itemView) {
            super(itemView);


            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);
            tvMaLoaiSach = (TextView) itemView.findViewById(R.id.tvMaLoaiSach);
            tvTenLoaiSach = (TextView) itemView.findViewById(R.id.tvTenLoaiSach);
            imgBtnSuaLoaiSach = (ImageButton) itemView.findViewById(R.id.imgBtnSuaLoaiSach);
            imgBtnXoaLoaiSach = (ImageButton) itemView.findViewById(R.id.imgBtnXoaLoaiSach);

        }

        public void DialogUpdate(LoaiSach loaiSach) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_update_loaisach, null);
            builder.setView(view);
            Dialog dialog = builder.create();

            edIDUpdateLoaiSach = (EditText) view.findViewById(R.id.edIDUpdateLoaiSach);
            edUpdateTenSach = (EditText) view.findViewById(R.id.edUpdateTenSach);
            btnUpdateLoaiSach = (Button) view.findViewById(R.id.btnUpdateLoaiSach);
            btnDongUpdateLoaiSach = (Button) view.findViewById(R.id.btnDongUpdateLoaiSach);
            edUpdateTenSach.setText(loaiSach.getTenLoai());
            btnUpdateLoaiSach.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loaiSachDAO = new LoaiSachDAO(context);
                    loaiSach.setTenLoai(edUpdateTenSach.getText().toString());

                    if (loaiSachDAO.updateLoaiSach(loaiSach)) {
                        Toast.makeText(context, "Sua Thanh Cong", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        listLoaiSach.clear();
                        listLoaiSach.addAll(loaiSachDAO.getAllLoaiSach());
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(context, "Sua that bai", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            btnDongUpdateLoaiSach.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }

}
