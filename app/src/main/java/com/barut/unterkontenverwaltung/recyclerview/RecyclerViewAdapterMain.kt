package com.barut.unterkontenverwaltung.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapterMain(val inhalt : ArrayList<String>,val layout : Int)
    : RecyclerView.Adapter<RecyclerViewAdapterMain.RecyclerViewHolderMain>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolderMain {
        val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
        return RecyclerViewHolderMain(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolderMain, position: Int) {

    }

    override fun getItemCount(): Int {
        return inhalt.size
    }

    class RecyclerViewHolderMain(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }
}