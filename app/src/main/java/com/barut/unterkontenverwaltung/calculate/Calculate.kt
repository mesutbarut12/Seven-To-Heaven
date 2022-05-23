package com.barut.unterkontenverwaltung.calculate

import com.barut.unterkontenverwaltung.recyclerview.RecylcerViewModel
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain

class Calculate(val geld : SQLiteMain, val unterkonto : SQLiteMain,val ausgaben : SQLiteMain) {


    fun calculate() : ArrayList<RecylcerViewModel>{
        var arrayList : ArrayList<RecylcerViewModel> = arrayListOf()
        for(x in unterkonto.readData()){
            for(y in geld.readData()){
                val ergebnis = (x.spaltenName2.toDouble()/100) * y.spaltenName1.toDouble()
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
        for(y in arraylist){
            var ergebnis = y.date.toDouble()
            for (i in ausgaben.readData()){




                if(y.spaltenName1Inhalt == i.spaltenName2){
                    ergebnis -= i.spaltenName1.toDouble()
                }

                }
            model = RecylcerViewModel(y.spaltenName1Inhalt,y.databaseType,y.date+","+y.spaltenName2Inhalt,ergebnis.toString())

            arraylistNew.add(model)
            }
        return arraylistNew
        }

    }
