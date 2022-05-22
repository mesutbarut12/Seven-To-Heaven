package com.barut.unterkontenverwaltung.showcalculatedata

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.recyclerview.RecyclerViewHolderMain
import com.barut.unterkontenverwaltung.recyclerview.RecylcerViewModel
import com.barut.unterkontenverwaltung.showexistingunterkonten.ShowExistingUnterkontoInterface

class ShowCalculateDataBinding(val holder : RecyclerViewHolderMain, val id : String,
                               val inhalt : ArrayList<RecylcerViewModel>,
                               val recyclerView : RecyclerView,
                               val showExistingUnterkontenInterface: ShowExistingUnterkontoInterface?) {
    fun onStart() {
        if (holder.differntHolder() != null) {
            if (id == "ShowCalculateData") {
                val showlist = holder.differntHolder()
                val tv = showlist!!.get(0) as TextView
                tv.setText("[ Unterkonto : ${inhalt.get(holder.adapterPosition).spaltenName1Inhalt} ]\n" +
                        "[ Gesamt Summe : ${inhalt.get(holder.adapterPosition).spaltenName2Inhalt} ] \n" +
                        "[ Prozent : ${inhalt.get(holder.adapterPosition).databaseType}% ] \n" +
                        "[ Ergebnis : ${inhalt.get(holder.adapterPosition).date} ]")

            }
        }
    }

}