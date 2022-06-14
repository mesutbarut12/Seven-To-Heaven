package com.barut.unterkontenverwaltung.HauptAnzeige.longclick.popup.recyclerview.einnahme

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class HAEinnahmeAdapter(val inhalt : ArrayList<HAEinnahmeModel>) : RecyclerView.Adapter<HAStarterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HAStarterHolder {
        val view = LayoutInflater.from(parent.context).
                inflate(R.layout.ha_einnahme_row,parent,false)
        return HAStarterHolder(view)
    }

    override fun onBindViewHolder(holder: HAStarterHolder, position: Int) {
        holder.prozent.setText("${inhalt.get(holder.adapterPosition).prozent}%")
        holder.ergebnis.setText("${inhalt.get(holder.adapterPosition).ergebnis}€")
        holder.datum.setText("${inhalt.get(holder.adapterPosition).datum}")
        holder.einnahme.setText("${inhalt.get(holder.adapterPosition).einnahme}€")
        holder.id.setText("${inhalt.get(holder.adapterPosition).id}")
    }

    override fun getItemCount(): Int {
        return inhalt.size
    }
}