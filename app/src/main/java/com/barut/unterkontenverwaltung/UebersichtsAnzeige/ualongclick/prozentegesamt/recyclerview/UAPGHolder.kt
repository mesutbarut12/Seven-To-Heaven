package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.prozentegesamt.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class UAPGHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val name : TextView = itemView.findViewById(R.id.ua_pg_name)
    val datum : TextView = itemView.findViewById(R.id.ua_pg_datum)
    val prozent : TextView = itemView.findViewById(R.id.ua_pg_prozent)
    val beschreibung : TextView = itemView.findViewById(R.id.ua_pg_beschreibung)

}