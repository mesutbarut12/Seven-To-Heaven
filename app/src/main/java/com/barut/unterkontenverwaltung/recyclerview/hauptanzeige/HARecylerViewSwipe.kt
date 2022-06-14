package com.barut.unterkontenverwaltung.recyclerview.hauptanzeige

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class HARecylerViewSwipe(
val context : Context) :
    ItemTouchHelper.SimpleCallback(
        0,ItemTouchHelper.RIGHT
    ) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {

        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        Toast.makeText(context,"Geswiped",Toast.LENGTH_SHORT).show()

    }
}