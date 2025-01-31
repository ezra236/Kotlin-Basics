package com.example.shopapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "admin.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_ADMIN = "admin"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableSQL = "CREATE TABLE $TABLE_ADMIN (" +
                "$COLUMN_USERNAME TEXT PRIMARY KEY," +
                "$COLUMN_PASSWORD TEXT)"
        db.execSQL(createTableSQL)

        // Insert initial admin credentials
        insertAdminCredentials(db, "Ezra", "123")
    }

    private fun insertAdminCredentials(db: SQLiteDatabase, username: String, password: String) {
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
        }
        db.insert(TABLE_ADMIN, null, values)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ADMIN")
        onCreate(db)
    }

    // Function to check admin credentials
    fun checkCredentials(username: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_ADMIN,
            arrayOf(COLUMN_USERNAME, COLUMN_PASSWORD),
            "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?",
            arrayOf(username, password),
            null, null, null
        )

        return cursor?.moveToFirst() == true
    }
}
