package com.example.duanmau_android_mobile.model;

public class Top10Sach {
    public int soLuong;
    public String tenSach;

    public Top10Sach(int soLuong, String tenSach) {
        this.soLuong = soLuong;
        this.tenSach = tenSach;
    }

    public Top10Sach() {

    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }
}
