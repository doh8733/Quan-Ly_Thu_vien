package com.example.duanmau_android_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau_android_mobile.DAo.ThuThuDAO;

public class LoginActivity extends AppCompatActivity {
    private EditText edUser;
    private EditText edPass;
    private Button btnLogin;
    private Button btnCancle;
    ThuThuDAO thuThuDAO;
    private CheckBox chkChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        chkChecked = findViewById(R.id.chkChecked);
        edUser = (EditText) findViewById(R.id.edUser);
        edPass = (EditText) findViewById(R.id.edPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);


        btnCancle = (Button) findViewById(R.id.btnCancle);
        thuThuDAO = new ThuThuDAO(this);

        SharedPreferences sharedPreferences = getSharedPreferences("USER_Flie", MODE_PRIVATE);
        edUser.setText(sharedPreferences.getString("USERNAME", ""));
        edPass.setText(sharedPreferences.getString("PASSWORD", ""));

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUser.setText("");
                edPass.setText("");
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

    }

    public void checkLogin() {

        String userName = edUser.getText().toString();
        String pass = edPass.getText().toString();
        boolean checked = chkChecked.isChecked();
        if (userName.isEmpty() || pass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Ten Dang nhap va mat khau khong dung", Toast.LENGTH_SHORT).show();
        } else {
            if (thuThuDAO.checkLogin(userName, pass) > 0 || (userName.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin"))) {
                SharedPreferences sharedPreferences = getSharedPreferences("USER_Flie", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("PASSWORD", pass);
                editor.commit();
                Toast.makeText(getApplicationContext(), "thanh cong", Toast.LENGTH_SHORT).show();
                luuThongTinDangNhap(userName, pass, checked);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("user", userName);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "mat khau va tai khoan khong dung", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void luuThongTinDangNhap(String taiKhoan, String matkhau, boolean remember) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER_Flie", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (remember == false) {
            editor.clear();
        } else {
            editor.putString("USERNAME", taiKhoan);
            editor.putString("PASSWORD", matkhau);
            editor.putBoolean("REMEMBER", remember);
        }

        editor.commit();
    }
}