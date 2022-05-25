package com.barut.unterkontenverwaltung.showitems

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.recyclerview.Model
import com.barut.unterkontenverwaltung.recyclerview.StartRecyclerView

class ShowItems(private val context : Context,private val recylcerView : RecyclerView
,private val inhaltSql : ArrayList<Model>) {

    private lateinit var inhalt : ArrayList<Model>
    private lateinit var model : Model

    fun transformSqlToRecyclerModel(){
         inhalt = arrayListOf()

        for(i in inhaltSql){
            model = Model(i.spaltenName1,i.spaltenName2,i.datum,i.databaseType,"")
            inhalt.add(model)
        }
    }

    fun showItems(){
        StartRecyclerView(context,recylcerView, inhalt, R.layout.show_items,"ShowItems",
        null)
    }
}