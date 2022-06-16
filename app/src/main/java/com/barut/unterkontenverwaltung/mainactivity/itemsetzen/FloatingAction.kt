package com.barut.unterkontenverwaltung.mainactivity.itemsetzen

import android.content.Context
import com.barut.unterkontenverwaltung.DataTransferUserAddedItem
import com.barut.unterkontenverwaltung.mainactivity.itemsetzen.additem.Starter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FloatingAction(val context: Context ,val floatingAction: FloatingActionButton,
private val dataTransferUserAddedItem: DataTransferUserAddedItem) {

    fun init(){
        floatingAction.setOnClickListener {
            var starter = Starter(context,dataTransferUserAddedItem)
            starter.init()
        }
    }
}