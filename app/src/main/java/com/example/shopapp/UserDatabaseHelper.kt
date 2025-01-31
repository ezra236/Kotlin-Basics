package com.example.shopapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user_db"
        private const val DATABASE_VERSION = 1
        const val TABLE_USERS = "users"
        const val COLUMN_NAME = "name"
        const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableSQL = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_NAME TEXT PRIMARY KEY,
                $COLUMN_PASSWORD TEXT
            )
        """
        db.execSQL(createTableSQL)

        // Insert sample users into the database
        insertUser(db, "Ezra", "123")
        insertUser(db, "Monica", "12q")
    }

    private fun insertUser(db: SQLiteDatabase, name: String, password: String) {
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_PASSWORD, password)
        }
        db.insert(TABLE_USERS, null, values)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    // Function to validate user credentials
    fun validateUser(name: String, password: String): Boolean {
        val db = readableDatabase
        val cursor: Cursor = db.query(
            TABLE_USERS,
            arrayOf(COLUMN_NAME, COLUMN_PASSWORD),
            "$COLUMN_NAME = ? AND $COLUMN_PASSWORD = ?",
            arrayOf(name, password),
            null, null, null
        )

        val isValid = cursor.count > 0
        cursor.close()
        return isValid
    }
}
