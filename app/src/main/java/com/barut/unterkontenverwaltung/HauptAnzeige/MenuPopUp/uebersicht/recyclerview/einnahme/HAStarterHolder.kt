package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.einnahme

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class HAStarterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val prozent : TextView = itemView.findViewById(R.id.ha_einnahme_prozent)
    val ergebnis : TextView = itemView.findViewById(R.id.ha_einnahme_ergebnis)
    val datum : TextView = itemView.findViewById(R.id.ha_einnahme_datum)
    val einnahme : TextView = itemView.findViewById(R.id.ha_einnahme_einnahme)
    val id : TextView = itemView.findViewById(R.id.ha_einnahme_id)
}