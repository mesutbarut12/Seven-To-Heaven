package com.barut.unterkontenverwaltung.json

import android.content.Context
import android.os.Environment
import android.widget.Toast
import java.io.*
import java.lang.Exception


private lateinit var fileOrdner: File
private lateinit var fileInhalt: File


class CreateTextFile {
    fun writeFile(filename: String, content: String, context: Context) {
        try {


            val path: File = context.filesDir
            val writer: FileOutputStream = FileOutputStream(File(path, filename))
            writer.write(content.toByteArray())
            writer.close()
            Toast.makeText(context,"File erstellt",Toast.LENGTH_LONG).show()
        } catch (e : Exception){

        }
    }
}
