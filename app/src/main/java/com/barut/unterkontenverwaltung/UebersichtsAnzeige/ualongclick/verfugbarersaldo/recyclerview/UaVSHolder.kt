package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.verfugbarersaldo.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class UaVSHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name : TextView = itemView.findViewById(R.id.ua_vs_Name)
    val guthaben : TextView = itemView.findViewById(R.id.ua_vs_Guthaben)
    val ausgabe : TextView = itemView.findViewById(R.id.ua_vs_Ausgabe)
    val datum : TextView = itemView.findViewById(R.id.ua_vs_Datum)
    val beschreibung : TextView = itemView.findViewById(R.id.ua_vs_beschreibung)
    val ergebnis : TextView = itemView.findViewById(R.id.ua_vs_ergebnis)
    val id : TextView = itemView.findViewById(R.id.ua_vs_id)
}