package com.barut.unterkontenverwaltung.recyclerview.binder.viewpage2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.recyclerview.Model

class ViewPage2Adapter(val inhalt: ArrayList<Model>) : RecyclerView.Adapter<ViewPage2Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPage2Holder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_viewpager2,
            parent, false
        )
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        return ViewPage2Holder(view)
    }

    override fun onBindViewHolder(holder: ViewPage2Holder, position: Int) {
        val tvBeschreibungViewPager = holder.tvBeschreibungViewPager
        val tvDatabaseTypViewpager = holder.tvDatabaseTypViewpager


        if (inhalt.get(position).beschreibung.isEmpty()) {
            tvBeschreibungViewPager.setText("Keine Beschreibung Vorhanden")
        } else {
            tvBeschreibungViewPager.setText(inhalt.get(position).beschreibung)
        }
        val wert = checkWelchesUnterkonto(position)
        tvDatabaseTypViewpager.setText(wert)
    }

    override fun getItemCount(): Int {
        return inhalt.size
    }

    fun checkWelchesUnterkonto (position : Int) : String{
        if(inhalt.get(position).databaseType == "Ausgabe"){
            return inhalt.get(position).spaltenName2
        } else if(inhalt.get(position).databaseType == "Einnahme"){
            return "Demnächste Verfügbar!"
        } else if(inhalt.get(position).databaseType == "Unterkonto"){
            return inhalt.get(position).spaltenName1
        }
        return " "
    }
}