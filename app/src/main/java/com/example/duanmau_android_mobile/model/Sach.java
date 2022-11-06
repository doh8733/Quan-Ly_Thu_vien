package com.example.duanmau_android_mobile.model;

public class Sach {
    private int maSach;
    private String TenSach;
    private int giaThue;
    private int maLoai;
    private int giaKhuyenMai;

    public Sach() {
    }

    public Sach(int maSach, String tenSach, int giaThue, int maLoai, int giaKhuyenMai) {
        this.maSach = maSach;
        TenSach = tenSach;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
        this.giaKhuyenMai = giaKhuyenMai;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getGiaKhuyenMai() {
        return giaKhuyenMai;
    }

    public void setGiaKhuyenMai(int giaKhuyenMai) {
        this.giaKhuyenMai = giaKhuyenMai;
    }
}
