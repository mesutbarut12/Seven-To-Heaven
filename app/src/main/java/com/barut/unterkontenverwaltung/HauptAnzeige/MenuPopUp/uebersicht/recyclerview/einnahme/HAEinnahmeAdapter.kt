package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.recyclerview.einnahme

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit
import com.barut.unterkontenverwaltung.recyclerview.EinkommenModel

class HAEinnahmeAdapter(val inhalt : ArrayList<HAEinnahmeModel>) : RecyclerView.Adapter<HAStarterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HAStarterHolder {
        val view = LayoutInflater.from(parent.context).
                inflate(R.layout.ha_einnahme_row,parent,false)
        return HAStarterHolder(view)
    }

    override fun onBindViewHolder(holder: HAStarterHolder, position: Int) {
        holder.datum.setText("${inhalt.get(holder.adapterPosition).datum}")
        holder.einnahme.setText("${inhalt.get(holder.adapterPosition).einnahme}€")
        holder.id.setText("${inhalt.get(holder.adapterPosition).id}")
        delete(holder)
    }
    fun delete(holder : HAStarterHolder){
        val sqlinit = SQliteInit(holder.itemView.context)
        val pos = holder.adapterPosition

        holder.delete.setOnClickListener {
            var model : EinkommenModel
            for(i in sqlinit.einnahme().readData()){

                if(inhalt.get(pos).id == i.id
                    && inhalt.get(pos).datum == i.datum &&
                    inhalt.get(pos).einnahme == i.summe &&
                    inhalt.get(pos).beschreibung == i.beschreibung
                ){
                    model = EinkommenModel(i.summe,i.datum,i.databaseType,i.id
                        ,i.beschreibung)
                    sqlinit.einnahme().deleateItem(model)
                }
            }
            inhalt.removeAt(pos)
            notifyItemRemoved(pos)
        }

    }

    override fun getItemCount(): Int {
        return inhalt.size
    }
}