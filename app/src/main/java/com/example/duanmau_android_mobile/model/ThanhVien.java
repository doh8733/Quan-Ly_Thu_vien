package com.example.duanmau_android_mobile.model;

import java.util.List;

public class ThanhVien {
    private int maTV;
    private String hoTenTV;
    private String namSinh;

    public ThanhVien() {
    }

    public ThanhVien(int maTV, String hoTenTV, String namSinh) {
        this.maTV = maTV;
        this.hoTenTV = hoTenTV;
        this.namSinh = namSinh;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTenTV() {
        return hoTenTV;
    }

    public void setHoTenTV(String hoTenTV) {
        this.hoTenTV = hoTenTV;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
