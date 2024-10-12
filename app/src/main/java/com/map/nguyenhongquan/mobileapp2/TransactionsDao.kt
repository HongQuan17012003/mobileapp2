package com.map.nguyenhongquan.mobileapp2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class TransactionsDao(context: Context,private val dbHelper: DatabaseHelper) {

    // Hàm thêm giao dịch mới
    fun addTransaction(transaction: Transaction): Boolean {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", transaction.name)
            put("idCateInOut", transaction.idCateInOut)
            put("amount", transaction.amount)
            put("date", transaction.date)
            put("note", transaction.note)
        }
        val result = db.insert("Transactions", null, values)
        db.close()
        return result != -1L // Trả về true nếu thêm thành công, false nếu thất bại
    }

    // Hàm chỉnh sửa giao dịch
    fun editTransaction(transaction: Transaction): Boolean {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", transaction.name)
            put("idCateInOut", transaction.idCateInOut)
            put("amount", transaction.amount)
            put("date", transaction.date)
            put("note", transaction.note)
        }
        val result = db.update("Transactions", values, "id = ?", arrayOf(transaction.id.toString()))
        db.close()
        return result > 0 // Trả về true nếu cập nhật thành công, false nếu không có hàng nào được cập nhật
    }

    // Hàm xóa giao dịch
    fun deleteTransaction(id: Int): Boolean {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val result = db.delete("Transactions", "id = ?", arrayOf(id.toString()))
        db.close()
        return result > 0 // Trả về true nếu xóa thành công, false nếu không có hàng nào được xóa
    }

    // Hàm tìm kiếm giao dịch theo ngày
    fun searchTransactionsByDate(date: String): List<Transaction> {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor = db.query(
            "Transactions",
            null,
            "date = ?",
            arrayOf(date),
            null,
            null,
            null
        )

        val transactions = mutableListOf<Transaction>()
        with(cursor) {
            while (moveToNext()) {
                val transaction = Transaction(
                    id = getInt(getColumnIndexOrThrow("id")),
                    name = getString(getColumnIndexOrThrow("name")),
                    idCateInOut = getInt(getColumnIndexOrThrow("idCateInOut")),
                    amount = getDouble(getColumnIndexOrThrow("amount")),
                    date = getString(getColumnIndexOrThrow("date")),
                    note = getString(getColumnIndexOrThrow("note"))
                )
                transactions.add(transaction)
            }
        }
        cursor.close()
        db.close()
        return transactions
    }
}
