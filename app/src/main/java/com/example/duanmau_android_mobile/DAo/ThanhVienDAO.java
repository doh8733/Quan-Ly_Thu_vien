package com.example.duanmau_android_mobile.DAo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_android_mobile.Database.DBHelper;
import com.example.duanmau_android_mobile.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    DBHelper dbHelper;

    public ThanhVienDAO(Context context) {
        this.dbHelper = new DBHelper(context);
    }
    public ArrayList<ThanhVien> getAllThanhVien(){
        ArrayList<ThanhVien>thanhViens = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String dataThanVien = "SELECT * FROM ThanhVien";
        Cursor cursor = database.rawQuery(dataThanVien,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()== false){
            int maTV = cursor.getInt(cursor.getColumnIndex("maTV"));
            String hoTen= cursor.getString(cursor.getColumnIndex("hoTen"));
            String namSinh = cursor.getString(cursor.getColumnIndex("namSinh"));
           ThanhVien thanhVien = new ThanhVien(maTV,hoTen,namSinh);
            thanhViens.add(thanhVien);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return thanhViens;
    }
    //get data cho thanh vien spinner

    public List<String> getAllThanhvienSpinner(){
        List<String> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String dataThanVien = "SELECT * FROM ThanhVien";
        Cursor cursor = database.rawQuery(dataThanVien,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()== false){
            int maTV = cursor.getInt(cursor.getColumnIndex("maTV"));
            String hoTen= cursor.getString(cursor.getColumnIndex("hoTen"));
            String namSinh = cursor.getString(cursor.getColumnIndex("namSinh"));
            ThanhVien thanhVien = new ThanhVien(maTV,hoTen,namSinh);
            list.add(String.valueOf(thanhVien.getMaTV()));
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return list;
    }

    public ThanhVien getAllMaThanhVien(int maThanhVien){
        List<ThanhVien> list = new ArrayList<>();
        String query = "SELECT * FROM ThanhVien WHERE maTV=?";
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query,new String[]{String.valueOf(maThanhVien)});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                int maTV = cursor.getInt(cursor.getColumnIndex("maTV"));
                String hoTen= cursor.getString(cursor.getColumnIndex("hoTen"));
                String namSinh = cursor.getString(cursor.getColumnIndex("namSinh"));
                ThanhVien thanhVien = new ThanhVien(maTV,hoTen,namSinh);
                list.add(thanhVien);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list.get(0);
    }
    public long insertThanhVien(ThanhVien thanhVien){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("maTV",thanhVien.getMaTV());
        values.put("hoTen",thanhVien.getHoTenTV());
        values.put("namSinh",thanhVien.getNamSinh());
        long row = database.insert("ThanhVien",null,values);
        return row;
    }
    public boolean updateThanhVien(ThanhVien thanhVien){
       // String sql = "update ThanhVien SET hoTen = ?,namSinh =? WHERE maTV =?";
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoTen",thanhVien.getHoTenTV());
        values.put("namSinh",thanhVien.getNamSinh());//cái sql đấy tôi để nhầm ấy
        long row = database.update("ThanhVien",values,
                "maTV=?",new String[]{String.valueOf(thanhVien.getMaTV())});// từ đợi tôi tí xem lại đonạ này phát
        return (row >0);
    }// đợi tôi xem nôi này là cái gì đã
    public boolean deleteThanhVien(int maTV){
        SQLiteDatabase database = this.dbHelper.getWritableDatabase();
        int row = database.delete("ThanhVien","maTV=?",new String[]{String.valueOf(maTV)});
        return  row >0;

    }
//    public boolean insertThanhVien(String tenTV, String date) {
//        SQLiteDatabase database =dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("hoTen",tenTV);
//        values.put("namSinh",date);
//        long row = database.insert("ThanhVien",null,values);
//
//        return (row > 0);
//    }
}
