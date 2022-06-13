package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.unterkontoanzahl.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class UAUAHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name : TextView = itemView.findViewById(R.id.ua_ua_name1)
    val datum : TextView = itemView.findViewById(R.id.ua_ua_datum1)
    val prozent : TextView = itemView.findViewById(R.id.ua_ua_prozent1)
    val beschreibung : TextView = itemView.findViewById(R.id.ua_ua_beschreibung)

}