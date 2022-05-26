package com.barut.unterkontenverwaltung.recyclerview.binder

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.recyclerview.RecyclerViewHolderMain
import com.barut.unterkontenverwaltung.recyclerview.Model
import com.barut.unterkontenverwaltung.recyclerview.NewModel
import java.text.DecimalFormat

class ShowCalculateDataBinding(val holder : RecyclerViewHolderMain, val id : String,
                               val inhalt : NewModel,
                               val recyclerView : RecyclerView,
                               ) {
    fun onStart() {
        if (holder.differntHolder() != null) {
             if(id == "EndShowDataCalculate"){
                val showlist = holder.differntHolder()
                val unterkonto = showlist!!.get(0) as TextView
                val prozent = showlist!!.get(1) as TextView
                val saldoMitAusgaben = showlist!!.get(2) as TextView
                val saldoUnterkonten = showlist!!.get(3) as TextView


                unterkonto.setText("Unterkonto : ${inhalt.spaltenName1.get(holder.adapterPosition)}")
                prozent.setText("Prozentuale Einteilung :  ${inhalt.spaltenName2.get(holder.adapterPosition)}%")
                saldoMitAusgaben.setText("Saldo mit den Ausgaben gerechnet : ${inhalt.datum.get(holder.adapterPosition)}")
                saldoUnterkonten.setText("Saldo für das Unterkonto: ${inhalt.databaseType.get(holder.adapterPosition)}")



            }
        }
    }


}