package com.barut.unterkontenverwaltung.recyclerview

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class RecyclerViewAdapterMain(val inhalt : ArrayList<RecylcerViewModel>,val layout : Int,val holderId : String)
    : RecyclerView.Adapter<RecyclerViewHolderMain>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolderMain {
        val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
        return RecyclerViewHolderMain(view,holderId)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolderMain, position: Int) {

        RecyclerViewBinder(holder,holderId,inhalt).onStart()

    }

    override fun getItemCount(): Int {
        return inhalt.size
    }


}