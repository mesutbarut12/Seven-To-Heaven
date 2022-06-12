package com.barut.unterkontenverwaltung.recyclerview.hauptanzeige

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HARecyclerViewAdapterMain(
    val inhalt: HAHauptAnzeigeModel, val layout: Int,

    ) : RecyclerView.Adapter<HARecyclerViewHolderMain>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HARecyclerViewHolderMain {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return HARecyclerViewHolderMain(view)
    }

    override fun onBindViewHolder(holder: HARecyclerViewHolderMain, position: Int) {
        holder.unterkonto.setText(inhalt.hAunterkontoName!!.get(holder.adapterPosition))
        holder.prozent.setText("Prozent : ${getValue(inhalt.hAProzentEinteilung!!.get(holder.adapterPosition))}%")
        holder.ausgaben.setText("Ausgabe : ${getValue(inhalt.hAAusgaben!!.get(holder.adapterPosition))}€")
        holder.guthaben.setText("Guthaben : ${getValue(inhalt.hAGuthaben!!.get(holder.adapterPosition))}€")
        holder.ergebnis.setText("Ergebnis : ${getValue(inhalt.hAErgebnis!!.get(holder.adapterPosition))}€")
    }

    override fun getItemCount(): Int {
        return inhalt.hAunterkontoName!!.size
    }

    fun getValue(value: String): String {
        return value.split(".")[0]
    }
}


