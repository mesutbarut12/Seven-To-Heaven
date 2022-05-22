package com.barut.unterkontenverwaltung.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.showalldatasinRecyclerView.ShowAllDatasInRecyclerViewBinder
import com.barut.unterkontenverwaltung.showexistingunterkonten.ShowExistingUnterkontenInRecyclerViewBinder
import com.barut.unterkontenverwaltung.showexistingunterkonten.ShowExistingUnterkontoInterface

class RecyclerViewAdapterMain(val inhalt : ArrayList<RecylcerViewModel>,val layout : Int,val holderId : String,
val recylcerView : RecyclerView,
val showExistingUnterkontoInterface: ShowExistingUnterkontoInterface?)
    : RecyclerView.Adapter<RecyclerViewHolderMain>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolderMain {
        val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
        return RecyclerViewHolderMain(view,holderId)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolderMain, position: Int) {

        ShowAllDatasInRecyclerViewBinder(holder,holderId,inhalt,recylcerView).onStart()
        ShowExistingUnterkontenInRecyclerViewBinder(holder,holderId,inhalt,recylcerView,showExistingUnterkontoInterface!!).onStart()
    }

    override fun getItemCount(): Int {
        return inhalt.size
    }


}