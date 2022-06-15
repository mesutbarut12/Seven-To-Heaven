package com.barut.unterkontenverwaltung.HauptAnzeige.longclickdeleteitem.recyclerview.einnahme

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class HALCDEinnahmeAdapter(val inhalt: ArrayList<HALCDEinnahmeModel>) :
    RecyclerView.Adapter<HALCDEinnahmeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HALCDEinnahmeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_delete_einnahme_row,
        parent,false)
        return HALCDEinnahmeHolder(view)
    }

    override fun onBindViewHolder(holder: HALCDEinnahmeHolder, position: Int) {
        holder.einnahme.setText(inhalt.get(holder.adapterPosition).einnahme)
        holder.datum.setText(inhalt.get(holder.adapterPosition).datum)
    }

    override fun getItemCount(): Int {
        return inhalt.size
    }
}