package com.example.duanmau_android_mobile.DAo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_android_mobile.Database.DBHelper;
import com.example.duanmau_android_mobile.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    DBHelper dbHelper;
    public ThuThuDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    public List<ThuThu> getAllThuThu(){
        ArrayList<ThuThu>thuThus = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String dataThuThu = "SELECT * FROM ThuThu";
        Cursor cursor = database.rawQuery(dataThuThu,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()== false){
           ThuThu thuThu = new ThuThu();
           thuThu.setMaTT(String.valueOf(cursor.getString(cursor.getColumnIndex("maTT"))));
           thuThu.setHoTenTT(String.valueOf(cursor.getString(cursor.getColumnIndex("hoTenTT"))));
           thuThu.setMatKhau(String.valueOf(cursor.getString(cursor.getColumnIndex("matKhau"))));
            thuThus.add(thuThu);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return thuThus;
    }
    // lay data cho spinner
    public List<String> getallThuThuSpinner(String spnTT){
        ArrayList<String>thuThus = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String dataThuThu = "SELECT * FROM ThuThu";
        Cursor cursor = database.rawQuery(dataThuThu,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()== false){
            ThuThu thuThu = new ThuThu();
            thuThu.setMaTT(String.valueOf(cursor.getString(cursor.getColumnIndex("maTT"))));
            thuThu.setHoTenTT(String.valueOf(cursor.getString(cursor.getColumnIndex("hoTenTT"))));
            thuThu.setMatKhau(String.valueOf(cursor.getString(cursor.getColumnIndex("matKhau"))));
            thuThus.add(String.valueOf(thuThu.getMaTT()));
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return thuThus;
    }
    public  ThuThu getallTenThuThuSpinner(String maThuThu){
        ArrayList<ThuThu>thuThus = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String dataThuThu = "SELECT * FROM ThuThu WHERE maTT=?";
        Cursor cursor = database.rawQuery(dataThuThu,new String[]{String.valueOf(maThuThu)});
        cursor.moveToFirst();
        while (cursor.isAfterLast()== false){
            ThuThu thuThu = new ThuThu();
            thuThu.setMaTT(String.valueOf(cursor.getString(cursor.getColumnIndex("maTT"))));
            thuThu.setHoTenTT(String.valueOf(cursor.getString(cursor.getColumnIndex("hoTenTT"))));
            thuThu.setMatKhau(String.valueOf(cursor.getString(cursor.getColumnIndex("matKhau"))));
           thuThus.add(thuThu);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return thuThus.get(0);
    }

    public long insertThuThu(ThuThu thuThu){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maTT",thuThu.getMaTT());
        values.put("hoTenTT",thuThu.getHoTenTT());
        values.put("matKhau",thuThu.getMatKhau());
        long row = database.insert("ThuThu",null,values);
        return row;
    }

    public long updateThuThu(ThuThu thuThu){
        String sql = "update ThuThu SET hoTenTT = ?,matKhau =? WHERE maTT =?";
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maTT",thuThu.getMaTT());
        values.put("hoTenTT",thuThu.getHoTenTT());
        values.put("matKhau",thuThu.getMatKhau());
        long row = database.update("ThuThu",values,
                "maTT=?",new String[]{String.valueOf(thuThu.getMaTT())});
        return row;
    }
    public boolean deleteThuThu(ThuThu maTT){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int row = database.delete("ThuThu","maTT=?",new String[]{maTT+""});
        return  row >0;


    }
    public ThuThu getId(String id){
        String sql = "SELECT * FROM ThuThu WHERE maTT=?";
        List<ThuThu> list = getData(sql,id);
        return list.get(0);
    }
    private List<ThuThu> getData(String sql, String...selectionArgs){
        List<ThuThu> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            ThuThu thuThu = new ThuThu();
            thuThu.setMaTT(String.valueOf(cursor.getString(cursor.getColumnIndex("maTT"))));
            thuThu.setHoTenTT(String.valueOf(cursor.getString(cursor.getColumnIndex("hoTenTT"))));
            thuThu.setMatKhau(String.valueOf(cursor.getString(cursor.getColumnIndex("matKhau"))));
            list.clear();
            list.add(thuThu);
        }
        return list;
    }

    public  int checkLogin(String taiKhoan,String matKhau){
        String sql = "SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> thuThuList = getData(sql,taiKhoan,matKhau);
            if (thuThuList.size()==0){
                return -1;
            }
        return 1;
    }



}
