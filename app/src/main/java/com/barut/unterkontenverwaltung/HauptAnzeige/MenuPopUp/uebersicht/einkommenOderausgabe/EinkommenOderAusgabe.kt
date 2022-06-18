package com.barut.unterkontenverwaltung.HauptAnzeige.MenuPopUp.uebersicht.einkommenOderausgabe

import android.view.View
import com.barut.unterkontenverwaltung.DataTransferEinkommenOderAusgabe
import com.barut.unterkontenverwaltung.R
import com.google.android.material.tabs.TabLayout

class EinkommenOderAusgabe(private val view : View,
                           private val dataTransferEinkommenOderAusgabe: DataTransferEinkommenOderAusgabe) {

    val tabLayout : TabLayout = view.findViewById(R.id.ha_tabLayout)

    fun init(){

        if(tabLayout.getTabAt(0)!!.position == 0){
            dataTransferEinkommenOderAusgabe.data(0)
        }
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                auswahl(tab!!)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                auswahl(tab!!)
            }
        })

    }

    fun auswahl(tab : TabLayout.Tab?){
        if(tab!!.position == 0){
            dataTransferEinkommenOderAusgabe.data(0)
        } else if(tab!!.position == 1){
            dataTransferEinkommenOderAusgabe.data(1)
        }
    }
    fun setTabLayoutEinkommen(){
        tabLayout.getTabAt(0)!!.select()
        dataTransferEinkommenOderAusgabe.data(0)
    }
}
