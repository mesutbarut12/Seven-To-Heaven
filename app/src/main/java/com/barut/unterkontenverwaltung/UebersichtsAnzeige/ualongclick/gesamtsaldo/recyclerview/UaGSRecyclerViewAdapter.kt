package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class UaGSRecyclerViewAdapter(private var inhalt: ArrayList<UaGSModel>) :
    RecyclerView.Adapter<UaGSRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UaGSRecyclerViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.ua_gs_row, parent, false
        )
        return UaGSRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: UaGSRecyclerViewHolder, position: Int) {
        holder.summe.setText("Einnahme : ${inhalt.get(holder.adapterPosition).summe}")
        holder.beschreibung.setText("${inhalt.get(holder.adapterPosition).beschreibung}")
        holder.datum.setText("Datum : ${inhalt.get(holder.adapterPosition).datum}")
        clickArrowDropDown(holder)
    }

    override fun getItemCount(): Int {
        return inhalt.size
    }

    fun clickArrowDropDown(holder: UaGSRecyclerViewHolder) {
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