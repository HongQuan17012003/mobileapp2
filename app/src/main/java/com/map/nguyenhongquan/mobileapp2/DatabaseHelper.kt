package com.map.nguyenhongquan.mobileapp2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "hong_quan.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Tạo các bảng
        db.execSQL("""
            CREATE TABLE InOut (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL
            )
        """)
        db.execSQL("""
            CREATE TABLE Category (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                idParent INTEGER,
                icon TEXT,
                note TEXT,
                FOREIGN KEY (idParent) REFERENCES Category(id)
            )
        """)
        db.execSQL("""
            CREATE TABLE CatInOut (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                idCat INTEGER NOT NULL,
                idInOut INTEGER NOT NULL,
                FOREIGN KEY (idCat) REFERENCES Category(id),
                FOREIGN KEY (idInOut) REFERENCES InOut(id)
            )
        """)
        db.execSQL("""
            CREATE TABLE Transactions (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                idCateInOut INTEGER NOT NULL,
                amount REAL NOT NULL,
                date TEXT NOT NULL,
                note TEXT,
                FOREIGN KEY (idCateInOut) REFERENCES CatInOut(id)
            )
        """)

        // Chèn dữ liệu mẫu cho bảng InOut
        db.execSQL("INSERT INTO InOut (id, name) VALUES (1, 'thu')")
        db.execSQL("INSERT INTO InOut (id, name) VALUES (2, 'chi')")

        // Chèn dữ liệu mẫu cho bảng Category
        db.execSQL("INSERT INTO Category (id, name, idParent, icon, note) VALUES (1, 'lương', NULL, NULL, NULL)")
        db.execSQL("INSERT INTO Category (id, name, idParent, icon, note) VALUES (2, 'làm thêm', NULL, NULL, NULL)")
        db.execSQL("INSERT INTO Category (id, name, idParent, icon, note) VALUES (3, 'học bổng', NULL, NULL, NULL)")
        db.execSQL("INSERT INTO Category (id, name, idParent, icon, note) VALUES (4, 'bố mẹ cho', NULL, NULL, NULL)")
        db.execSQL("INSERT INTO Category (id, name, idParent, icon, note) VALUES (5, 'quà tặng', NULL, NULL, NULL)")
        db.execSQL("INSERT INTO Category (id, name, idParent, icon, note) VALUES (6, 'học phí', NULL, NULL, NULL)")
        db.execSQL("INSERT INTO Category (id, name, idParent, icon, note) VALUES (7, 'chi tiêu hằng ngày', NULL, NULL, NULL)")
        db.execSQL("INSERT INTO Category (id, name, idParent, icon, note) VALUES (8, 'tiền nhà', 7, NULL, NULL)")
        db.execSQL("INSERT INTO Category (id, name, idParent, icon, note) VALUES (9, 'tiền điện', 7, NULL, NULL)")
        db.execSQL("INSERT INTO Category (id, name, idParent, icon, note) VALUES (10, 'tiền nước', 7, NULL, NULL)")
        db.execSQL("INSERT INTO Category (id, name, idParent, icon, note) VALUES (11, 'tiền điện thoại', 7, NULL, NULL)")
        db.execSQL("INSERT INTO Category (id, name, idParent, icon, note) VALUES (12, 'tiền ăn', 7, NULL, NULL)")
        db.execSQL("INSERT INTO Category (id, name, idParent, icon, note) VALUES (13, 'tiền đi chợ', 7, NULL, NULL)")
        db.execSQL("INSERT INTO Category (id, name, idParent, icon, note) VALUES (14, 'hiếu hỉ', NULL, NULL, NULL)")

        // Chèn dữ liệu mẫu cho bảng CatInOut
        db.execSQL("INSERT INTO CatInOut (id, idCat, idInOut) VALUES (1, 1, 1)")
        db.execSQL("INSERT INTO CatInOut (id, idCat, idInOut) VALUES (2, 2, 1)")
        db.execSQL("INSERT INTO CatInOut (id, idCat, idInOut) VALUES (3, 3, 1)")
        db.execSQL("INSERT INTO CatInOut (id, idCat, idInOut) VALUES (4, 4, 1)")
        db.execSQL("INSERT INTO CatInOut (id, idCat, idInOut) VALUES (5, 5, 1)")
        db.execSQL("INSERT INTO CatInOut (id, idCat, idInOut) VALUES (6, 6, 2)")
        db.execSQL("INSERT INTO CatInOut (id, idCat, idInOut) VALUES (7, 7, 2)")
        db.execSQL("INSERT INTO CatInOut (id, idCat, idInOut) VALUES (8, 8, 2)")
        db.execSQL("INSERT INTO CatInOut (id, idCat, idInOut) VALUES (9, 9, 2)")
        db.execSQL("INSERT INTO CatInOut (id, idCat, idInOut) VALUES (10, 10, 2)")
        db.execSQL("INSERT INTO CatInOut (id, idCat, idInOut) VALUES (11, 11, 2)")
        db.execSQL("INSERT INTO CatInOut (id, idCat, idInOut) VALUES (12, 12, 2)")
        db.execSQL("INSERT INTO CatInOut (id, idCat, idInOut) VALUES (13, 13, 2)")
        db.execSQL("INSERT INTO CatInOut (id, idCat, idInOut) VALUES (14, 14, 2)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS InOut")
        db.execSQL("DROP TABLE IF EXISTS Category")
        db.execSQL("DROP TABLE IF EXISTS CatInOut")
        db.execSQL("DROP TABLE IF EXISTS Transactions")
        onCreate(db)
    }
}
