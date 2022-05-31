package com.barut.unterkontenverwaltung.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class RecyclerViewHolderMain(itemView: View, val id: String) : RecyclerView.ViewHolder(itemView) {

    fun differntHolder(): ArrayList<View>? {
        val arraylistView: ArrayList<View> = arrayListOf()

        if (id == "ShowItems") {
            val image: ImageView = itemView.findViewById(R.id.iVShowList)
            val ivDelete: ImageView = itemView.findViewById(R.id.ivDelete)
            val tvspaltenname1: TextView = itemView.findViewById(R.id.tvShowListSpaltenName1)
            val tvspaltenname2: TextView = itemView.findViewById(R.id.tvShowListSpaltenName2)
            val tvEchtzeitDatum: TextView = itemView.findViewById(R.id.tvShowListEchtzeitDatum)
            val ivEdit: ImageView = itemView.findViewById(R.id.ivEdit)
            arraylistView.add(image)
            arraylistView.add(ivDelete)
            arraylistView.add(tvspaltenname1)
            arraylistView.add(tvspaltenname2)
            arraylistView.add(tvEchtzeitDatum)
            arraylistView.add(ivEdit)
            return arraylistView
        } else if (id == "ShowExistingUnterkontenItems") {
            val tv: TextView = itemView.findViewById(R.id.tv)
            arraylistView.add(tv)
            return arraylistView
        } else if (id == "EndShowDataCalculate") {
            val unterkonto: TextView = itemView.findViewById(R.id.tvUnterkonto)
            val prozent: TextView = itemView.findViewById(R.id.tvProzentualeEinteilung)
            val guthaben: TextView = itemView.findViewById(R.id.tvGuthaben)
            val ausgaben: TextView = itemView.findViewById(R.id.tvAusgaben)
            val ergebnis: TextView = itemView.findViewById(R.id.tvErgebnis)
            val beschreibungAusgabe: TextView = itemView.findViewById(R.id.tvBAusgabe)
            val beschreibungUnterkonto: TextView = itemView.findViewById(R.id.tvBUnterkonto)
            arraylistView.add(unterkonto)
            arraylistView.add(prozent)
            arraylistView.add(guthaben)
            arraylistView.add(ausgaben)
            arraylistView.add(ergebnis)
            arraylistView.add(beschreibungAusgabe)
            arraylistView.add(beschreibungUnterkonto)
            return arraylistView
        } else {
            Toast.makeText(itemView.context, "Fehler beim Laden", Toast.LENGTH_LONG).show()
            return null
        }

        return null
    }


}