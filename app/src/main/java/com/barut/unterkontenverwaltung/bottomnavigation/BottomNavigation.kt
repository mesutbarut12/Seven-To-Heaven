package com.barut.unterkontenverwaltung.bottomnavigation

import android.content.Context
import android.view.MenuItem
import com.barut.unterkontenverwaltung.DataTransferUserAddedItem
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.bottomnavigation.additem.Starter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class BottomNavigation {


    //Die Klasse Starter startet den  Pop Up Alert Dialog
    // und anschließend connected der mit der funktion das
    // der user mit den buttons inteagieren kann

    fun init(
        bottomNavigationView: BottomNavigationView,
         context : Context,dataTransferUserAddedItem: DataTransferUserAddedItem
    ) {
        bottomNavigationView.setOnItemSelectedListener(object :
            NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                if(item.itemId == R.id.home) {


                } else if (item.itemId == R.id.addItem) {
                   var starter = Starter(context,dataTransferUserAddedItem)
                    starter.init()
                }
                return false
            }
        })
    }
}