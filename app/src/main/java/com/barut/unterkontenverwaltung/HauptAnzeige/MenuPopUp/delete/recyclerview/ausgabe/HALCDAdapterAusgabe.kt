package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.delete.recyclerview.ausgabe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class HALCDAdapterAusgabe(val inhalt : ArrayList<HALCDModelAusgabe>) : RecyclerView.Adapter<HALCDHolderAusgabe>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HALCDHolderAusgabe {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_delete_ausgabe_row,
        parent,false)
        return HALCDHolderAusgabe(view)
    }

    override fun onBindViewHolder(holder: HALCDHolderAusgabe, position: Int) {
        holder.datum.setText(inhalt.get(holder.adapterPosition).datum)
        holder.summe.setText(inhalt.get(holder.adapterPosition).summe)
        holder.name.setText(inhalt.get(holder.adapterPosition).name)
    }

    override fun getItemCount(): Int {
        return inhalt.size
    }
}