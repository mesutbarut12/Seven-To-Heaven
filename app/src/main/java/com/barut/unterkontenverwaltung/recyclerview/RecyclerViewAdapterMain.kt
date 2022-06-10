package com.barut.unterkontenverwaltung.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.DataTransferShowUnterkontoBinder
import com.barut.unterkontenverwaltung.recyclerview.binder.ShowAllDatasInRecyclerViewBinder
import com.barut.unterkontenverwaltung.recyclerview.binder.CalculateBinder
import com.barut.unterkontenverwaltung.showexistingunterkonten.ShowExistingUnterkontenInRecyclerViewBinder

class RecyclerViewAdapterMain(
    val inhalt: ArrayList<UnterkontoModel>, val layout: Int, val holderId: String,
    val recylcerView: RecyclerView,
    val newInhalt: NewModel?,
    val dataTransferShowUnterkontoBinder: DataTransferShowUnterkontoBinder?
) : RecyclerView.Adapter<RecyclerViewHolderMain>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolderMain {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return RecyclerViewHolderMain(view, holderId)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolderMain, position: Int) {
        if (holderId == "ShowItems") {
            ShowAllDatasInRecyclerViewBinder(holder, holderId, inhalt, recylcerView).onStart()
        } else if (holderId == "ShowExistingUnterkontenItems") {
            ShowExistingUnterkontenInRecyclerViewBinder(
                holder,
                holderId,
                inhalt,
                recylcerView,
                dataTransferShowUnterkontoBinder!!
            ).init()
        } else if (holderId == "EndShowDataCalculate") {
            CalculateBinder(holder, holderId, newInhalt!!, recylcerView, inhalt)
                .onStart()
        }
    }

    override fun getItemCount(): Int {
        if (holderId == "EndShowDataCalculate") {
            return newInhalt!!.spaltenName1.size
        } else {
            return inhalt.size
        }
        return inhalt.size

    }


}