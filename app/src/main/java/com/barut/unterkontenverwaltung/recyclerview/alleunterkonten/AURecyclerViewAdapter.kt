package com.barut.unterkontenverwaltung.recyclerview.alleunterkonten

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.DataTransferShowUnterkontoBinder
import com.barut.unterkontenverwaltung.R

class AURecyclerViewAdapter(
    private val inhalt: AURecyclerViewModel,
    private val dataTransferShowUnterkontoBinder: DataTransferShowUnterkontoBinder
) :
    RecyclerView.Adapter<AURecylerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AURecylerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.set_item_unterkonten_row,
            parent, false
        )
        return AURecylerViewHolder(view)
    }

    override fun onBindViewHolder(holder: AURecylerViewHolder, position: Int) {
        holder.unterkonto.setText(inhalt.unterkonto.get(holder.adapterPosition))
        holder.summe.setText("Verfügbarer Saldo : ${inhalt.summe.get(holder.adapterPosition)}€")

        clickListener(holder)
    }

    override fun getItemCount(): Int {
        return inhalt.unterkonto.size
    }

    fun clickListener(holder: AURecylerViewHolder) {
        holder.itemView.setOnClickListener {
            dataTransferShowUnterkontoBinder.getData(inhalt.unterkonto.get(holder.adapterPosition))
        }
    }
}