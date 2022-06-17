package com.barut.unterkontenverwaltung.allgemein.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.barut.unterkontenverwaltung.recyclerview.EDirektModel

class SQLiteEDirekt(
    val context: Context, val DATABASENAME: String,
    val TABLENAME: String, val summe: String, val datum: String,
    val databaseType: String, val id: String, val beschreibung: String,val unterkonto : String
) : SQLiteOpenHelper(context, DATABASENAME, null, 7) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME +
                "(" +
                id + " INTEGER PRIMARY KEY," +
                summe + " VARCHAR " + "," +
                datum + " VARCHAR " + "," +
                unterkonto + " VARCHAR " + "," +
                beschreibung + " VARCHAR " + "," +
                databaseType + " VARCHAR" +

                ")"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (newVersion != oldVersion) {


        }
    }

    fun setData(model: EDirektModel) {
        val contenValue = ContentValues()
        val db = this.writableDatabase
        contenValue.put(summe, model.summe)
        contenValue.put(datum, model.datum)
        contenValue.put(databaseType, model.databaseType)
        contenValue.put(beschreibung, model.beschreibung)
        contenValue.put(unterkonto,model.unterkonto)
        db.insert(TABLENAME, null, contenValue)
    }

    @SuppressLint("Range")
    fun readData(): ArrayList<EDirektModel> {
        val dbLesen = this.readableDatabase
        val selection = "Select * From $TABLENAME"
        var model: EDirektModel
        var arraylist: ArrayList<EDirektModel> = arrayListOf()
        val cursor = dbLesen.rawQuery(selection, null)


        if (cursor.moveToFirst()) {
            do {
                model = EDirektModel(
                    cursor.getString(cursor.getColumnIndex(summe)),
                    cursor.getString(cursor.getColumnIndex(datum)),
                    cursor.getString(cursor.getColumnIndex(databaseType)),
                    cursor.getString(cursor.getColumnIndex(id)),
                    cursor.getString(cursor.getColumnIndex(beschreibung)),
                    cursor.getString(cursor.getColumnIndex(unterkonto))
                )

                arraylist.add(model)
            } while (cursor.moveToNext())
        }

        return arraylist
    }

    fun deleateItem(model: EDirektModel) {
        val db = this.writableDatabase
        for (eDirekt in readData()) {

            if (eDirekt.id == model.id && eDirekt.databaseType == model.databaseType
                && eDirekt.summe == model.summe && eDirekt.unterkonto == model.unterkonto) {
                db.delete(TABLENAME, this.id + "=" + eDirekt.id, null)

            }
        }
    }


    fun updateData(model: EDirektModel) {
        val contenValue = ContentValues()
        val db = this.writableDatabase
        contenValue.put(summe, model.summe)
        contenValue.put(datum, model.datum)
        contenValue.put(databaseType, model.databaseType)
        contenValue.put(beschreibung, model.beschreibung)
        contenValue.put(unterkonto,model.unterkonto)


        for (eDirekt in readData()) {
            if (model.summe == eDirekt.summe && model.databaseType == eDirekt.databaseType &&
                model.id == eDirekt.id
            ) {
                db.update(
                    TABLENAME,
                    contenValue,
                    this.id + "=" + eDirekt.id,
                    null
                )

            }
        }
    }
}