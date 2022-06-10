package com.barut.unterkontenverwaltung.calculate.hauptanzeige

import android.content.Context
import com.barut.unterkontenverwaltung.calculate.uebersichtsanzeige.CalculateUebersichtsAnzeige
import com.barut.unterkontenverwaltung.recyclerview.CalculateSqlModel
import com.barut.unterkontenverwaltung.recyclerview.hauptanzeige.HAHauptAnzeigeModel
import com.barut.unterkontenverwaltung.sqlite.*
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

    fun init(): HAHauptAnzeigeModel? {
        initAny()

        return null
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
        var ergebnis = 0.0
        for(i in 0..unterkonto.readData().size-1){
            ergebnis = hAGuthaben().get(i).toDouble() - hAAusgaben().get(i).toDouble()
            ergebnisString = dec.format(ergebnis)
            array.add(ergebnisString)
        }
        return array
    }

    fun hAGuthaben(): ArrayList<String> {
        var array: ArrayList<String> = arrayListOf()
        var gesamtSaldo = calculateUebersichtsAnzeige.uAGesamtSaldo()
        for (i in unterkonto.readData()) {
            var ergebnis = (gesamtSaldo.toDouble() / 100.0) * i.prozent.toDouble()
            var ergenisString = dec.format(ergebnis)
            array.add(ergenisString)
        }
        return array

    }

    fun hAErgebnis(): ArrayList<String> {
        var arrayAusgabe: ArrayList<String> = arrayListOf()
        var ergebnisStringAusgabe = ""

        //Ausgaben raus filtern!
        for (i in unterkonto.readData()) {
            var ergebnis = 0.0
            for (y in ausgabe.readData()) {
                if (i.name == y.unterkonto) {
                    ergebnis += y.summe.toDouble()

                }
                ergebnisStringAusgabe = dec.format(ergebnis)
                arrayAusgabe.add(ergebnisStringAusgabe)
            }


        }

        return arrayListOf()
    }

    fun setDataInDataBase() {
        println("--------------------------")
        println(hAunterkontoName() + " Unterkonto Name")
        println(hAProzentEinteilung() + " Prozent Einteilung")
        println(hAAusgaben() + " Ausgaben")
        println(hAGuthaben() + " Guthaben")
        println(hAErgebnis() + " Ergebnis")
        println("--------------------------")

        context.deleteDatabase("Calculate")
        for (i in 0..hAunterkontoName().size-1) {

            var model = CalculateSqlModel(
                hAunterkontoName().get(i),
                hAProzentEinteilung().get(i), "",
                hAGuthaben().get(i), "", ""
            )
            sqlCalculate.setData(model)

        }

    }

}