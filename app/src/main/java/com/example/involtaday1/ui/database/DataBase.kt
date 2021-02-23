package com.example.involtaday1.ui.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented")
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d(LOG_TAG, "--- onCreate database ---")
        val createTable = "CREATE TABLE $TABLE_NAME($COLUMN_ID Integer PRIMARY KEY, $COLUMN_VALUES String)"
        db?.execSQL(createTable)
    }

    fun addValue(user: DBU): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COLUMN_VALUES, user.values)

        val success = db.insert(TABLE_NAME, null, values)
        db.close()
        Log.v("InsertedID", "$success")
        return (Integer.parseInt("$success") != -1)
    }

    fun getAllValues(): StringBuilder {
        val allValues = StringBuilder()
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val value = cursor.getString(cursor.getColumnIndex(COLUMN_VALUES))
                    val id = cursor.getString(cursor.getColumnIndex(COLUMN_ID))

                    allValues.append("$value ")

                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return allValues
    }

    fun getAllColumns(): StringBuilder {
        val allValues = StringBuilder()
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val value = cursor.getString(cursor.getColumnIndex(COLUMN_VALUES))
                    val id = cursor.getString(cursor.getColumnIndex(COLUMN_ID))

                    allValues.append("$id. $value\n")

                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return allValues
    }

    fun delete(){
        val db = writableDatabase
        Log.d(LOG_TAG, "--- Clear mytable: ---")
        // удаляем все записи
        val clearCount = db.delete(TABLE_NAME, null, null)
        Log.d(LOG_TAG, "deleted rows count = $clearCount")
    }

    companion object {
        internal const val LOG_TAG = "myLogs"
            private const val DB_NAME = "InvoltaBD"
            private const val DB_VERSION = 1
            private const val TABLE_NAME = "InvoltaTable"
            private const val COLUMN_ID = "mId"
            private const val COLUMN_VALUES = "mValues"
    }
}

