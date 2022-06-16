package com.barut.unterkontenverwaltung.HauptAnzeige.calculate

import android.content.Context
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.calculate.CalculateUebersichtsAnzeige
import com.barut.unterkontenverwaltung.allgemein.sqlite.*
import com.barut.unterkontenverwaltung.recyclerview.CalculateSqlModel
import com.barut.unterkontenverwaltung.HauptAnzeige.recyclerview.HAHauptAnzeigeModel
import java.text.DecimalFormat

class CalculateHauptAnzeige(private val context: Context) {

    private val sQliteInit = SQliteInit(context)
    private lateinit var ausgabe: SQliteAusgaben
    private lateinit var einnahme: SQliteEinkommen
    private lateinit var unterkonto: SQliteUnterkonto
    private lateinit var sqlCalculate: SQliteCalculate

    private val dec = DecimalFormat("#.##")

    private lateinit var calculateUebersichtsAnzeige: CalculateUebersichtsAnzeige

    private fun initAny() {
        ausgabe = sQliteInit.ausgabe()
        einnahme = sQliteInit.einnahme()
        unterkonto = sQliteInit.unterkonto()
        sqlCalculate = sQliteInit.sqlCalculate()

        calculateUebersichtsAnzeige = CalculateUebersichtsAnzeige(context)
        calculateUebersichtsAnzeige.init()
    }

    fun init() {
        initAny()
        /*println("--------------------------")
        println(hAunterkontoName() + " Unterkonto Name")
        println(hAProzentEinteilung() + " Prozent Einteilung")
        println(hAAusgaben() + " Ausgaben")
        println(hAGuthaben() + " Guthaben")
        println(hAErgebnis() + " Ergebnis")
        println("--------------------------")*/

    }


    fun hAunterkontoName(): ArrayList<String> {
        var array: ArrayList<String> = arrayListOf()
        for (i in unterkonto.readData()) {
            array.add(i.name)
        }
        return array
    }

    fun hAProzentEinteilung(): ArrayList<String> {
        var array: ArrayList<String> = arrayListOf()
        var ergebnisstring = ""

        for (i in unterkonto.readData()) {
            ergebnisstring = dec.format(i.prozent.toDouble())
            var gecheckt = checkHasComma(ergebnisstring)
            array.add(gecheckt)
        }

        return array

    }

    fun hAAusgaben(): ArrayList<String> {
        var array: ArrayList<String> = arrayListOf()
        var ergebnisString = ""
        for (y in sQliteInit.unterkonto().readData()) {
            var ergebnis = 0.0
            for (i in sQliteInit.ausgabe().readData()) {
                if (i.unterkonto == y.name) {
                    var gecheckt = i.summe
                    ergebnis += gecheckt.toDouble()
                }
            }
            ergebnisString = dec.format(ergebnis)
            var gecheckt = checkHasComma(ergebnisString)
            array.add(gecheckt + "." + y.name)
        }
        return array
    }

    fun hAGuthaben(): ArrayList<String> {
        var array: ArrayList<String> = arrayListOf()
        var gesamtSaldo = calculateUebersichtsAnzeige.uAGesamtSaldo()
        for (i in unterkonto.readData()) {
            var ergebnis = (gesamtSaldo.toDouble() / 100.0) * i.prozent.toDouble()
            var ergenisString = dec.format(ergebnis)
            var gecheckt = checkHasComma(ergenisString)
            array.add(gecheckt + "." + i.name)
        }
        return array

    }

    fun hAErgebnis(): ArrayList<String> {
        val arraylistSumme: ArrayList<String> = arrayListOf()


        var guthaben = hAGuthaben()
        var ausgaben = hAAusgaben()


        for (i in guthaben) {
            var unterkontoG = i.split(".")[1]
            var guthabenG = i.split(".")[0].toDouble()
            for (y in ausgaben) {
                var unterkontoA = y.split(".")[1]
                var guthabenA = y.split(".")[0].toDouble()
                if (unterkontoA == unterkontoG) {
                    var checkGuthabeng = checkHasComma(guthabenG.toString()).toDouble()
                    var checkGuthabenA = checkHasComma(guthabenA.toString()).toDouble()
                    checkGuthabeng -= guthabenA
                }

            }
            var ergebnis = guthabenG
            var ergebnisString = dec.format(ergebnis)
            var gecheckt = checkHasComma(ergebnisString)

            arraylistSumme.add(gecheckt + "." + unterkontoG)


        }
        return arraylistSumme
    }

    fun setData(): HAHauptAnzeigeModel? {
        var modelHA: HAHauptAnzeigeModel


        modelHA = HAHauptAnzeigeModel(
            hAunterkontoName(),
            hAProzentEinteilung(), hAAusgaben(),
            hAGuthaben(), hAErgebnis(),
        )

        return modelHA

    }
    fun checkHasComma(zahl : String) : String{
        if(zahl.contains(",")){
            return zahl.replace(",",".")
        }
        return zahl
    }
}