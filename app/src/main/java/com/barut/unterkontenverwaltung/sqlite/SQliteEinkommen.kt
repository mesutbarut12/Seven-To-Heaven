package com.barut.unterkontenverwaltung.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.barut.unterkontenverwaltung.recyclerview.EinkommenModel

class SQliteEinkommen(
    val context: Context, val DATABASENAME: String,
    val TABLENAME: String, val summe: String, val datum: String,
    val databaseType: String, val id: String, val beschreibung: String,
) : SQLiteOpenHelper(context, DATABASENAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME +
                "(" +
                id + " INTEGER PRIMARY KEY," +
                summe + " VARCHAR " + "," +
                datum + " VARCHAR " + "," +
                beschreibung + " VARCHAR " + "," +
                databaseType + " VARCHAR" +

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

    fun setData(model: EinkommenModel) {
        val contenValue = ContentValues()
        val db = this.writableDatabase
        contenValue.put(summe, model.summe)
        contenValue.put(datum, model.datum)
        contenValue.put(databaseType, model.databaseType)
        contenValue.put(beschreibung, model.beschreibung)
        db.insert(TABLENAME, null, contenValue)
    }

    @SuppressLint("Range")
    fun readData(): ArrayList<EinkommenModel> {
        val dbLesen = this.readableDatabase
        val selection = "Select * From $TABLENAME"
        var model: EinkommenModel
        var arraylist: ArrayList<EinkommenModel> = arrayListOf()
        val cursor = dbLesen.rawQuery(selection, null)


        if (cursor.moveToFirst()) {
            do {
                model = EinkommenModel(
                    cursor.getString(cursor.getColumnIndex(summe)),
                    cursor.getString(cursor.getColumnIndex(datum)),
                    cursor.getString(cursor.getColumnIndex(databaseType)),
                    cursor.getString(cursor.getColumnIndex(id)),
                    cursor.getString(cursor.getColumnIndex(beschreibung)),
                )

                arraylist.add(model)
            } while (cursor.moveToNext())
        }

        return arraylist
    }

    fun deleateItem(model : EinkommenModel) {
        val db = this.writableDatabase
        for (einkommen in readData()) {

            if (einkommen.id == model.id && einkommen.databaseType == model.databaseType && einkommen.summe == model.summe) {
                db.delete(TABLENAME, this.id + "=" + einkommen.id, null)

            }
        }
    }


    fun updateData(model: EinkommenModel) {
        val contenValue = ContentValues()
        val db = this.writableDatabase
        contenValue.put(summe, model.summe)
        contenValue.put(datum, model.datum)
        contenValue.put(databaseType, model.databaseType)
        contenValue.put(beschreibung, model.beschreibung)


        for (einkommen in readData()) {
            if (model.summe == einkommen.summe && model.databaseType == einkommen.databaseType &&
                model.id == einkommen.id
            ) {
                db.update(
                    TABLENAME,
                    contenValue,
                    this.id + "=" + einkommen.id,
                    null
                )

            }
        }
    }
}