package com.barut.unterkontenverwaltung.recyclerview.ualongclick

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class UaRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var summe = itemView.findViewById<TextView>(R.id.ua_summe)
    var datum = itemView.findViewById<TextView>(R.id.ua_datum)
    var beschreibung = itemView.findViewById<TextView>(R.id.ua_beschreibung)

}