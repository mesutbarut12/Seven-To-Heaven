package com.barut.unterkontenverwaltung.allgemein.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.barut.unterkontenverwaltung.recyclerview.UserIdModel

class SQliteUserID(
    val context: Context, val DATABASENAME: String,
    val TABLENAME: String, val id: String
) : SQLiteOpenHelper(context, DATABASENAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME +
                "(" +

                id + " VARCHAR" +

                ")"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (newVersion != oldVersion) {
            println("upgrde")
            //db!!.execSQL("ALTER TABLE $DATABASENAME ADD COLUMN ${userInputDatum}")
            //db!!.execSQL("ALTER TABLE $DATABASENAME RENAME COLUMN datum TO leer")

        }
    }

    fun setData(model: UserIdModel) {
        val contenValue = ContentValues()
        val db = this.writableDatabase
        contenValue.put(id, model.id)
        db.insert(TABLENAME, null, contenValue)
    }

    @SuppressLint("Range")
    fun readData(): ArrayList<UserIdModel> {
        val dbLesen = this.readableDatabase
        val selection = "Select * From $TABLENAME"
        var model: UserIdModel
        var arraylist: ArrayList<UserIdModel> = arrayListOf()
        val cursor = dbLesen.rawQuery(selection, null)


        if (cursor.moveToFirst()) {
            do {
                model = UserIdModel(
                    cursor.getString(cursor.getColumnIndex(id)),
                )

                arraylist.add(model)
            } while (cursor.moveToNext())
        }

        return arraylist
    }

    fun deleateItem(model: UserIdModel) {
        val db = this.writableDatabase
        for (userID in readData()) {

            if (userID.id == model.id) {
                db.delete(TABLENAME, this.id + "=" + userID.id, null)

            }
        }
    }


    fun updateData(model: UserIdModel) {
        val contenValue = ContentValues()
        val db = this.writableDatabase
        contenValue.put(id, model.id)



        for (userID in readData()) {
            if (
                model.id == userID.id
            ) {
                db.update(
                    TABLENAME,
                    contenValue,
                    this.id + "=" + userID.id,
                    null
                )

            }
        }
    }
}