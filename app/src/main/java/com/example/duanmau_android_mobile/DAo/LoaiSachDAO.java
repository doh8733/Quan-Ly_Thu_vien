package com.example.duanmau_android_mobile.DAo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_android_mobile.Database.DBHelper;
import com.example.duanmau_android_mobile.model.LoaiSach;
import com.example.duanmau_android_mobile.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    DBHelper dbHelper;
    public LoaiSachDAO(Context context) {
        this.dbHelper = new DBHelper(context);
    }


    public LoaiSach getAllTenLoai(int maLoai){
        List<LoaiSach> list = new ArrayList<>();
        String query = "SELECT * FROM LoaiSach WHERE maLoai=?";
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query,new String[]{String.valueOf(maLoai)});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String maLoaisach = cursor.getString(cursor.getColumnIndex("maLoai"));
                String tenLoai = cursor.getString(cursor.getColumnIndex("tenLoai"));
                list.add(new LoaiSach(Integer.parseInt(maLoaisach),tenLoai));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list.get(0);
    }
    public List<String> getAllLoaiSachspn(){

        List<String>lisSpn = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String dataloaiSach = "SELECT * FROM LoaiSach";
        Cursor cursor = database.rawQuery(dataloaiSach,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()== false){
            int maLS = cursor.getInt(0);
            String tenLoai= cursor.getString(1);

            LoaiSach loaiSach = new LoaiSach(maLS,tenLoai);
            lisSpn.add(String.valueOf(loaiSach.getMaLoai()));
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return lisSpn;
    }


    public ArrayList<LoaiSach> getAllLoaiSach(){

        ArrayList<LoaiSach>loaiSachs = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String dataloaiSach = "SELECT * FROM LoaiSach";
        Cursor cursor = database.rawQuery(dataloaiSach,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()== false){
            int maLS = cursor.getInt(0);
            String tenLoai= cursor.getString(1);
            LoaiSach loaiSach = new LoaiSach(maLS,tenLoai);
            loaiSachs.add(loaiSach);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return loaiSachs;
    }
    public long insertLoaiSach(LoaiSach loaiSach){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenLoai",loaiSach.getTenLoai());
        long row = database.insert("LoaiSach",null,values);
       return row;
    }
    public boolean updateLoaiSach(LoaiSach loaiSach){
        String sql = "update LoaiSach SET tenLoai = ?, WHERE maLoai =?";
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tenLoai",loaiSach.getTenLoai());
        long row = database.update("LoaiSach",values,
                "maLoai=?",new String[]{loaiSach.getMaLoai()+""});
        return (row >0);
    }
    public boolean deleteLoaiSach(LoaiSach loaiSach){
       // String sql = "update LoaiSach SET tenLoai = ?, WHERE maLoai =?";
        SQLiteDatabase database = dbHelper.getWritableDatabase();
       // ContentValues values = new ContentValues();
       // values.put("tenLoai",loaiSach.getTenLoai());
        int row = database.delete("LoaiSach",
                "maLoai=?",new String[]{String.valueOf(loaiSach.getMaLoai())+""});
        return (row >0);
    }
}
