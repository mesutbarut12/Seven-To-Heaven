package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.verfugbarersaldo.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class UaVSAdapter(private val inhalt: ArrayList<UaVSModel>) : RecyclerView.Adapter<UaVSHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UaVSHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.ua_vs_row,parent,
        false)
        return UaVSHolder(view)
    }

    override fun onBindViewHolder(holder: UaVSHolder, position: Int) {
        holder.name.setText("${inhalt.get(holder.adapterPosition).name}")
        holder.guthaben.setText("${inhalt.get(holder.adapterPosition).guthaben}")
        holder.ausgabe.setText("${inhalt.get(holder.adapterPosition).ausgabe}")
        holder.datum.setText("${inhalt.get(holder.adapterPosition).datum}")
        holder.beschreibung.setText("${inhalt.get(holder.adapterPosition).beschreibung}")
        holder.ergebnis.setText("${inhalt.get(holder.adapterPosition).ergebnis}")
        clickArrowDropDown(holder)
    }

    override fun getItemCount(): Int {
        return inhalt.size
    }

    fun clickArrowDropDown(holder: UaVSHolder) {
        holder.itemView.setOnClickListener {
            var boolean = holder.beschreibung.visibility == View.GONE
            if (boolean == true) {
                holder.beschreibung.visibility = View.VISIBLE
            } else if (boolean == false) {
                holder.beschreibung.visibility = View.GONE
            }
        }
    }
}