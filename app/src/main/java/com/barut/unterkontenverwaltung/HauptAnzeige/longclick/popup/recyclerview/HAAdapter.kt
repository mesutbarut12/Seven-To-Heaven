package com.barut.unterkontenverwaltung.HauptAnzeige.longclick.popup.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class HAAdapter(val inhalt : ArrayList<HAModel>) : RecyclerView.Adapter<HAHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HAHolder {
        val view = LayoutInflater.from(parent.context).
                inflate(R.layout.ha_einnahme_row,parent,false)
        return HAHolder(view)
    }

    override fun onBindViewHolder(holder: HAHolder, position: Int) {
        holder.prozent.setText("${inhalt.get(holder.adapterPosition).prozent}%")
        holder.ergebnis.setText("${inhalt.get(holder.adapterPosition).ergebnis}€")
        holder.datum.setText("${inhalt.get(holder.adapterPosition).datum}€")
    }

    override fun getItemCount(): Int {
        return inhalt.size
    }
}