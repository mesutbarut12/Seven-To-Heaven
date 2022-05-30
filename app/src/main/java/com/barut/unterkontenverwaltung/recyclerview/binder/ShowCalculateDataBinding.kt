package com.barut.unterkontenverwaltung.recyclerview.binder

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.recyclerview.RecyclerViewHolderMain
import com.barut.unterkontenverwaltung.recyclerview.NewModel
import java.text.DecimalFormat
import kotlin.math.roundToInt

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
                val guthaben = showlist!!.get(2) as TextView
                val ausgaben = showlist!!.get(3) as TextView
                val ergebnis = showlist!!.get(4) as TextView

                 val f = DecimalFormat("#0.0")
                 unterkonto.setText("${inhalt.spaltenName1.get(holder.adapterPosition)}")
                 prozent.setText("Prozentuale Einteilung :  ${runden(inhalt.spaltenName2.get(holder.adapterPosition).toDouble())}%")
                 guthaben.setText("Guthaben : ${runden(inhalt.databaseType.get(holder.adapterPosition).toDouble())}")
                 ausgaben.setText("Ausgaben : ${runden(inhalt.datum.get(holder.adapterPosition).toDouble())}")
                 var ergebnis1 =  inhalt.databaseType.get(holder.adapterPosition).toDouble() - inhalt.datum.get(holder.adapterPosition).toDouble()
                 ergebnis.setText("Saldo : ${runden(ergebnis1)}")





            }
        }

    }

    fun runden(double : Double) : Double{
        var roundOff = (double * 100.0).roundToInt() / 100.0
        return roundOff
    }
}