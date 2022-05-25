package com.barut.unterkontenverwaltung.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.barut.unterkontenverwaltung.recyclerview.Model

class SQLiteMain(val context : Context,DATABASENAME : String,
                 val TABLENAME : String, val spaltenName1 : String, val spaltenName2 : String,
                 val echtZeitDatum : String,val databaseType : String,val id : String)
    : SQLiteOpenHelper(context,DATABASENAME,null,2) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME +
                "(" +
                id + " INTEGER PRIMARY KEY," +
                spaltenName1 + " VARCHAR "  + "," +
                spaltenName2 + " VARCHAR " + "," +
                echtZeitDatum + " VARCHAR " + "," +
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


    fun setData(model : Model){
        val contenValue = ContentValues()
        val db = this.writableDatabase
        contenValue.put(spaltenName1, model.spaltenName1)
        contenValue.put(spaltenName2, model.spaltenName2)
        contenValue.put(echtZeitDatum,model.datum)
        contenValue.put(databaseType,model.databaseType)
        db.insert(TABLENAME,null,contenValue)
    }
    @SuppressLint("Range")
    fun readData() : ArrayList<Model>{
        val dbLesen = this.readableDatabase
        val selection = "Select * From $TABLENAME"
        var model : Model
        var arraylist : ArrayList<Model> = arrayListOf()
        val cursor = dbLesen.rawQuery(selection,null)


        if(cursor.moveToFirst()){
            do {
                model = Model(cursor.getString(cursor.getColumnIndex(spaltenName1)),
                    cursor.getString(cursor.getColumnIndex(spaltenName2)),
                    cursor.getString(cursor.getColumnIndex(echtZeitDatum)),
                    cursor.getString(cursor.getColumnIndex(databaseType)),
                    cursor.getString(cursor.getColumnIndex(id)))
                arraylist.add(model)
            }while (cursor.moveToNext())
        }

        return arraylist
    }
    fun deleateItem(spaltenName1 : String, spaltenName2 : String){
        val db = this.writableDatabase
        for (unterkontoNameForSchleife in readData()) {

            println("Kommt vom SqliteDatabase : spaltenname1 = ${unterkontoNameForSchleife.spaltenName1} " +
                    "spaltenname2 = ${unterkontoNameForSchleife.spaltenName2}" +
                    "Id = ${unterkontoNameForSchleife.id}")
            println("------------------------------------------------")
            println("Kommt vom User Input : spaltenname1 = $spaltenName1 " +
                    "spaltenname2 =$spaltenName2")
            if(spaltenName1 == unterkontoNameForSchleife.spaltenName1 && spaltenName2 == unterkontoNameForSchleife.spaltenName2){

                //db.delete(TABLE_NAME, unterkontoName+"="+unterkontoNameForSchleife,null)
                //db.execSQL(" DELETE FROM $TABLENAME  WHERE ${this.spaltenName1}" + "=\"" + unterkontoNameForSchleife + "\";")
                println(this.spaltenName2)
                db.delete(TABLENAME,this.id + "=" + unterkontoNameForSchleife.id,null)

            }
        }
    }

}