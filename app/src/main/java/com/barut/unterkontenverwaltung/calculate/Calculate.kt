package com.barut.unterkontenverwaltung.calculate

import com.barut.unterkontenverwaltung.recyclerview.Model
import com.barut.unterkontenverwaltung.recyclerview.NewModel
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain
import java.text.DecimalFormat

class Calculate(val geld : SQLiteMain, val unterkonto : SQLiteMain,val ausgaben : SQLiteMain) {


    fun calculate() : CalculateModel{

        var gesamtSaldo = getGesamtSaldo()
        var gesamtAusgaben = getGesamtAusgaben()
        var verfugbarerSaldo = getVerfugbarerSaldo()
        var getUnterkontenAnzahl = getUnterkontenAnzahl()
        var getProzenteGesamt = getProzenteInsgesamt()

        val f = DecimalFormat("#0.00")
        return CalculateModel(f.format(gesamtSaldo).toDouble(),f.format(gesamtAusgaben).toDouble(),
            f.format(verfugbarerSaldo).toDouble(),f.format(getUnterkontenAnzahl).toDouble(),f.format(getProzenteGesamt).toDouble())
    }
    fun getGesamtSaldo() : Double{
        var ergebnis = 0.0
        for(i in geld.readData()){
            ergebnis += i.spaltenName1.toDouble()
        }
        return ergebnis
    }
    fun getGesamtAusgaben() : Double {
        var ergebnis = 0.0
        for(i in ausgaben.readData()){
            ergebnis += i.spaltenName2.toDouble()
        }
        return ergebnis
    }
    fun getVerfugbarerSaldo() : Double{
        var gesamtSaldo = getGesamtSaldo()
        var gesamtAusgaben = getGesamtAusgaben()
        var ergebnis = gesamtSaldo - gesamtAusgaben
        return ergebnis
    }
    fun getUnterkontenAnzahl() : Double {
        return unterkonto.readData().size.toDouble()
    }
    fun getProzenteInsgesamt() : Double{
        var ergebnis = 0.0
        for (i in unterkonto.readData()){
            ergebnis += i.spaltenName2.toDouble()
        }
        return ergebnis
    }

    fun calculateData() : NewModel {
        var unterkonten = getUnterkonto()
        var prozentualeEinteilung = getProzentualeEinteilung()
        var saldoMitAusgaben = getSaldoMitausgaben()
        var saldoFürDasUnterkonto = getSaldoFürDasUnterkonto()


        var model = NewModel(unterkonten,prozentualeEinteilung,saldoMitAusgaben,saldoFürDasUnterkonto,
            arrayListOf())
        return model
    }
    fun getUnterkonto() : ArrayList<String>{
        var arrayList : ArrayList<String> = arrayListOf()
        for(i in unterkonto.readData()){
            arrayList.add(i.spaltenName1)
        }
        return arrayList
    }
    fun getProzentualeEinteilung() : ArrayList<String>{
        var arrayList : ArrayList<String> = arrayListOf()
        for(i in unterkonto.readData()){
            arrayList.add(i.spaltenName2)
        }
        return arrayList
    }
    fun getSaldoMitausgaben() : ArrayList<String>{
        var arrayList : ArrayList<String> = arrayListOf()
        for(i in ausgaben.readData()){
            arrayList.add(i.spaltenName2)
        }
        return arrayList
    }
    fun getSaldoFürDasUnterkonto() : ArrayList<String>{
        var arrayList : ArrayList<String> = arrayListOf()
        return arrayList
    }

    }

data class CalculateModel(val gesamtSaldo : Double,val gesamtAusgaben : Double,val verfugbarerSaldo : Double,
val unterKontenAnzahl : Double,val prozenteGesamt : Double)