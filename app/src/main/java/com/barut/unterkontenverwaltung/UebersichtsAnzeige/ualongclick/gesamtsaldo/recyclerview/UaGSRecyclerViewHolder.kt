package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class UaGSRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var summe = itemView.findViewById<TextView>(R.id.ua_gs_summe)
    var datum = itemView.findViewById<TextView>(R.id.ua_gs_datum)
    var beschreibung = itemView.findViewById<TextView>(R.id.ua_gs_beschreibung)
    var arrow = itemView.findViewById<ImageView>(R.id.ua_gs_arrow)

}