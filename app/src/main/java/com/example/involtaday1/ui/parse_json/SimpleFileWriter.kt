package com.example.involtaday1.ui.parse_json

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream

class SimpleFileWriter(private val context: Context) {
    fun writeFileToExternalCache(folderName: String, fileName: String, text: String): Boolean {
        var path = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.absolutePath
        path?.let {
            path += File.separator + folderName + File.separator

            val fileDirectory = File(path)
            fileDirectory.mkdirs()

            val file = File(path, fileName)

            val outputStream = FileOutputStream(file, true)
            outputStream.write(text.toByteArray(Charsets.UTF_8))
            outputStream.close()

            Log.d(ContentValues.TAG, "File $file created")

            return true
        }

        return false
    }
}