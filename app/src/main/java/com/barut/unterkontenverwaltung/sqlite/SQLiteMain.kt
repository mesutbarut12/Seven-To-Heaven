package com.barut.unterkontenverwaltung.sqlite

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.barut.unterkontenverwaltung.recyclerview.Model

class SQLiteMain(
    val context: Context, DATABASENAME: String,
    val TABLENAME: String, val spaltenName1: String, val spaltenName2: String,
    val echtZeitDatum: String, val databaseType: String, val id: String, val beschreibung: String
) : SQLiteOpenHelper(context, DATABASENAME, null, 2) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME +
                "(" +
                id + " INTEGER PRIMARY KEY," +
                spaltenName1 + " VARCHAR " + "," +
                spaltenName2 + " VARCHAR " + "," +
                echtZeitDatum + " VARCHAR " + "," +
                beschreibung + " VARCHAR " + "," +
                databaseType + " VARCHAR" +
                ")"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db?.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
            onCreate(db);
        }
    }


    fun setData(model: Model) {
        val contenValue = ContentValues()
        val db = this.writableDatabase
        contenValue.put(spaltenName1, model.spaltenName1)
        contenValue.put(spaltenName2, model.spaltenName2)
        contenValue.put(echtZeitDatum, model.datum)
        contenValue.put(databaseType, model.databaseType)
        contenValue.put(beschreibung, model.beschreibung)
        db.insert(TABLENAME, null, contenValue)
    }

    @SuppressLint("Range")
    fun readData(): ArrayList<Model> {
        val dbLesen = this.readableDatabase
        val selection = "Select * From $TABLENAME"
        var model: Model
        var arraylist: ArrayList<Model> = arrayListOf()
        val cursor = dbLesen.rawQuery(selection, null)


        if (cursor.moveToFirst()) {
            do {
                model = Model(
                    cursor.getString(cursor.getColumnIndex(spaltenName1)),
                    cursor.getString(cursor.getColumnIndex(spaltenName2)),
                    cursor.getString(cursor.getColumnIndex(echtZeitDatum)),
                    cursor.getString(cursor.getColumnIndex(databaseType)),
                    cursor.getString(cursor.getColumnIndex(id)),
                    cursor.getString(cursor.getColumnIndex(beschreibung)))
                arraylist.add(model)
            } while (cursor.moveToNext())
        }

        return arraylist
    }

    fun deleateItem(spaltenName1: String, spaltenName2: String) {
        val db = this.writableDatabase
        for (unterkontoNameForSchleife in readData()) {

            if (spaltenName1 == unterkontoNameForSchleife.spaltenName1 && spaltenName2 == unterkontoNameForSchleife.spaltenName2) {
                db.delete(TABLENAME, this.id + "=" + unterkontoNameForSchleife.id, null)

            }
        }
    }

    fun deleteArgs(unterkontoName: String) {
        val db = this.writableDatabase
        for (unterkontoNameForSchleife in readData()) {
            if (unterkontoName == unterkontoNameForSchleife.spaltenName2) {
                db.delete(TABLENAME, this.id + "=" + unterkontoNameForSchleife.id, null)

            }
        }
    }

    fun updateData(unterkontoName: String, model: Model) {
        val contenValue = ContentValues()
        val db = this.writableDatabase
        contenValue.put(spaltenName1, model.spaltenName1)
        contenValue.put(spaltenName2, model.spaltenName2)
        contenValue.put(echtZeitDatum, model.datum)
        contenValue.put(databaseType, model.databaseType)
        contenValue.put(beschreibung,model.beschreibung)


        for (unterkontoNameForSchleife in readData()) {
            if (unterkontoName == unterkontoNameForSchleife.spaltenName2) {
                db.update(
                    TABLENAME,
                    contenValue,
                    this.id + "=" + unterkontoNameForSchleife.id,
                    null
                )

            }
        }
    }
}