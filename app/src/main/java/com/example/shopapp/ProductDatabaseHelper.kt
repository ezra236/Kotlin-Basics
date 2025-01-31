package com.example.shopapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProductDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "shop.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_PRODUCTS = "products"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PRICE = "price"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableSQL = "CREATE TABLE $TABLE_PRODUCTS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_PRICE REAL)"
        db.execSQL(createTableSQL)

        // Insert sample products into the database
        insertProduct(db, "Apple", 15f)
        insertProduct(db, "Banana", 20f)
    }

    private fun insertProduct(db: SQLiteDatabase, name: String, price: Float) {
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_PRICE, price)
        }
        db.insert(TABLE_PRODUCTS, null, values)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTS")
        onCreate(db)
    }

    // Function to get the product price by name
    fun getProductPrice(productName: String): Float? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_PRODUCTS, arrayOf(COLUMN_PRICE),
            "$COLUMN_NAME = ?", arrayOf(productName),
            null, null, null
        )

        cursor.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndex(COLUMN_PRICE)
                if (columnIndex != -1) {
                    return it.getFloat(columnIndex) // Return the price
                }
            }
        }
        return null // Return null if no valid price is found
    }
}
