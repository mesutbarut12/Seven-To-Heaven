package com.barut.unterkontenverwaltung.recyclerview.binder.viewpage2

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class ViewPage2Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val tvBeschreibungViewPager : TextView = itemView.findViewById(R.id.tvBeschreibungViewpager)
    val tvDatabaseTypViewpager : TextView = itemView.findViewById(R.id.tvDatabaseTypViewpager)
}