package com.barut.unterkontenverwaltung.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.barut.unterkontenverwaltung.recyclerview.UnterkontoModel

class SQliteUnterkonto(
    val context: Context, val DATABASENAME: String,
    val TABLENAME: String, val name: String, val prozent: String,
    val datum: String, val databaseType: String, val id: String, val beschreibung: String,
    ) : SQLiteOpenHelper(context, DATABASENAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME +
                "(" +
                id + " INTEGER PRIMARY KEY," +
                name + " VARCHAR " + "," +
                prozent + " VARCHAR " + "," +
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

    fun setData(model: UnterkontoModel) {
        val contenValue = ContentValues()
        val db = this.writableDatabase
        contenValue.put(name, model.name)
        contenValue.put(prozent, model.prozent)
        contenValue.put(datum, model.datum)
        contenValue.put(databaseType, model.databaseType)
        contenValue.put(beschreibung, model.beschreibung)
        db.insert(TABLENAME, null, contenValue)
    }

    @SuppressLint("Range")
    fun readData(): ArrayList<UnterkontoModel> {
        val dbLesen = this.readableDatabase
        val selection = "Select * From $TABLENAME"
        var model: UnterkontoModel
        var arraylist: ArrayList<UnterkontoModel> = arrayListOf()
        val cursor = dbLesen.rawQuery(selection, null)


        if (cursor.moveToFirst()) {
            do {
                model = UnterkontoModel(
                    cursor.getString(cursor.getColumnIndex(name)),
                    cursor.getString(cursor.getColumnIndex(prozent)),
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

    fun deleateItem(spaltenName1: String, spaltenName2: String) {
        val db = this.writableDatabase
        for (unterkontoNameForSchleife in readData()) {

            if (spaltenName1 == unterkontoNameForSchleife.name && spaltenName2 == unterkontoNameForSchleife.prozent) {
                db.delete(TABLENAME, this.id + "=" + unterkontoNameForSchleife.id, null)

            }
        }
    }

    fun deleteArgs(unterkontoName: String) {
        val db = this.writableDatabase
        for (unterkontoNameForSchleife in readData()) {
            if (unterkontoName == unterkontoNameForSchleife.prozent) {
                db.delete(TABLENAME, this.id + "=" + unterkontoNameForSchleife.id, null)

            }
        }
    }

    fun updateData(unterkontoName: String, model: UnterkontoModel) {
        val contenValue = ContentValues()
        val db = this.writableDatabase
        contenValue.put(name, model.name)
        contenValue.put(prozent, model.prozent)
        contenValue.put(datum, model.datum)
        contenValue.put(databaseType, model.databaseType)
        contenValue.put(beschreibung, model.beschreibung)


        for (unterkontoNameForSchleife in readData()) {
            if (unterkontoName == unterkontoNameForSchleife.prozent) {
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