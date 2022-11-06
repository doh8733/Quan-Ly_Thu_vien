package com.example.duanmau_android_mobile.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau_android_mobile.DAo.ThuThuDAO;
import com.example.duanmau_android_mobile.R;
import com.example.duanmau_android_mobile.model.ThuThu;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class DoiMatKhauFragment extends Fragment {
    private Button buttonHuyLuuMatKhauMoi;
    private TextView textView6;
    private TextInputLayout edittext11;
    private TextInputEditText edtMatKhauCu;
    private TextInputLayout edittext12;
    private TextInputEditText edtMatKhauMoi1;
    private TextInputLayout textinput13;
    private TextInputEditText edtMatKhauMoi2;
    private Button buttonLuuMatKhauMoi;
    ThuThuDAO thuThuDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        buttonHuyLuuMatKhauMoi = (Button) view.findViewById(R.id.button_huyLuuMatKhauMoi);
        textView6 = (TextView) view.findViewById(R.id.textView6);
        edittext11 = (TextInputLayout) view.findViewById(R.id.edittext11);
        edtMatKhauCu = (TextInputEditText) view.findViewById(R.id.edt_matKhauCu);
        edittext12 = (TextInputLayout) view.findViewById(R.id.edittext12);
        edtMatKhauMoi1 = (TextInputEditText) view.findViewById(R.id.edt_matKhauMoi1);
        textinput13 = (TextInputLayout) view.findViewById(R.id.textinput13);
        edtMatKhauMoi2 = (TextInputEditText) view.findViewById(R.id.edt_matKhauMoi2);
        buttonLuuMatKhauMoi = (Button) view.findViewById(R.id.button_luuMatKhauMoi);

        thuThuDAO = new ThuThuDAO(getContext());
        buttonHuyLuuMatKhauMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMatKhauCu.setText("");
                edtMatKhauMoi1.setText("");
                edtMatKhauMoi2.setText("");
            }
        });
        buttonLuuMatKhauMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("USER_Flie",MODE_PRIVATE);
                String User = preferences.getString("USERNAME","");
                if (validate()>0){
                    ThuThu thuThu = thuThuDAO.getId(User);
                    thuThu.setMatKhau(edtMatKhauMoi1.getText().toString());
                    thuThuDAO.updateThuThu(thuThu);
                    if (thuThuDAO.updateThuThu(thuThu)>0) {
                        Toast.makeText(getContext(), "Thanh Cong", Toast.LENGTH_SHORT).show();

                        edtMatKhauCu.setText("");
                        edtMatKhauMoi1.setText("");
                        edtMatKhauMoi2.setText("");

                    }else {
                        Toast.makeText(getContext(), "That Bai", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });
    }

    public  int validate(){
        int check = 1;
        String mkCu = edtMatKhauCu.getText().toString();
        String mKmoi1 =edtMatKhauMoi1.getText().toString();
        String mkMoi2 =edtMatKhauMoi2.getText().toString();
        if ( mkCu.length() ==0 || mKmoi1.length() == 0 || mkMoi2.length() ==0){
            Toast.makeText(getContext(), "Khong duoc de trong ", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            SharedPreferences preferences = getActivity().getSharedPreferences("USER_Flie", MODE_PRIVATE);
            String passold = preferences.getString("PASSWORD","");
            String pass = edtMatKhauMoi1.getText().toString();
            String rePass = edtMatKhauMoi2.getText().toString();
            if (!passold.equals(edtMatKhauCu.getText().toString())){
                Toast.makeText(getContext(), "Sai mat khau cu", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(rePass)){
                Toast.makeText(getContext(), "Mat khau moi khong giong nhau", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;


    }
}