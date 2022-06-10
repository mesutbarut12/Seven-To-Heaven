package com.barut.unterkontenverwaltung.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.barut.unterkontenverwaltung.recyclerview.AusgabenModel

class SQliteAusgaben(
    val context: Context, val DATABASENAME: String,
    val TABLENAME: String, val name: String, val summe: String,
    val datum: String, val databaseType: String, val id: String, val beschreibung: String,
) : SQLiteOpenHelper(context, DATABASENAME, null, 9) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME +
                "(" +
                id + " INTEGER PRIMARY KEY," +
                name + " VARCHAR " + "," +
                summe + " VARCHAR " + "," +
                datum + " VARCHAR " + "," +
                databaseType + " VARCHAR " + "," +
                beschreibung + " VARCHAR" +

                ")"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (newVersion != oldVersion) {
            //db!!.execSQL("ALTER TABLE $DATABASENAME RENAME COLUMN datum TO leer")

        }
    }

    fun setData(model: AusgabenModel) {
        val contenValue = ContentValues()
        val db = this.writableDatabase
        println(model.summe)
        contenValue.put(name, model.unterkonto)
        contenValue.put(summe, model.summe)
        contenValue.put(datum, model.datum)
        contenValue.put(databaseType, model.databaseType)
        contenValue.put(beschreibung, model.beschreibung)
        db.insert(TABLENAME, null, contenValue)
    }

    @SuppressLint("Range")
    fun readData(): ArrayList<AusgabenModel> {
        val dbLesen = this.readableDatabase
        val selection = "Select * From $TABLENAME"
        var model: AusgabenModel
        var arraylist: ArrayList<AusgabenModel> = arrayListOf()
        val cursor = dbLesen.rawQuery(selection, null)


        if (cursor.moveToFirst()) {
            do {
                model = AusgabenModel(
                    cursor.getString(cursor.getColumnIndex(name)),
                    cursor.getString(cursor.getColumnIndex(summe)),
                    cursor.getString(cursor.getColumnIndex(datum)),
                    cursor.getString(cursor.getColumnIndex(databaseType)),
                    cursor.getString(cursor.getColumnIndex(id)),
                    cursor.getString(cursor.getColumnIndex(beschreibung))
                )

                arraylist.add(model)
            } while (cursor.moveToNext())
        }

        return arraylist
    }

    fun deleateItem(model: AusgabenModel) {
        val db = this.writableDatabase
        for (ausgaben in readData()) {

            if (ausgaben.id == model.id && ausgaben.databaseType == model.databaseType && ausgaben.summe == model.summe) {
                db.delete(TABLENAME, this.id + "=" + ausgaben.id, null)

            }
        }
    }


    fun updateData(model: AusgabenModel) {
        val contenValue = ContentValues()
        val db = this.writableDatabase
        contenValue.put(summe, model.summe)
        contenValue.put(datum, model.datum)
        contenValue.put(databaseType, model.databaseType)
        contenValue.put(beschreibung, model.beschreibung)


        for (ausgaben in readData()) {
            if (model.summe == ausgaben.summe && model.databaseType == ausgaben.databaseType &&
                model.id == ausgaben.id
            ) {
                db.update(
                    TABLENAME,
                    contenValue,
                    this.id + "=" + ausgaben.id,
                    null
                )

            }
        }
    }
}