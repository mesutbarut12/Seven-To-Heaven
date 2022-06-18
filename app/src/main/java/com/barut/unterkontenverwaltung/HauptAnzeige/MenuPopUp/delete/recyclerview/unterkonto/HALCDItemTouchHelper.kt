package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.delete.recyclerview.unterkonto

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit
import com.barut.unterkontenverwaltung.recyclerview.UnterkontoModel

class HALCDItemTouchHelper(val inhalt: ArrayList<HALCDModelUnterkonto>, private val context: Context, ) :
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
        var model : UnterkontoModel
        for(i in sqlinit.unterkonto().readData()){
            if(inhalt.get(pos).unterkonto == i.name && inhalt.get(pos).beschreibung == i.beschreibung
                && inhalt.get(pos).id == i.id && inhalt.get(pos).prozent == i.prozent){
                model = UnterkontoModel(i.name,i.prozent,i.datum,i.databaseType,i.id,i.beschreibung)
                sqlinit.unterkonto().deleateItem(model)
            }
        }
    }

}