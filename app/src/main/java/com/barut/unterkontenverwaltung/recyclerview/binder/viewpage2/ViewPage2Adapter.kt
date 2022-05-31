package com.barut.unterkontenverwaltung.recyclerview.binder.viewpage2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.recyclerview.Model

class ViewPage2Adapter(val inhalt : ArrayList<Model>) : RecyclerView.Adapter<ViewPage2Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPage2Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_viewpager2,
        parent,false)
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        return ViewPage2Holder(view)
    }

    override fun onBindViewHolder(holder: ViewPage2Holder, position: Int) {
        val tvBeschreibungViewPager = holder.tvBeschreibungViewPager
        val tvDatabaseTypViewpager = holder.tvDatabaseTypViewpager

        tvBeschreibungViewPager.setText(inhalt.get(position).beschreibung)
        tvDatabaseTypViewpager.setText(inhalt.get(position).databaseType)
    }

    override fun getItemCount(): Int {
        println(inhalt.size)
       return inhalt.size
    }
}