package com.barut.unterkontenverwaltung.calculate.starter

import android.content.Context
import android.view.View
import android.widget.TextView
import com.barut.unterkontenverwaltung.calculate.uebersichtsanzeige.CalculateUebersichtsAnzeige


private lateinit var verfugbarerSaldo : TextView
private lateinit var gesamtSaldo : TextView
private lateinit var gesamtAusgaben : TextView
private lateinit var unterkontoAnzahl : TextView
private lateinit var prozenteGesamt : TextView
private lateinit var beschreibungVorhanden : TextView

private lateinit var calculateUebersichtsAnzeige : CalculateUebersichtsAnzeige



class CalculateStarter(
    private val context: Context,
    private val arrayList: ArrayList<View>
) {

    fun initViews(){
        verfugbarerSaldo = arrayList[0] as TextView
        gesamtSaldo = arrayList[1] as TextView
        gesamtAusgaben = arrayList[2] as TextView
        unterkontoAnzahl = arrayList[3] as TextView
        prozenteGesamt = arrayList[4] as TextView
        beschreibungVorhanden = arrayList[5] as TextView
    }
    fun initCalculate(){
        calculateUebersichtsAnzeige = CalculateUebersichtsAnzeige(context)
        calculateUebersichtsAnzeige.init()
    }

    fun initUebersichtsAnzeige() {
        initViews()
        initCalculate()
        verfugbarerSaldo.setText(calculateUebersichtsAnzeige.uAVerfügbarerSaldo())
        gesamtSaldo.setText(calculateUebersichtsAnzeige.uAGesamtSaldo())
        gesamtAusgaben.setText(calculateUebersichtsAnzeige.uAGesamtAusgaben())
        unterkontoAnzahl.setText(calculateUebersichtsAnzeige.uAUnterkontoAnzahl())
        prozenteGesamt.setText(calculateUebersichtsAnzeige.uAProzenteGesamt())
        beschreibungVorhanden.setText(calculateUebersichtsAnzeige.uABeschreibungVorhanden())
    }

    fun update(){
        initUebersichtsAnzeige()
    }
}