package com.barut.unterkontenverwaltung.calculate

import com.barut.unterkontenverwaltung.recyclerview.RecylcerViewModel
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain

class Calculate(val geld : SQLiteMain, val unterkonto : SQLiteMain,val ausgaben : SQLiteMain) {


    fun calculate() : ArrayList<RecylcerViewModel>{
        var arrayList : ArrayList<RecylcerViewModel> = arrayListOf()
        for(x in unterkonto.readData()){
            for(y in geld.readData()){
                val ergebnis = (x.spaltenName2.toFloat()/100) * y.spaltenName1.toInt()
                val model = RecylcerViewModel(x.spaltenName1,y.spaltenName1,ergebnis.toString(),x.spaltenName2)
                arrayList.add(model)
            }
        }

        return arrayList
    }
    //1 = unterkonto
    //2 = gesamt summe
    //3 prozent
    //4 ergebnis
    fun calculateBetter(arraylist : ArrayList<RecylcerViewModel>) : ArrayList<RecylcerViewModel>{
        val arraylistNew : ArrayList<RecylcerViewModel> = arrayListOf()
        for(y in unterkonto.readData()){
            var x = 0.0
            for(i in arraylist){
                if(i.spaltenName1Inhalt == y.spaltenName1) {
                    x += i.date.toDouble()
                }

            }

            val gesamtSummeAusrechnen = (x*100) / y.spaltenName2.toDouble()
           val model = RecylcerViewModel(y.spaltenName1,gesamtSummeAusrechnen.toString(),x.toString(),y.spaltenName2)
            arraylistNew.add(model)
        }
        return arraylistNew
    }
    fun calculateWithAusgaben(arraylist : ArrayList<RecylcerViewModel>) : ArrayList<RecylcerViewModel>{
        val arraylistNew : ArrayList<RecylcerViewModel> = arrayListOf()
        var model : RecylcerViewModel
        for (i in ausgaben.readData()){
            for(y in arraylist){
                println(i.spaltenName1 + " " + i.spaltenName2 + " " + i.databaseTyp + " " +
                i.echtZeitDatum)
                println("-----------------------------------------------")
                println(y.spaltenName1Inhalt + " " + y.spaltenName2Inhalt + " " + y.date + " " +
                y.databaseType)
                println("-----------------------------------------------")

                if(y.spaltenName1Inhalt == i.spaltenName2){
                    println(y.date.toDouble() - i.spaltenName1.toDouble())
                    var ergebnis = y.date.toDouble() - i.spaltenName1.toDouble()
                    model = RecylcerViewModel(y.spaltenName1Inhalt,y.date,ergebnis.toString(),y.databaseType)

                } else {
                    model = RecylcerViewModel(y.spaltenName1Inhalt,y.spaltenName2Inhalt,y.date,y.databaseType)
                }
                arraylistNew.add(model)
            }
        }
        return arraylistNew
    }
}