package com.barut.unterkontenverwaltung.HauptAnzeige.delete.recyclerview.unterkonto

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.delete.recyclerview.einnahme.HALCDEinnahmeModel
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit
import com.barut.unterkontenverwaltung.recyclerview.EinkommenModel

class HALCDEinnahmeTouchHelper(val inhalt: ArrayList<HALCDEinnahmeModel>, private val context: Context, ) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private val sqlinit = SQliteInit(context)

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val pos = viewHolder.adapterPosition
        var model : EinkommenModel
        for(i in sqlinit.einnahme().readData()){
            if(inhalt.get(pos).id == i.id && inhalt.get(pos).beschreibung == i.beschreibung
                && inhalt.get(pos).einnahme == i.summe && inhalt.get(pos).datum == i.datum){
                model = EinkommenModel(i.summe,i.datum,i.databaseType,i.id
                ,i.beschreibung)
                sqlinit.einnahme().deleateItem(model)
            }
        }
    }

}