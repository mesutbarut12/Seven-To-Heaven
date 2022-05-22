package com.barut.unterkontenverwaltung.showexistingunterkonten

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.recyclerview.RecyclerViewHolderMain
import com.barut.unterkontenverwaltung.recyclerview.RecylcerViewModel

class ShowExistingUnterkontenInRecyclerViewBinder(val holder : RecyclerViewHolderMain, val id : String,
                                                  val inhalt : ArrayList<RecylcerViewModel>,
                                                  val recyclerView : RecyclerView) {

    fun onStart(){
        if(holder.differntHolder() != null){
            if(id == "ShowExistingUnterkontenItems"){
                val showlist = holder.differntHolder()
                val tv = showlist!!.get(0) as TextView
                tv.setText(inhalt.get(holder.adapterPosition).spaltenName1Inhalt)

            }
        }
    }
}