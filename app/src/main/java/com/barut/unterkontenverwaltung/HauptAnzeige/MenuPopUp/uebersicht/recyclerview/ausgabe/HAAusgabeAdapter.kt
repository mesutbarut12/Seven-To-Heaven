package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.ausgabe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class HAAusgabeAdapter(val inhalt : ArrayList<HAAusgabeModel>) : RecyclerView.Adapter<HAAusgabeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HAAusgabeHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.ha_ausgabe_row,parent,false)
        return HAAusgabeHolder(view)
    }

    override fun onBindViewHolder(holder: HAAusgabeHolder, position: Int) {
        holder.datum.setText(inhalt.get(holder.adapterPosition).datum)
        holder.guthaben.setText(inhalt.get(holder.adapterPosition).guthaben)
        holder.ausgabe.setText(inhalt.get(holder.adapterPosition).ausgabe)
        holder.ergebnis.setText(inhalt.get(holder.adapterPosition).ergebnis)
        holder.id.setText(inhalt.get(holder.adapterPosition).id)
    }

    override fun getItemCount(): Int {
        return inhalt.size
    }
}