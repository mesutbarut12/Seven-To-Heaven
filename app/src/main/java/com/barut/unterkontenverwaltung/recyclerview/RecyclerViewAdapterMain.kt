package com.barut.unterkontenverwaltung.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.recyclerview.binder.ShowAllDatasInRecyclerViewBinder
import com.barut.unterkontenverwaltung.recyclerview.binder.ShowCalculateDataBinding
import com.barut.unterkontenverwaltung.showexistingunterkonten.ShowExistingUnterkontenInRecyclerViewBinder
import com.barut.unterkontenverwaltung.showexistingunterkonten.ShowExistingUnterkontoInterface

class RecyclerViewAdapterMain(val inhalt : ArrayList<Model>, val layout : Int, val holderId : String,
                              val recylcerView : RecyclerView,
                              val showExistingUnterkontoInterface: ShowExistingUnterkontoInterface?,
                              val newInhalt : NewModel?)
    : RecyclerView.Adapter<RecyclerViewHolderMain>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolderMain {
        val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
        return RecyclerViewHolderMain(view,holderId)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolderMain, position: Int) {
        if(holderId == "ShowItems") {
            ShowAllDatasInRecyclerViewBinder(holder, holderId, inhalt, recylcerView).onStart()
        } else if(holderId == "ShowExistingUnterkontenItems") {
            ShowExistingUnterkontenInRecyclerViewBinder(holder, holderId, inhalt, recylcerView,
                showExistingUnterkontoInterface!!).onStart()
        } else if(holderId == "EndShowDataCalculate"){
            ShowCalculateDataBinding(holder,holderId,newInhalt!!,recylcerView)
                .onStart()
        }
    }

    override fun getItemCount(): Int {
        if(holderId == "EndShowDataCalculate"){
            return newInhalt!!.spaltenName1.size
        } else {
            return inhalt.size
        }
        return inhalt.size

    }


}