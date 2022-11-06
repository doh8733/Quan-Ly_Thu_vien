package com.example.duanmau_android_mobile.Adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_android_mobile.DAo.PhieuMuonDAO;
import com.example.duanmau_android_mobile.DAo.SachDAO;
import com.example.duanmau_android_mobile.DAo.ThanhVienDAO;
import com.example.duanmau_android_mobile.DAo.ThuThuDAO;
import com.example.duanmau_android_mobile.Database.DBHelper;
import com.example.duanmau_android_mobile.R;
import com.example.duanmau_android_mobile.model.PhieuMuon;
import com.example.duanmau_android_mobile.model.Sach;
import com.example.duanmau_android_mobile.model.ThanhVien;
import com.example.duanmau_android_mobile.model.ThuThu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.PhieuMuonViewHolder> {
    private List<PhieuMuon> phieuMuonList;
    private Context context;
    private ThanhVienDAO thanhVienDAO;
    private ThanhVien thanhVien;
    private SachDAO sachDAO;
    private ThuThu thuThu;
    private ThuThuDAO thuThuDAO;
    private PhieuMuonDAO phieuMuonDAO;
    AlertDialog alertDialog;
    private Spinner spnsuaMaThuThu;
    private Spinner spnSuaMaTV;
    private Spinner spinerSuaMaSach;
    private EditText edSuangay;
    private CheckBox checkBox;
    private Button btnEdit;
    private Button btnCancle;

    private List<ThanhVien> thanhVienList;
    private List<ThuThu> thuThuList;
    private List<Sach> sachList;

    private SachpinnerAdapter sachpinnerAdapter;
    private ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    private ThuThuSpinnerAdapter thuThuSpinnerAdapter;

    private int checked;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonAdapter(List<PhieuMuon> phieuMuonList, Context context) {
        this.phieuMuonList = phieuMuonList;
        this.context = context;
    }

    @NonNull
    @Override
    public PhieuMuonAdapter.PhieuMuonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_phieumuon, parent, false);
        PhieuMuonViewHolder phieuMuonViewHolder = new PhieuMuonViewHolder(view);
        return phieuMuonViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuMuonViewHolder holder, int position) {
        final PhieuMuon phieuMuon = phieuMuonList.get(position);

        thanhVienDAO = new ThanhVienDAO(holder.itemView.getContext());
        thanhVien = thanhVienDAO.getAllMaThanhVien(phieuMuon.getMaTV());


        thuThuDAO = new ThuThuDAO(holder.itemView.getContext());
        thuThu = thuThuDAO.getallTenThuThuSpinner(phieuMuon.getMaTT());

//        sachDAO = new SachDAO(holder.itemView.getContext());
//        Log.e("list",sachDAO.getNameSachFromID(String.valueOf(phieuMuon.getMaSach())));

        holder.tvMaPhieuMuon.setText("Ma Phieu Muon: " + String.valueOf(phieuMuon.getMaPM()));
        holder.tvMaThuThu.setText("Ma Thu Thu: " + String.valueOf(phieuMuon.getMaTT()));
        holder.tvMaTV.setText("Ma Thanh Vien: " + String.valueOf(phieuMuon.getMaTV()));
        holder.tvmaSach.setText("Tien Thue: " + String.valueOf(phieuMuon.getMaSach()));
        holder.tvNgay.setText("Ngay: " + String.valueOf(phieuMuon.getNgay()));
        holder.tvTienThue.setText("Ma Sach: " + String.valueOf(phieuMuon.getTienThue()));
        // holder.tvTraSach.setText("Trang Thai: " + String.valueOf(phieuMuon.getTraSach()));
        holder.tvTenThanhVien.setText("Ten TV: " + thanhVien.getHoTenTV());
        holder.tvTenThuThu.setText("Ten TT: " + thuThu.getHoTenTT());
//        holder.tvmaSach.setText("Ten Sach: " + sach.getTenSach());


        if (phieuMuon.getTraSach() == 1) {
            holder.tvTraSach.setText("Da Tra Sach");
            holder.tvTraSach.setTextColor(Color.GREEN);
        }
        if (phieuMuon.getTraSach() == 0) {
            holder.tvTraSach.setText("Chua tra Sach");
            holder.tvTraSach.setTextColor(Color.RED);
        }
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Xoa");
                builder.setMessage("Ban Muon Xoa " + phieuMuon.getMaPM() + "?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        phieuMuonDAO = new PhieuMuonDAO(v.getContext());
                       boolean check = phieuMuonDAO.deletePhieuMuoma(phieuMuon.getMaPM());
                       if (check ){
                           phieuMuonList.remove(holder.getAdapterPosition());
                           notifyItemRemoved(holder.getAdapterPosition());
                           notifyItemChanged(holder.getAdapterPosition());
                           notifyDataSetChanged();
                           alertDialog.dismiss();
                           Toast.makeText(context, "Xoa Thanh Cong", Toast.LENGTH_SHORT).show();
                       }else {
                           Toast.makeText(context, "Xoa That Bai", Toast.LENGTH_SHORT).show();
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
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_sua_phieumuon, null);

                thuThuDAO = new ThuThuDAO(view.getContext());
                thanhVienDAO = new ThanhVienDAO(view.getContext());
                sachDAO = new SachDAO(view.getContext());

                thanhVienList = new ArrayList<>();
                thuThuList = new ArrayList<>();
                sachList = new ArrayList<>();

                thanhVienList = thanhVienDAO.getAllThanhVien();
                thuThuList = thuThuDAO.getAllThuThu();
                sachList = sachDAO.getAllSach();

                sachpinnerAdapter = new SachpinnerAdapter(context, sachList);
                thuThuSpinnerAdapter = new ThuThuSpinnerAdapter(context, thuThuList);
                thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context, thanhVienList);


                spnsuaMaThuThu = (Spinner) view.findViewById(R.id.spnsuaMaThuThu);
                spnSuaMaTV = (Spinner) view.findViewById(R.id.spnSuaMaTV);
                spinerSuaMaSach = (Spinner) view.findViewById(R.id.spinerSuaMaSach);
                edSuangay = (EditText) view.findViewById(R.id.edSuangay);
                checkBox = (CheckBox) view.findViewById(R.id.checkBox);
                btnEdit = (Button) view.findViewById(R.id.btnEdit);
                btnCancle = (Button) view.findViewById(R.id.btnCancle);

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                int nam = calendar.get(Calendar.YEAR);
                int thang = calendar.get(Calendar.MONTH);
                int ngay = calendar.get(Calendar.DAY_OF_MONTH);

                spinerSuaMaSach.setAdapter(sachpinnerAdapter);
                spnSuaMaTV.setAdapter(thanhVienSpinnerAdapter);
                spnsuaMaThuThu.setAdapter(thuThuSpinnerAdapter);

                builder.setView(view);

                edSuangay.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(year, month, dayOfMonth);
                                edSuangay.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        }, nam, thang, ngay);
                        datePickerDialog.show();
                    }
                });

                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String matt = thuThuList.get(spnsuaMaThuThu.getSelectedItemPosition()).getMaTT();
                        int matv = thanhVienList.get(spnSuaMaTV.getSelectedItemPosition()).getMaTV();
                        int maSach = sachList.get(spinerSuaMaSach.getSelectedItemPosition()).getMaSach();
                        String ngay = edSuangay.getText().toString();
                        int tienThue = sachList.get(spinerSuaMaSach.getSelectedItemPosition()).getGiaThue();

                        phieuMuon.setMaTT(matt);
                        phieuMuon.setMaTV(matv);
                        phieuMuon.setMaSach(maSach);
                        phieuMuon.setNgay(ngay);
                        phieuMuon.setTienThue(tienThue);

                        if (checkBox.isChecked()==true) {
                            phieuMuon.setTraSach(1);
                        } else{
                            phieuMuon.setTraSach(0);
                        }
                        phieuMuonDAO= new PhieuMuonDAO(context);
                            int check =phieuMuonDAO.updatePhieuMuon(phieuMuon);
                        if (check>0) {
                            Toast.makeText(context, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                            phieuMuonList.clear();
                            phieuMuonList.addAll(phieuMuonDAO.getAllPhieuMuon());
                            alertDialog.dismiss();
                            notifyDataSetChanged();

                        } else {
                            Toast.makeText(context, "Sua that bai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnCancle.setOnClickListener(new View.OnClickListener() {
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


    @Override
    public int getItemCount() {
        return phieuMuonList.size();
    }

    public class PhieuMuonViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView2;


        private TextView tvMaPhieuMuon;
        private TextView tvMaThuThu;
        private TextView tvTenThuThu;
        private TextView tvMaTV;
        private TextView tvTenThanhVien;
        private TextView tvmaSach;
        private TextView tvTienThue;
        private TextView tvNgay;
        private TextView tvTraSach;
        private CardView cardView;

        public PhieuMuonViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);
            tvMaPhieuMuon = (TextView) itemView.findViewById(R.id.tvMaPhieuMuon);
            tvMaThuThu = (TextView) itemView.findViewById(R.id.tvMaThuThu);
            tvTenThuThu = (TextView) itemView.findViewById(R.id.tvTenThuThu);
            tvMaTV = (TextView) itemView.findViewById(R.id.tvMaTV);
            tvTenThanhVien = (TextView) itemView.findViewById(R.id.tvTenThanhVien);
            tvmaSach = (TextView) itemView.findViewById(R.id.tvmaSach);
            tvTienThue = (TextView) itemView.findViewById(R.id.tvTienThue);
            tvNgay = (TextView) itemView.findViewById(R.id.tvNgay);
            tvTraSach = (TextView) itemView.findViewById(R.id.tvTraSach);

        }

    }

}
