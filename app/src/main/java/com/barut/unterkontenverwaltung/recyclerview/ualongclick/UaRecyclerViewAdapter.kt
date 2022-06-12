package com.barut.unterkontenverwaltung.recyclerview.ualongclick

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class UaRecyclerViewAdapter(private var inhalt: ArrayList<UaModel>) :
    RecyclerView.Adapter<UaRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UaRecyclerViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.ua_recyclerview_longclick_row, parent, false
        )
        return UaRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: UaRecyclerViewHolder, position: Int) {
        holder.summe.setText("Einnahme : ${inhalt.get(holder.adapterPosition).summe}")
        holder.beschreibung.setText("${inhalt.get(holder.adapterPosition).beschreibung}")
        holder.datum.setText("Datum : ${inhalt.get(holder.adapterPosition).datum}")
        clickArrowDropDown(holder)
    }

    override fun getItemCount(): Int {
        return inhalt.size
    }

    fun clickArrowDropDown(holder: UaRecyclerViewHolder) {
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