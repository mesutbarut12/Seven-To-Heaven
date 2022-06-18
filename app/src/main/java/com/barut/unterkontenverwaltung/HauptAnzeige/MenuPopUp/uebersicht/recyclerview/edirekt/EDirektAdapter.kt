package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.edirekt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.DataTransferPopUpDelete
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit


class EDirektAdapter(
    val inhalt: ArrayList<EDirektModel>,
    val dataTransferPopUpDelete: DataTransferPopUpDelete
) : RecyclerView.Adapter<EDirektHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EDirektHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.ha_edirekt_row,
            parent, false
        )
        return EDirektHolder(view)
    }

    override fun onBindViewHolder(holder: EDirektHolder, position: Int) {
        holder.datum.setText(inhalt.get(holder.adapterPosition).datum)
        holder.summe.setText(inhalt.get(holder.adapterPosition).summe)
        holder.unterkonto.setText(inhalt.get(holder.adapterPosition).unterkonto)
        delete(holder)
    }

    override fun getItemCount(): Int {
        return inhalt.size
    }

    fun delete(holder: EDirektHolder) {
        val sqlinit = SQliteInit(holder.itemView.context)
        val pos = holder.adapterPosition

        holder.delete.setOnClickListener {
            var model: com.barut.unterkontenverwaltung.recyclerview.EDirektModel
            for (i in sqlinit.eDirekt().readData()) {
                if (inhalt.get(pos).id == i.id
                    && inhalt.get(pos).datum == i.datum &&
                    inhalt.get(pos).summe == i.summe &&
                    inhalt.get(pos).unterkonto == i.unterkonto
                ) {
                    model = com.barut.unterkontenverwaltung.recyclerview.EDirektModel(
                        i.summe, i.datum, i.databaseType, i.id, i.beschreibung, i.unterkonto
                    )
                    sqlinit.eDirekt().deleateItem(model)
                    dataTransferPopUpDelete.data(3)
                }
            }
            inhalt.removeAt(pos)
            notifyItemRemoved(pos)
        }

    }
}