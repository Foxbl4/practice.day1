package com.example.involtaday1.ui.database

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.fragment.app.FragmentActivity


class DatabaseHandler(context: FragmentActivity) : SQLiteOpenHelper(
    context,
    DB_NAME,
    null,
    DB_VERSION
) {

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

    fun getAllColumns(): MutableList<String> {
        val db = readableDatabase
        val listValues: MutableList<String> = mutableListOf()
        val selectALLQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val value = cursor.getString(cursor.getColumnIndex(COLUMN_VALUES))
                    val id = cursor.getString(cursor.getColumnIndex(COLUMN_ID))

                    listValues.add("$id. $value")

                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return listValues
    }

    fun delete(word: String){
        val db = writableDatabase
        Log.d(LOG_TAG, "--- Clear mytable: ---")
        db.execSQL("DELETE FROM InvoltaTable WHERE mValues='$word'")
    }

    fun find(word: String): String {

        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME, arrayOf(COLUMN_ID, COLUMN_VALUES), "$COLUMN_VALUES = ?",
            arrayOf(word), null, null, null
        )
        cursor.moveToFirst()

        val meanSearch = "${cursor.getString(cursor.getColumnIndex(COLUMN_ID))}. ${cursor.getString(
            cursor.getColumnIndex(COLUMN_VALUES))}"

        cursor.close()
        db.close()
        return meanSearch
    }

    fun lastElement(word: String): String{
        val db = readableDatabase
        val selectQuery = "SELECT  * FROM " + "InvoltaTable"
        val cursor: Cursor = db.rawQuery(selectQuery, null)
        cursor.moveToLast()
        val meanSearch = "${cursor.getString(cursor.getColumnIndex(COLUMN_ID))}. ${cursor.getString(
            cursor.getColumnIndex(
                COLUMN_VALUES
            )
        )}"
        return meanSearch
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

