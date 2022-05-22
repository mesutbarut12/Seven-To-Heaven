package com.barut.unterkontenverwaltung.showexistingunterkonten

import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.recyclerview.RecyclerViewHolderMain
import com.barut.unterkontenverwaltung.recyclerview.RecylcerViewModel
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain
import com.barut.unterkontenverwaltung.sqlite.SQLiteModel

class ShowExistingUnterkontenInRecyclerViewBinder(val holder : RecyclerViewHolderMain, val id : String,
                                                  val inhalt : ArrayList<RecylcerViewModel>,
                                                  val recyclerView : RecyclerView,
                                                  val showExistingUnterkontenInterface: ShowExistingUnterkontoInterface) {
    val showlist = holder.differntHolder()
    val tv = showlist!!.get(0) as TextView

    val sqliteShowExistingUnterkontenInRecyclerView = SQLiteMain(holder.itemView.context,"ShowExistingUnterkonto","ShowExistingUnterkonto",
        "name","prozent","datum","databaseType","id")

    fun onStart(){
        if(holder.differntHolder() != null){
            if(id == "ShowExistingUnterkontenItems"){
                tv.setText(inhalt.get(holder.adapterPosition).spaltenName1Inhalt)
                currentUnterkontoClick()

            }
        }
    }

    fun currentUnterkontoClick(){

        tv.setOnClickListener {
            val model = SQLiteModel(tv.text.toString(),"","","ShowExistingUnterkonto")
            sqliteShowExistingUnterkontenInRecyclerView.setData(model)
            showExistingUnterkontenInterface.showExistingUnterkonto(true,tv.text.toString())
        }

    }
}

interface ShowExistingUnterkontoInterface{
    fun showExistingUnterkonto(boolean: Boolean,data : String)
}