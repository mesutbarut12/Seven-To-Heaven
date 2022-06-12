package com.barut.unterkontenverwaltung.calculate.hauptanzeige

import android.content.Context
import com.barut.unterkontenverwaltung.UebersichtsAnzeige.calculate.CalculateUebersichtsAnzeige
import com.barut.unterkontenverwaltung.allgemein.sqlite.*
import com.barut.unterkontenverwaltung.recyclerview.CalculateSqlModel
import com.barut.unterkontenverwaltung.recyclerview.hauptanzeige.HAHauptAnzeigeModel
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
        println("--------------------------")
        println(hAunterkontoName() + " Unterkonto Name")
        println(hAProzentEinteilung() + " Prozent Einteilung")
        println(hAAusgaben() + " Ausgaben")
        println(hAGuthaben() + " Guthaben")
        println(hAErgebnis() + " Ergebnis")

        println("--------------------------")
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
            array.add(ergebnisstring)
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
                    ergebnis += i.summe.toDouble()
                }
            }
            ergebnisString = dec.format(ergebnis)
            array.add(ergebnisString + "." + y.name)
        }
        return array
    }

    fun hAGuthaben(): ArrayList<String> {
        var array: ArrayList<String> = arrayListOf()
        var gesamtSaldo = calculateUebersichtsAnzeige.uAGesamtSaldo()
        for (i in unterkonto.readData()) {
            var ergebnis = (gesamtSaldo.toDouble() / 100.0) * i.prozent.toDouble()
            var ergenisString = dec.format(ergebnis)
            array.add(ergenisString + "." + i.name)
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
                    guthabenG -= guthabenA
                }

            }
            var ergebnis = guthabenG
            var ergebnisString = dec.format(ergebnis)
            arraylistSumme.add(ergebnisString + "." + unterkontoG)


        }
        return arraylistSumme
    }

    fun setDataInDataBase(): HAHauptAnzeigeModel? {
        var modelHA: HAHauptAnzeigeModel

        context.deleteDatabase("Calculate")
        for (i in 0..hAunterkontoName().size - 1) {

            var model = CalculateSqlModel(
                hAunterkontoName().get(i),
                hAProzentEinteilung().get(i), hAAusgaben().get(i),
                hAGuthaben().get(i), hAErgebnis().get(i), ""
            )
            modelHA = HAHauptAnzeigeModel(
                hAunterkontoName(),
                hAProzentEinteilung(), hAAusgaben(),
                hAGuthaben(), hAErgebnis(),
            )

            sqlCalculate.setData(model)
            return modelHA

        }

        return null
    }

}