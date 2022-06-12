package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtausgaben.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class UAGAHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name : TextView = itemView.findViewById(R.id.ua_ga_name)
    val datum : TextView = itemView.findViewById(R.id.ua_ga_datum)
    val ausgabe : TextView = itemView.findViewById(R.id.ua_ga_ausgabe)
    val beschreibung : TextView = itemView.findViewById(R.id.ua_ga_beschreibung)
}