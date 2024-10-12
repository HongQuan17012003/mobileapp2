package com.map.nguyenhongquan.mobileapp2

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class CategoryDao(context: Context,private val dbHelper: DatabaseHelper) {
    // Hàm tìm kiếm danh mục dựa trên InOut (thu hoặc chi)
    fun searchIntOut(i: Boolean): List<Category> {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val idInOut = if (i) 1 else 2 // Nếu i là true thì tìm danh mục 'thu', ngược lại tìm danh mục 'chi'

        // Câu truy vấn lấy danh mục dựa trên giá trị idInOut
        val cursor: Cursor = db.rawQuery(
            """
            SELECT Category.id, Category.name, Category.idParent, Category.icon, Category.note
            FROM Category
            INNER JOIN CatInOut ON Category.id = CatInOut.idCat
            WHERE CatInOut.idInOut = ?
            """, arrayOf(idInOut.toString())
        )

        val categories = mutableListOf<Category>()
        with(cursor) {
            while (moveToNext()) {
                val category = Category(
                    id = getInt(getColumnIndexOrThrow("id")),
                    name = getString(getColumnIndexOrThrow("name")),
                    idParent = getInt(getColumnIndexOrThrow("idParent")),
                    icon = getString(getColumnIndexOrThrow("icon")),
                    note = getString(getColumnIndexOrThrow("note"))
                )
                categories.add(category)
            }
        }
        cursor.close()
        db.close()
        return categories
    }
}