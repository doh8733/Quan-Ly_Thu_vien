package com.example.duanmau_android_mobile.DAo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_android_mobile.Database.DBHelper;
import com.example.duanmau_android_mobile.model.LoaiSach;
import com.example.duanmau_android_mobile.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    DBHelper dbHelper;

    public SachDAO(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public ArrayList<Sach> getAllSach() {

        ArrayList<Sach> saches = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String dataSach = "SELECT * FROM Sach";
        Cursor cursor = database.rawQuery(dataSach, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int maSach = cursor.getInt(cursor.getColumnIndex("maSach"));
            String tenSach = cursor.getString(cursor.getColumnIndex("TenSach"));
            int giaThue = cursor.getInt(cursor.getColumnIndex("giaThue"));
            int maLoai = cursor.getInt(cursor.getColumnIndex("maLoai"));
            int khuyenMai = cursor.getInt(cursor.getColumnIndex("giaKhuyenMai"));

            //  String maLoai = cursor.getString(3);
            Sach sach = new Sach(maSach, tenSach, giaThue, maLoai,khuyenMai);
            saches.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return saches;
    }

//    public String getNameSachFromID(int id) {
//
//        List<String> saches = new ArrayList<>();
//        SQLiteDatabase database = dbHelper.getReadableDatabase();
//        String dataSach = "SELECT TenSach FROM Sach WHERE maSach = ?";
//        Cursor cursor = database.rawQuery(dataSach, new String[]{id});
//        if (cursor.getCount()>0){
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                String tenSach = cursor.getString(cursor.getColumnIndex("TenSach"));
//                saches.add(tenSach);
//                cursor.moveToNext();
//            }
//            cursor.close();
//        }
//        return saches.get(0);
//    }

    public List<String> getAllTenSachSpinner() {

        List<String> lisSpn = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String dataloaiSach = "SELECT * FROM Sach";
        Cursor cursor = database.rawQuery(dataloaiSach, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int maLS = cursor.getInt(0);
            String tenLoai = cursor.getString(1);

            LoaiSach loaiSach = new LoaiSach(maLS, tenLoai);
            lisSpn.add(String.valueOf(loaiSach.getMaLoai()));
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return lisSpn;
    }

    public long insertSach(Sach sach) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenSach", sach.getTenSach());
        values.put("giaThue", sach.getGiaThue());
        values.put("maLoai", sach.getMaLoai());
        values.put("giaKhuyenMai", sach.getGiaKhuyenMai());
        long row = database.insert("Sach", null, values);
        return row;
    }

    public boolean updateSach(Sach sach) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenSach", sach.getTenSach());
        values.put("giaThue ", sach.getGiaThue());
        values.put("maLoai", sach.getMaLoai());
        values.put("giaKhuyenMai", sach.getGiaKhuyenMai());
        int row = database.update("Sach", values,
                "maSach=?", new String[]{String.valueOf(sach.getMaSach())});
        return (row > 0);
    }

    public boolean deleteSach(int maSach) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int row = database.delete("Sach", "maSach=?", new String[]{String.valueOf(maSach)});
        return row > 0;

    }
    public Sach getId(String id) {
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql, id);
        return list.get(0);
    }

    private List<Sach> getData(String sql, String... selectionArgs) {
        List<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);

        while (cursor.moveToNext()) {
            Sach obj = new Sach();
            obj.setMaSach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach"))));
            obj.setGiaThue(Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaThue"))));
            obj.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai"))));
            obj.setTenSach(String.valueOf(cursor.getString(cursor.getColumnIndex("TenSach"))));
            obj.setTenSach(String.valueOf(cursor.getString(cursor.getColumnIndex("giaKhuyenMai"))));

            list.add(obj);
        }
        return list;
    }



}
