package com.barut.unterkontenverwaltung.recyclerview.binder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.recyclerview.UnterkontoModel
import com.barut.unterkontenverwaltung.recyclerview.RecyclerViewHolderMain
import com.barut.unterkontenverwaltung.recyclerview.NewModel
import com.google.android.material.tabs.TabLayout
import java.text.DecimalFormat
import kotlin.math.roundToInt

class CalculateBinder(
    val holder: RecyclerViewHolderMain, val id: String,
    val newInhalt: NewModel,
    val recyclerView: RecyclerView, val inhalt: ArrayList<UnterkontoModel>
) {
    fun onStart() {
        if (holder.differntHolder() != null) {
            if (id == "EndShowDataCalculate") {
                val showlist = holder.differntHolder()
                val unterkonto = showlist!!.get(0) as TextView
                val prozent = showlist!!.get(1) as TextView
                val guthaben = showlist!!.get(2) as TextView
                val ausgaben = showlist!!.get(3) as TextView
                val ergebnis = showlist!!.get(4) as TextView
                val beschreibungAusgabe = showlist!!.get(5) as TextView
                val beschreibungUnterkonto = showlist!!.get(6) as TextView

                val f = DecimalFormat("#0.0")
                unterkonto.setText("${newInhalt.spaltenName1.get(holder.adapterPosition)}")
                prozent.setText(
                    "Prozentuale Einteilung :  ${
                        runden(
                            newInhalt.spaltenName2.get(holder.adapterPosition).toDouble()
                        )
                    }%"
                )
                guthaben.setText(
                    "Guthaben : ${
                        runden(
                            newInhalt.databaseType.get(holder.adapterPosition).toDouble()
                        )
                    }"
                )
                ausgaben.setText(
                    "Ausgaben : ${
                        runden(
                            newInhalt.datum.get(holder.adapterPosition).toDouble()
                        )
                    }"
                )
                var ergebnis1 =
                    newInhalt.databaseType.get(holder.adapterPosition)
                        .toDouble() - newInhalt.datum.get(
                        holder.adapterPosition
                    ).toDouble()
                ergebnis.setText("Saldo : ${runden(ergebnis1)}")

                beschreibungAusgabe.setText("X")
                beschreibungUnterkonto.setText("X")


                for (i in newInhalt.id) {

                    if (i == "unterkonto") {
                        beschreibungUnterkonto.setText("✓")
                    } else if (i == "ausgabe") {
                        beschreibungAusgabe.setText("✓")
                    } else if (i == "aLeer") {
                        beschreibungAusgabe.setText("X")
                    } else if (i == "uLeer") {
                        beschreibungUnterkonto.setText("X")
                    }
                }
            }
        }
    }

    fun runden(double: Double): Double {
        var roundOff = (double * 100.0).roundToInt() / 100.0
        return roundOff
    }






    fun getViewAlertDialog(alert: AlertDialogMain): View {
        val view = alert.setLayout()
        return view
    }






    fun getDataForTabLayout2(position: Int): ArrayList<UnterkontoModel> {
        val arrayList: ArrayList<UnterkontoModel> = arrayListOf()
        for (i in inhalt) {
            if (position == 0) {
                if (i.databaseType == "Unterkonto") {
                    arrayList.add(i)
                }
            } else if (position == 1) {
                if (i.databaseType == "Einnahme") {
                    arrayList.add(i)
                }
            } else if (position == 2) {
                if (i.databaseType == "Ausgabe") {
                    arrayList.add(i)
                }
            }
        }
        return arrayList
    }




}