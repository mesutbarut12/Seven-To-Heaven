package com.barut.unterkontenverwaltung.showexistingunterkonten

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.recyclerview.Model
import com.barut.unterkontenverwaltung.recyclerview.StartRecyclerView
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain

class ShowExistingUnterkontenInRecyclerView(val context : Context,
                                            val recyclerView: RecyclerView,
                                            val showExistingUnterkontoInterface:
                                            ShowExistingUnterkontoInterface) {
    val sqLiteMainUnterkonto = SQLiteMain(context,"Unterkonto","Unterkonto",
    "name","prozent","datum","databaseType","id",)


    fun onStart(){
        val data = sqLiteMainUnterkonto.readData()
        val arrayList : ArrayList<Model> = arrayListOf()
        var model : Model
        for(i in data){
            model = Model(i.spaltenName1,i.spaltenName2,i.datum,i.databaseType,"")
            arrayList.add(model)
        }
        StartRecyclerView(context,recyclerView,arrayList, R.layout.show_existing_unterkonten,
            "ShowExistingUnterkontenItems",showExistingUnterkontoInterface)
    }

}