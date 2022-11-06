package com.example.duanmau_android_mobile.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_android_mobile.DAo.ThuThuDAO;
import com.example.duanmau_android_mobile.R;
import com.example.duanmau_android_mobile.model.ThuThu;

import java.util.List;

public class ThuThuAdapter extends RecyclerView.Adapter<ThuThuAdapter.ThuThuViewHolder> {
    List<ThuThu> thuThuList;
    ThuThuDAO thuThuDAO;
    Context context;
    AlertDialog alertDialog;

    public ThuThuAdapter(List<ThuThu> thuThuList) {
        this.thuThuList = thuThuList;
        this.thuThuDAO = new ThuThuDAO(context);
        this.context = context;
    }



    @NonNull
    @Override
    public ThuThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thu_thu_row,parent,false);

        ThuThuViewHolder thuThuViewHolder = new ThuThuViewHolder(view);

        return thuThuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThuThuViewHolder holder, int position) {
        ThuThu thuThu = thuThuList.get(position);

        holder.tvIDThuThu.setText(String.valueOf(thuThu.getMaTT()));
        holder.tvTenThuThu.setText(String.valueOf(thuThu.getHoTenTT()));
        holder.tvMatKhau.setText(String.valueOf(thuThu.getMatKhau()));

        if (position % 2 ==1){
            holder.tvIDThuThu.setTextColor(Color.GREEN);
            holder.tvTenThuThu.setTextColor(Color.GREEN);
            holder.tvMatKhau.setTextColor(Color.GREEN);
        }
        else {
            holder.tvIDThuThu.setTextColor(Color.RED);
            holder.tvTenThuThu.setTextColor(Color.RED);
            holder.tvMatKhau.setTextColor(Color.RED);
        }

        holder.imgBtnXoaThuThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Xoa");
                builder.setMessage("Ban co chac muon Xoa "+thuThu.getMaTT()+"?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ThuThuDAO thuThuDAO = new ThuThuDAO(v.getContext());
                        thuThuDAO.deleteThuThu(thuThu);
                        thuThuList.remove(holder.getAdapterPosition());
                        notifyItemChanged(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                        notifyDataSetChanged();
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

    }

    @Override
    public int getItemCount() {
        return thuThuList.size();
    }





    public class ThuThuViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView3;
        private TextView textView2;
        private TextView textView3;
        private TextView textView5;
        private TextView tvIDThuThu;
        private TextView tvTenThuThu;
        private TextView tvMatKhau;
        private ImageButton imgBtnXoaThuThu;
        private ImageButton imgBtnSuaThuThu;
        private TextView tvtaiKhoanThuThu;
        public ThuThuViewHolder(@NonNull View itemView) {

            super(itemView);


            imageView3 = (ImageView) itemView.findViewById(R.id.imageView3);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);
            textView3 = (TextView) itemView.findViewById(R.id.textView3);



            tvIDThuThu = (TextView) itemView.findViewById(R.id.tvIDThuThu);
            tvTenThuThu = (TextView) itemView.findViewById(R.id.tvTenThuThu);
            tvMatKhau = (TextView) itemView.findViewById(R.id.tvMatKhau);
            imgBtnXoaThuThu = (ImageButton) itemView.findViewById(R.id.imgBtnXoaThuThu);
            imgBtnSuaThuThu = (ImageButton) itemView.findViewById(R.id.imgBtnSuaThuThu);



        }
        public  void  OnpenDialogThuThu(ThuThu thuThu){

        }
    }
}
