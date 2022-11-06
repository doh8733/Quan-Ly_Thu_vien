package com.example.duanmau_android_mobile.DAo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_android_mobile.Database.DBHelper;
import com.example.duanmau_android_mobile.model.Sach;
import com.example.duanmau_android_mobile.model.Top10Sach;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    DBHelper createData;
    SQLiteDatabase liteDatabase;
    Context context;

    public ThongKeDAO(Context context) {
        this.context = context;
        createData = new DBHelper(context);
        liteDatabase = createData.getWritableDatabase();
    }

    public List<Top10Sach> GetTop() {
        // gới hạn 10 kết quả từ trên xuống
        String sql = "SELECT maSach , COUNT(maSach) AS soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";
        List<Top10Sach> list = new ArrayList<>();
        SachDAO sachDao = new SachDAO(context);
        Cursor cursor = liteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Top10Sach top = new Top10Sach();
            Sach sach = sachDao.getId(cursor.getString(cursor.getColumnIndex("maSach")));
            top.tenSach= sach.getTenSach();
            top.soLuong=(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            list.add(top);
        }
        return list;
    }
    public int getDoanhThu(String tuNgay,String denNgay){
        String sql =  "SELECT SUM(tienThue) as doanhthu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor cursor = liteDatabase.rawQuery(sql,new String[]{tuNgay,denNgay});
            while (cursor.moveToNext()){
                try{
                    String doanhThu = cursor.getString(cursor.getColumnIndex("doanhthu"));
                    list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhthu"))));
                }catch (Exception e){
                    list.add(0);
                }

            }
            return list.get(0);
    }
}
