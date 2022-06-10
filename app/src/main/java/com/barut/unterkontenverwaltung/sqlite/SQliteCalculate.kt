package com.barut.unterkontenverwaltung.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.barut.unterkontenverwaltung.recyclerview.CalculateSqlModel
import com.barut.unterkontenverwaltung.recyclerview.EinkommenModel

class SQliteCalculate(
    val context: Context, val DATABASENAME: String,
    val TABLENAME: String, val unterkonto: String, val prozent: String,
    val ausgaben: String, val guthaben: String, val ergebnis: String, val id: String
) : SQLiteOpenHelper(context, DATABASENAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME +
                "(" +
                id + " INTEGER PRIMARY KEY," +
                unterkonto + " VARCHAR " + "," +
                prozent + " VARCHAR " + "," +
                ausgaben + " VARCHAR " + "," +
                guthaben + " VARCHAR " + "," +
                ergebnis + " VARCHAR" +

                ")"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (newVersion != oldVersion) {
            val createTable = "CREATE TABLE " + TABLENAME +
                    "(" +
                    id + " INTEGER PRIMARY KEY," +
                    unterkonto + " VARCHAR " + "," +
                    prozent + " VARCHAR " + "," +
                    ausgaben + " VARCHAR " + "," +
                    guthaben + " VARCHAR " + "," +
                    ergebnis + " VARCHAR" +

                    ")"
            db?.execSQL(createTable)

        }
    }

    fun setData(model: CalculateSqlModel) {
        val contenValue = ContentValues()
        val db = this.writableDatabase
        contenValue.put(unterkonto, model.unterkonto)
        contenValue.put(prozent, model.prozent)
        contenValue.put(ausgaben, model.ausgaben)
        contenValue.put(guthaben, model.guthaben)
        contenValue.put(ergebnis, model.ergebnis)
        db.insert(TABLENAME, null, contenValue)
    }

    @SuppressLint("Range")
    fun readData(): ArrayList<CalculateSqlModel> {
        val dbLesen = this.readableDatabase
        val selection = "Select * From $TABLENAME"
        var model: CalculateSqlModel
        var arraylist: ArrayList<CalculateSqlModel> = arrayListOf()
        val cursor = dbLesen.rawQuery(selection, null)


        if (cursor.moveToFirst()) {
            do {
                model = CalculateSqlModel(
                    cursor.getString(cursor.getColumnIndex(unterkonto)),
                    cursor.getString(cursor.getColumnIndex(prozent)),
                    cursor.getString(cursor.getColumnIndex(ausgaben)),
                    cursor.getString(cursor.getColumnIndex(guthaben)),
                    cursor.getString(cursor.getColumnIndex(ergebnis)),
                    cursor.getString(cursor.getColumnIndex(id))
                )

                arraylist.add(model)
            } while (cursor.moveToNext())
        }

        return arraylist
    }

    fun deleateItem(model: CalculateSqlModel) {
        val db = this.writableDatabase
        for (einkommen in readData()) {

            if (einkommen.id == model.id && einkommen.unterkonto == model.unterkonto
                && einkommen.guthaben == model.guthaben && einkommen.ergebnis == model.ergebnis
            ) {
                db.delete(TABLENAME, this.id + "=" + einkommen.id, null)

            }
        }
    }


    fun updateData(model: CalculateSqlModel) {
        val contenValue = ContentValues()
        val db = this.writableDatabase
        contenValue.put(unterkonto, model.unterkonto)
        contenValue.put(prozent, model.prozent)
        contenValue.put(ausgaben, model.ausgaben)
        contenValue.put(ergebnis, model.ergebnis)


        for (einkommen in readData()) {
            if (einkommen.id == model.id && einkommen.unterkonto == model.unterkonto
                && einkommen.guthaben == model.guthaben && einkommen.ergebnis == model.ergebnis
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