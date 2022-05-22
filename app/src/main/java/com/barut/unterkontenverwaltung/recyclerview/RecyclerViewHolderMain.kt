package com.barut.unterkontenverwaltung.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R

class RecyclerViewHolderMain(itemView : View,val id : String) : RecyclerView.ViewHolder(itemView) {

    fun differntHolder() : ArrayList<View>?{
        val arraylistView : ArrayList<View> = arrayListOf()

        if(id == "ShowItems"){
            val image : ImageView = itemView.findViewById(R.id.iVShowList)
            val ivDelete : ImageView = itemView.findViewById(R.id.ivDelete)
            val tvspaltenname1 : TextView = itemView.findViewById(R.id.tvShowListSpaltenName1)
            val tvspaltenname2 : TextView = itemView.findViewById(R.id.tvShowListSpaltenName2)
            val tvEchtzeitDatum : TextView = itemView.findViewById(R.id.tvShowListEchtzeitDatum)
            arraylistView.add(image)
            arraylistView.add(ivDelete)
            arraylistView.add(tvspaltenname1)
            arraylistView.add(tvspaltenname2)
            arraylistView.add(tvEchtzeitDatum)
            return arraylistView
        } else if (id == "ShowExistingUnterkontenItems") {
            val tv : TextView = itemView.findViewById(R.id.tv)
            arraylistView.add(tv)
            return arraylistView
        } else {
            Toast.makeText(itemView.context,"Fehler beim Laden", Toast.LENGTH_LONG).show()
            return null
        }

        return null
    }



}