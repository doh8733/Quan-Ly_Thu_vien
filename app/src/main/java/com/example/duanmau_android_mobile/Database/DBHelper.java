package com.example.duanmau_android_mobile.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "huydqph142813.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableThuThu="create table ThuThu(" +
                "maTT TEXT PRIMARY KEY ," +// chô này ông để text
                "hoTenTT TEXT NOT NULL," +
                "matKhau TEXT NOT NULL)";
        db.execSQL(createTableThuThu);
//        db.execSQL("INSERT INTO ThuThu VALUES (\"TT1\",\"DO QUOC HUY\",\"12345\")");
//        db.execSQL("INSERT INTO ThuThu VALUES (\"TT2\",\"DO Huy\",\"123\")");
//        db.execSQL("INSERT INTO ThuThu VALUES (\"TT3\",\"Quoc Huy\",\"1234\")");

        String createTableThanhVien="create table ThanhVien(" +
                "maTV INTEGER  PRIMARY KEY AUTOINCREMENT," +
                "hoTen TEXT NOT NULL," +
                "namSinh INTEGER NOT NULL)";
        db.execSQL(createTableThanhVien);
//        db.execSQL("INSERT INTO ThanhVien VALUES (null,\"DO QUOC HUY\",\"2002-11-27\")");
//        db.execSQL("INSERT INTO ThanhVien VALUES (null,\"DO Huy\",\"2003-12-28\")");
//        db.execSQL("INSERT INTO ThanhVien VALUES (null,\"Quoc Huy\",\"2004-11-10\")");

        String createTableLoaiSach=
                "create table LoaiSach(" +
                        "maLoai INTEGER  PRIMARY KEY AUTOINCREMENT," +
                        "tenLoai TEXT NOT NULL)";
        db.execSQL(createTableLoaiSach);

        db.execSQL("INSERT INTO LoaiSach VALUES(null,\"Sach Tin hoc\")");
        db.execSQL("INSERT INTO LoaiSach VALUES(null,\"Sach a\")");
        db.execSQL("INSERT INTO LoaiSach VALUES(null,\"Sach b\")");



        String createTableSach="create table Sach(" +
                "maSach INTEGER  PRIMARY KEY AUTOINCREMENT," +
                "TenSach TEXT NOT NULL," +
                "giaThue INTEGER NOT NULL," +
                "giaKhuyenMai INTEGER NOT NULL," +
                "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
        db.execSQL(createTableSach);

//oi diem danh mon t.anh cai da ong//tiep di ong oi ok
        String createTablePhieuMuon="create table PhieuMuon(" +
                "maPM INTEGER  PRIMARY KEY AUTOINCREMENT," +
                "maTT INTEGER REFERENCES ThuThu(maTT)," +
                "maTV INTEGER REFERENCES ThanhVien(maTV)," +
                "maSach INTERGER REFERENCES Sach(maSach)," +
                "tienThue INTERGER NOT NULL," +
                "ngay DATE NOT NULL," +
                "traSach INTEGER NOT NULL)";

        db.execSQL(createTablePhieuMuon);
//        db.execSQL("INSERT INTO PhieuMuon VALUES (null,\"TT1\",1,1,50000,\"2002-11-27\",1)");
//        db.execSQL("INSERT INTO PhieuMuon VALUES (null,\"TT2\",2,1,50000,\"2002-11-27\",0)");
//        db.execSQL("INSERT INTO PhieuMuon VALUES (null,\"TT3\",3,1,50000,\"2002-11-27\",1)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableLoaiThuThu = "drop table if exists ThuThu";
        db.execSQL(dropTableLoaiThuThu);
        String dropTableThanhVien= "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db);
    }
}
