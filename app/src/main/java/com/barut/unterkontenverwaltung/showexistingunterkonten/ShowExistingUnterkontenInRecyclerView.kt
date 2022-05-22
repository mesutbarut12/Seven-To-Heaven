package com.barut.unterkontenverwaltung.showexistingunterkonten

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.recyclerview.RecylcerViewModel
import com.barut.unterkontenverwaltung.recyclerview.StartRecyclerView
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain

class ShowExistingUnterkontenInRecyclerView(val context : Context,
                                            val recyclerView: RecyclerView) {
    val sqLiteMainUnterkonto = SQLiteMain(context,"Unterkonto","Unterkonto",
    "name","prozent","datum","databaseType","id")

    fun onStart(){
        val data = sqLiteMainUnterkonto.readData()
        val arrayList : ArrayList<RecylcerViewModel> = arrayListOf()
        var model : RecylcerViewModel
        for(i in data){
            model = RecylcerViewModel(i.spaltenName1,i.spaltenName2,i.echtZeitDatum,i.databaseTyp)
            arrayList.add(model)
        }
        StartRecyclerView(context,recyclerView,arrayList, R.layout.show_existing_unterkonten,
            "ShowExistingUnterkontenItems")
    }

}