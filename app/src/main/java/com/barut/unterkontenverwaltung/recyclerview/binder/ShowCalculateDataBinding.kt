package com.barut.unterkontenverwaltung.recyclerview.binder

import android.view.View
import android.widget.TabHost
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.recyclerview.RecyclerViewHolderMain
import com.barut.unterkontenverwaltung.recyclerview.NewModel
import com.google.android.material.tabs.TabLayout
import java.text.DecimalFormat
import kotlin.math.roundToInt

class ShowCalculateDataBinding(
    val holder: RecyclerViewHolderMain, val id: String,
    val inhalt: NewModel,
    val recyclerView: RecyclerView,
) {
    fun onStart() {
        if (holder.differntHolder() != null) {
            if (id == "EndShowDataCalculate") {
                longClickListener()
                val showlist = holder.differntHolder()
                val unterkonto = showlist!!.get(0) as TextView
                val prozent = showlist!!.get(1) as TextView
                val guthaben = showlist!!.get(2) as TextView
                val ausgaben = showlist!!.get(3) as TextView
                val ergebnis = showlist!!.get(4) as TextView
                val beschreibungAusgabe = showlist!!.get(5) as TextView
                val beschreibungUnterkonto = showlist!!.get(6) as TextView

                val f = DecimalFormat("#0.0")
                unterkonto.setText("${inhalt.spaltenName1.get(holder.adapterPosition)}")
                prozent.setText(
                    "Prozentuale Einteilung :  ${
                        runden(
                            inhalt.spaltenName2.get(holder.adapterPosition).toDouble()
                        )
                    }%"
                )
                guthaben.setText(
                    "Guthaben : ${
                        runden(
                            inhalt.databaseType.get(holder.adapterPosition).toDouble()
                        )
                    }"
                )
                ausgaben.setText(
                    "Ausgaben : ${
                        runden(
                            inhalt.datum.get(holder.adapterPosition).toDouble()
                        )
                    }"
                )
                var ergebnis1 =
                    inhalt.databaseType.get(holder.adapterPosition).toDouble() - inhalt.datum.get(
                        holder.adapterPosition
                    ).toDouble()
                ergebnis.setText("Saldo : ${runden(ergebnis1)}")

                beschreibungAusgabe.setText("X")
                beschreibungUnterkonto.setText("X")
                println("---------------------------")


                for (i in inhalt.id) {
                    println(i)

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

    fun longClickListener() {
        holder.itemView.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(p0: View?): Boolean {
                val alert =
                    AlertDialogMain(holder.itemView.context, R.layout.item_click_long_listener)
                val view = alert.setLayout()
                alert.createDialog()
                Toast.makeText(holder.itemView.context, "Lang geklickt!", Toast.LENGTH_LONG).show()

                val tabHost = view.findViewById<TabLayout>(R.id.tabLayout)
                tabHost.addTab(tabHost.newTab().setText("Beschreibung"))
                tabHost.tabMode = TabLayout.MODE_FIXED
                

                return true
            }

        })
    }
}