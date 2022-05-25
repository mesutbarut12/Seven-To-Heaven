package com.barut.unterkontenverwaltung.showexistingunterkonten

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.recyclerview.RecyclerViewHolderMain
import com.barut.unterkontenverwaltung.recyclerview.Model

class ShowExistingUnterkontenInRecyclerViewBinder(val holder : RecyclerViewHolderMain, val id : String,
                                                  val inhalt : ArrayList<Model>,
                                                  val recyclerView : RecyclerView,
                                                  val showExistingUnterkontenInterface: ShowExistingUnterkontoInterface) {
    val showlist = holder.differntHolder()
    val tv = showlist!!.get(0) as TextView


    fun onStart(){
        if(holder.differntHolder() != null){
            if(id == "ShowExistingUnterkontenItems"){
                tv.setText(inhalt.get(holder.adapterPosition).spaltenName1)
                currentUnterkontoClick()

            }
        }
    }

    fun currentUnterkontoClick(){

        tv.setOnClickListener {
            showExistingUnterkontenInterface.showExistingUnterkonto(true,tv.text.toString())
        }

    }
}

interface ShowExistingUnterkontoInterface{
    fun showExistingUnterkonto(boolean: Boolean,data : String)
}