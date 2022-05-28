package com.barut.unterkontenverwaltung.calculate

import com.barut.unterkontenverwaltung.recyclerview.NewModel
import com.barut.unterkontenverwaltung.recyclerview.ZumAusrechnenVonSaldoMitAusgaben
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain
import java.text.DecimalFormat

class Calculate(val geld : SQLiteMain, val unterkonto : SQLiteMain,val ausgaben : SQLiteMain) {


    fun calculate() : CalculateModel{

        var gesamtSaldo = getGesamtSaldo()
        var gesamtAusgaben = getGesamtAusgaben()
        var verfugbarerSaldo = getVerfugbarerSaldo()
        var getUnterkontenAnzahl = getUnterkontenAnzahl()
        var getProzenteGesamt = getProzenteInsgesamt()

        //val f = DecimalFormat("#0.0")
        //return CalculateModel(f.format(gesamtSaldo).toDouble(),f.format(gesamtAusgaben).toDouble(),
        //    f.format(verfugbarerSaldo).toDouble(),f.format(getUnterkontenAnzahl).toDouble(),f.format(getProzenteGesamt).toDouble())

        return CalculateModel(gesamtSaldo,gesamtAusgaben,verfugbarerSaldo,getUnterkontenAnzahl,getProzenteGesamt)
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

            ergebnis += i.spaltenName1.toDouble()
        }
        return ergebnis
    }
    fun getVerfugbarerSaldo() : Double{
        var ergebnis = 0.0
        var gesamtSaldo = getGesamtSaldo()
        var gesamtAusgaben = getGesamtAusgaben()
        ergebnis = gesamtSaldo - gesamtAusgaben
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
        var ausgabenName = getAusgabenName()
        var prozentualeEinteilung = getProzentualeEinteilung()
        var saldoFürDasUnterkonto = getSaldoFürDasUnterkonto()
        var saldoMitAusgaben = getSaldoMitausgaben()
        var ausgaben = getAusgaben()


        var ausrechnenVonSaldoMitAusgaben = ZumAusrechnenVonSaldoMitAusgaben(unterkonten,ausgabenName
        ,ausgaben,saldoFürDasUnterkonto)

        var model = NewModel(unterkonten,prozentualeEinteilung,ausgaben,saldoFürDasUnterkonto,
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
        var ergebnis = 0.0
        var saldoFürUnterkonto = getSaldoFürDasUnterkonto()
        var ausgaben = getAusgabenName()



        return arrayList
    }
    fun getSaldoFürDasUnterkonto() : ArrayList<String>{
        var arrayList : ArrayList<String> = arrayListOf()
            var ergebnis = 0.0
            for(i in unterkonto.readData()) {
                ergebnis = (getGesamtSaldo()/100) * i.spaltenName2.toDouble()
                arrayList.add(ergebnis.toString())
            }

        return arrayList
    }
    fun getAusgabenName() : List<String> {
        var setList : MutableSet<String>
        setList = mutableSetOf()
        for (i in ausgaben.readData()) {
                setList.add(i.spaltenName2)
                }

        return setList.toList()
        }
    fun getAusgaben() : ArrayList<String>{
        var arrayList : ArrayList<String> = arrayListOf()
        for(y in unterkonto.readData()){
            var ergebnis = 0.0
            for(i in ausgaben.readData()){
                if(i.spaltenName2 == y.spaltenName1){
                    ergebnis += i.spaltenName1.toDouble()

                }
            }
            arrayList.add(ergebnis.toString())
        }
        return arrayList
    }

    }

data class CalculateModel(val gesamtSaldo : Double,val gesamtAusgaben : Double,val verfugbarerSaldo : Double,
val unterKontenAnzahl : Double,val prozenteGesamt : Double)