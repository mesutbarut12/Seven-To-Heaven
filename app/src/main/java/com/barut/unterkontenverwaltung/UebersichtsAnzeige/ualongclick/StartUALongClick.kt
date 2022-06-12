package com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick

import android.content.Context
import android.widget.LinearLayout
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtausgaben.verbindenundstarten.UALCGesamtAusgaben
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.gesamtsaldo.verbindenundstarten.UALCGesamtSaldo
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.ualongclick.verfugbarersaldo.verbindenundstarten.UaVSLongClick

class StartUALongClick(private val context: Context,private val clickArea : LinearLayout,
private val key : String) {

    fun init(){
        if(key == "UAGesamdSaldo") {
            UALCGesamtSaldo(clickArea, context).init()
        } else if(key == "UAVerfügbarerSaldo") {
            UaVSLongClick(clickArea, context).init()
        } else if(key == "UAGesamtAusgaben"){
            UALCGesamtAusgaben(clickArea,context).init()
        }
    }
}