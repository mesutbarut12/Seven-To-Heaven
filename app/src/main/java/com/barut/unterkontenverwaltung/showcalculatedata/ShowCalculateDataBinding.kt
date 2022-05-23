package com.barut.unterkontenverwaltung.showcalculatedata

import android.opengl.Visibility
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.recyclerview.RecyclerViewHolderMain
import com.barut.unterkontenverwaltung.recyclerview.RecylcerViewModel
import com.barut.unterkontenverwaltung.showexistingunterkonten.ShowExistingUnterkontoInterface
import java.text.DecimalFormat
import kotlin.math.nextDown
import kotlin.math.nextUp
import kotlin.math.roundToInt
import kotlin.math.roundToLong

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

            }else if(id == "EndShowDataCalculate"){
                if(holder.adapterPosition == 0){

                }
                val showlist = holder.differntHolder()
                val unterkonto = showlist!!.get(0) as TextView
                val prozent = showlist!!.get(1) as TextView
                val saldoMitAusgaben = showlist!!.get(2) as TextView
                val saldoUnterkonten = showlist!!.get(3) as TextView

                val data = processDateToTwoDatas(inhalt.get(holder.adapterPosition).date)
                val aufrunden = data[0].toDouble()
                val f =  DecimalFormat("#0.00")



                unterkonto.setText("Unterkonto : ${inhalt.get(holder.adapterPosition).spaltenName1Inhalt}")
                prozent.setText("Prozentuale Einteilung :  ${inhalt.get(holder.adapterPosition).spaltenName2Inhalt}%")
                saldoMitAusgaben.setText("Saldo mit den Ausgaben gerechnet : ${f.format(inhalt.get(holder.adapterPosition).databaseType.toDouble())}")
                saldoUnterkonten.setText("Saldo für das Unterkonto: : ${f.format(aufrunden)}")



            }
        }
    }
    fun processDateToTwoDatas(data : String) : ArrayList<String>{
        var arraylist : ArrayList<String> = arrayListOf()
        val dataSplitted = data.split(",")
        arraylist.add(dataSplitted[0])
        arraylist.add(dataSplitted[1])
        return arraylist
    }

}