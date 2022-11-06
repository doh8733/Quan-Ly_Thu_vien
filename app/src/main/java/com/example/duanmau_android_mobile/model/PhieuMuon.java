package com.example.duanmau_android_mobile.model;

public class PhieuMuon {
    private int maPM;
    private String maTT;
    private int maTV;
    private int tienThue;
    private int maSach;
    private String ngay;
    private int traSach;


    public PhieuMuon() {
    }

    public PhieuMuon(int maPM, String maTT, int maTV, int tienThue, int maSach, String ngay, int traSach) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTV = maTV;
        this.tienThue = tienThue;
        this.maSach = maSach;
        this.ngay = ngay;
        this.traSach = traSach;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }

}
