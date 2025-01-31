package com.example.shopapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ProductDatabaseHelper2(context: Context) :
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
        val result = db.insert(TABLE_PRODUCTS, null, values)
        if (result == -1L) {
            Log.e("DB_ERROR", "Failed to insert product: $name")
        } else {
            Log.d("DB_SUCCESS", "Product inserted: $name")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTS")
        onCreate(db)
    }

    // Function to get all products and their prices
    fun getAllProducts(): List<Pair<String, Float>> {
        val db = readableDatabase
        val cursor: Cursor = db.query(
            TABLE_PRODUCTS, arrayOf(COLUMN_NAME, COLUMN_PRICE),
            null, null, null, null, null
        )

        val products = mutableListOf<Pair<String, Float>>()
        if (cursor.count == 0) {
            Log.e("DB_ERROR", "No products found in the database.")
        }

        while (cursor.moveToNext()) {
            // Get column indices safely
            val nameIndex = cursor.getColumnIndex(COLUMN_NAME)
            val priceIndex = cursor.getColumnIndex(COLUMN_PRICE)

            // Check if the column indices are valid
            if (nameIndex != -1 && priceIndex != -1) {
                val name = cursor.getString(nameIndex)
                val price = cursor.getFloat(priceIndex)
                products.add(Pair(name, price))
            }
        }
        cursor.close()
        return products
    }
}
