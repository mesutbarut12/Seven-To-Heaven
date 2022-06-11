package com.barut.unterkontenverwaltung.calculate.uebersichtsanzeige

import android.content.Context
import com.barut.unterkontenverwaltung.sqlite.SQliteAusgaben
import com.barut.unterkontenverwaltung.sqlite.SQliteEinkommen
import com.barut.unterkontenverwaltung.sqlite.SQliteInit
import com.barut.unterkontenverwaltung.sqlite.SQliteUnterkonto
import java.text.DecimalFormat


class CalculateUebersichtsAnzeige(private val context: Context) {

    private val sQliteInit = SQliteInit(context)
    private lateinit var ausgabe: SQliteAusgaben
    private lateinit var einnahme: SQliteEinkommen
    private lateinit var unterkonto: SQliteUnterkonto
    private val dec = DecimalFormat("#.##")

    private fun initSqlite() {
        ausgabe = sQliteInit.ausgabe()
        einnahme = sQliteInit.einnahme()
        unterkonto = sQliteInit.unterkonto()
    }


    fun init() {
        initSqlite()
       /* println(uAGesamtSaldo())
        println(uAGesamtAusgaben())
        println(uAUnterkontoAnzahl())
        println(uAProzenteGesamt())
        println(uABeschreibungVorhanden())
*/

    }

    fun uAVerfügbarerSaldo() : String {
        var ergebnis = 0.0
        var ergebnisString = ""
        ergebnis = uAGesamtSaldo().toDouble() - uAGesamtAusgaben().toDouble()
        ergebnisString = dec.format(ergebnis)
        return ergebnisString
    }

    fun uAGesamtSaldo(): String {
        var ergebnis = 0.0
        var ergebnisString = ""
        for (i in einnahme.readData()) {
            ergebnis += i.summe.toDouble()
        }
        ergebnisString = dec.format(ergebnis)
        return ergebnisString
    }

    fun uAGesamtAusgaben(): String {
        var ergebnis = 0.0
        var ergebnisString = ""
        for (i in ausgabe.readData()) {
            ergebnis += i.summe.toDouble()
        }
        ergebnisString = dec.format(ergebnis)
        return ergebnisString
    }

    fun uAUnterkontoAnzahl(): String {
        var ergebnis = 0
        var ergebnisString = ""
        ergebnis = unterkonto.readData().size
        return "$ergebnis"
    }

    fun uAProzenteGesamt(): String {
        var ergebnis = 0.0
        var ergebnisString = ""
        //Hier muss ein system geschrieben werden
        // falls prozente über 100% gehen das es nicht möglich ist!!
        for (i in unterkonto.readData()) {
            ergebnis += i.prozent.toDouble()
        }
        ergebnisString = dec.format(ergebnis)
        return ergebnisString
    }

    fun uABeschreibungVorhanden(): String {
        var sizeEinnahmen = einnahme.readData().size
        var ergebnis = 0
        for (i in einnahme.readData()) {
            if (i.beschreibung.isNotEmpty()) {
                ergebnis += 1
            }
        }
        if (ergebnis == sizeEinnahmen && ergebnis != 0) {
            return "✓"
        }
        return "X"
    }


}

