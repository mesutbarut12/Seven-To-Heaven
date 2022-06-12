package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtausgaben.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.recyclerview.UaGSRecyclerViewHolder

class UAGAAdapter(private val inhalt : ArrayList<UAGAModel>) : RecyclerView.Adapter<UAGAHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UAGAHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ua_ga_row,parent,false)
        return UAGAHolder(view)
    }

    override fun onBindViewHolder(holder: UAGAHolder, position: Int) {
        holder.ausgabe.setText(inhalt.get(holder.adapterPosition).ausgabe)
        holder.beschreibung.setText(inhalt.get(holder.adapterPosition).beschreibung)
        holder.datum.setText(inhalt.get(holder.adapterPosition).datum)
        holder.name.setText(inhalt.get(holder.adapterPosition).name)
        clickArrowDropDown(holder)
    }

    override fun getItemCount(): Int {
        return inhalt.size
    }
    fun clickArrowDropDown(holder: UAGAHolder) {
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