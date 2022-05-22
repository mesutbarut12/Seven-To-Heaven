package com.barut.unterkontenverwaltung.showitems

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.recyclerview.RecylcerViewModel
import com.barut.unterkontenverwaltung.recyclerview.StartRecyclerView
import com.barut.unterkontenverwaltung.showexistingunterkonten.ShowExistingUnterkontoInterface
import com.barut.unterkontenverwaltung.sqlite.SQLiteModel

class ShowItems(private val context : Context,private val recylcerView : RecyclerView
,private val inhaltSql : ArrayList<SQLiteModel>) {

    private lateinit var inhalt : ArrayList<RecylcerViewModel>
    private lateinit var model : RecylcerViewModel

    fun transformSqlToRecyclerModel(){
         inhalt = arrayListOf()

        for(i in inhaltSql){
            model = RecylcerViewModel(i.spaltenName1,i.spaltenName2,i.echtZeitDatum,i.databaseTyp)
            inhalt.add(model)
        }
    }

    fun showItems(){
        StartRecyclerView(context,recylcerView, inhalt, R.layout.show_items,"ShowItems",
        null)
    }
}