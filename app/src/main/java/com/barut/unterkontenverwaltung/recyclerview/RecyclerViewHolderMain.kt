package com.barut.unterkontenverwaltung.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class RecyclerViewHolderMain(itemView : View,val id : String) : RecyclerView.ViewHolder(itemView) {

    fun differntHolder() : ArrayList<View>?{
        val arraylist : ArrayList<View> = arrayListOf()
        if(id == "Test"){
            val tv : TextView = itemView.findViewById(R.id.tvspaltenname2)
            arraylist.add(tv)
            return arraylist
        }
        return null
    }



}