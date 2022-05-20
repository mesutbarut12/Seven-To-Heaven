package com.barut.unterkontenverwaltung.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class SQLiteMain(val context : Context,val DATABASENAME : String,
                 val TABLENAME : String, val spaltenName1 : String, val spaltenName2 : String,val id : String) : SQLiteOpenHelper(context,DATABASENAME,null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME +
                "(" +
                id + " INTEGER PRIMARY KEY," +  // Define a primary key
                spaltenName1 + " VARCHAR "  + "," +  // Define a foreign key
                spaltenName2 + " VARCHAR" +
                ")"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db?.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
            onCreate(db);
        }
    }
    fun setData(model : SQLiteModel){
        val contenValue = ContentValues()
        val db = this.writableDatabase
        contenValue.put(spaltenName1, model.spaltenName1)
        contenValue.put(spaltenName2, model.spaltenName2)
        db.insert(TABLENAME,null,contenValue)
    }


    @SuppressLint("Range")
    fun readData() : ArrayList<SQLiteModel>{
        val dbLesen = this.readableDatabase
        val selection = "Select * From $TABLENAME"
        var model : SQLiteModel
        var arraylist : ArrayList<SQLiteModel> = arrayListOf()
        val cursor = dbLesen.rawQuery(selection,null)


        if(cursor.moveToFirst()){
            do {
                model = SQLiteModel(cursor.getString(cursor.getColumnIndex(spaltenName1)),
                    cursor.getString(cursor.getColumnIndex(spaltenName2)))
                arraylist.add(model)
            }while (cursor.moveToNext())
        }

        return arraylist
    }

    fun deleateItem(spaltenName1 : String, spaltenName2 : String){
        val db = this.writableDatabase
        for ((unterkontoNameForSchleife,unterkontoProzentForSchleife) in readData()) {

            if(spaltenName1 in unterkontoNameForSchleife && spaltenName2 in unterkontoProzentForSchleife){

                //db.delete(TABLE_NAME, unterkontoName+"="+unterkontoNameForSchleife,null)
                db.execSQL(" DELETE FROM $TABLENAME  WHERE ${this.spaltenName1}" + "=\"" + unterkontoNameForSchleife + "\";")

            }
        }
    }
    fun deleateTable(){
        val db = this.writableDatabase
        //db.delete(DATABASENAME,null,null)
        val delete = context.deleteDatabase(DATABASENAME)
        if(delete == true){
            println("Database Erfolgreich gelöscht!")
        }
    }
}