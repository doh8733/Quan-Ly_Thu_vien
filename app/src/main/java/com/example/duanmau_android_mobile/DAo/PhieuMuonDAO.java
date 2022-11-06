package com.example.duanmau_android_mobile.DAo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_android_mobile.Database.DBHelper;
import com.example.duanmau_android_mobile.model.PhieuMuon;

import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    DBHelper dbHelper;
    public PhieuMuonDAO(Context context)
    {
        this.dbHelper = new DBHelper(context);
    }
    public List<PhieuMuon> getAllPhieuMuon(){
        List<PhieuMuon>phieuMuonList = new ArrayList<>();
        SQLiteDatabase database =dbHelper.getReadableDatabase();
        String dataPhieuMuon = "SELECT * FROM PhieuMuon";
        Cursor cursor = database.rawQuery(dataPhieuMuon,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            int maPM = cursor.getInt(0);
            String maTT = cursor.getString(1);
            int maTV = cursor.getInt(2);
            int maSach =cursor.getInt(3);
            int tienThue = cursor.getInt(4);
            String ngay = cursor.getString(5);
            int traSach = cursor.getInt(6);
            PhieuMuon phieuMuon = new PhieuMuon(maPM,maTT,maTV,maSach,tienThue,ngay,traSach);
            phieuMuonList.add(phieuMuon);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return phieuMuonList;
    }
    public long insertPhieuMuon(PhieuMuon phieuMuon){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTT",phieuMuon.getMaTT());
        contentValues.put("maTV",phieuMuon.getMaTV());
        contentValues.put("maSach",phieuMuon.getMaSach());
        contentValues.put("tienThue",phieuMuon.getTienThue());
        contentValues.put("ngay",phieuMuon.getNgay());
        contentValues.put("traSach",phieuMuon.getTraSach());

        long row =database.insert("PhieuMuon",null,contentValues);
        return  row;
    }
    public int updatePhieuMuon(PhieuMuon phieuMuon){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTT",phieuMuon.getMaTT());
        contentValues.put("maTV",phieuMuon.getMaTV());
        contentValues.put("maSach",phieuMuon.getMaSach());
        contentValues.put("tienThue",phieuMuon.getTienThue());
        contentValues.put("ngay",phieuMuon.getNgay());
        contentValues.put("traSach",phieuMuon.getTraSach());
        int row = database.update("PhieuMuon",contentValues,
                "maPM=?",new String[]{String.valueOf(phieuMuon.getMaPM())});
        return row ;
    }
    public boolean deletePhieuMuom(PhieuMuon phieuMuon){
        // String sql = "update LoaiSach SET tenLoai = ?, WHERE maLoai =?";
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // ContentValues values = new ContentValues();
        // values.put("tenLoai",loaiSach.getTenLoai());
        int row = database.delete("PhieuMuon",
                "maPM =?",new String[]{String.valueOf(phieuMuon)});
        return row>0;
    }


    public boolean deletePhieuMuoma(int maPM) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int row = database.delete("PhieuMuon",
                "maPM=?",new String[]{String.valueOf(maPM)});
        return row>0 ;
    }



}
