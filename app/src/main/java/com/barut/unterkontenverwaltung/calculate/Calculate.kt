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
}