package com.barut.unterkontenverwaltung.calculate

import com.barut.unterkontenverwaltung.recyclerview.Model
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain

class Calculate(val geld : SQLiteMain, val unterkonto : SQLiteMain,val ausgaben : SQLiteMain) {


    fun calculate() : ArrayList<Model>{
        var arrayList : ArrayList<Model> = arrayListOf()
        for(x in unterkonto.readData()){
            for(y in geld.readData()){
                val ergebnis = (x.spaltenName2.toDouble()/100) * y.spaltenName1.toDouble()
                val model = Model(x.spaltenName1,y.spaltenName1,ergebnis.toString(),x.spaltenName2,"")
                arrayList.add(model)
            }
        }

        return arrayList
    }
    //1 = unterkonto
    //2 = gesamt summe
    //3 prozent
    //4 ergebnis
    fun calculateBetter(arraylist : ArrayList<Model>) : ArrayList<Model>{
        val arraylistNew : ArrayList<Model> = arrayListOf()
        for(y in unterkonto.readData()){
            var x = 0.0
            for(i in arraylist){
                if(i.spaltenName1 == y.spaltenName1) {
                    x += i.datum.toDouble()
                }

            }

            val gesamtSummeAusrechnen = (x*100) / y.spaltenName2.toDouble()
           val model = Model(y.spaltenName1,gesamtSummeAusrechnen.toString(),x.toString(),y.spaltenName2,"")
            arraylistNew.add(model)
        }
        return arraylistNew
    }
    fun calculateWithAusgaben(arraylist : ArrayList<Model>) : ArrayList<Model>{
        val arraylistNew : ArrayList<Model> = arrayListOf()
        var model : Model
        for(y in arraylist){
            var ergebnis = y.datum.toDouble()
            for (i in ausgaben.readData()){




                if(y.spaltenName1 == i.spaltenName2){
                    ergebnis -= i.spaltenName1.toDouble()
                }

                }
            model = Model(y.spaltenName1,y.databaseType,y.datum+","+y.spaltenName2,ergebnis.toString(),
            "")

            arraylistNew.add(model)
            }
        return arraylistNew
        }
    fun returnAusgaben() : String{
        var ergebnis = 0.0

            for(i in ausgaben.readData()){

                    ergebnis += i.spaltenName1.toDouble()
                println(i.spaltenName1)


        }


        return ergebnis.toString()
    }
    fun getSizeUnterkonten() : Int{

        return unterkonto.readData().size
    }
    fun getProzentAnzahl() : String{
        var ergebnis = 0.0
        for(i in unterkonto.readData()){
            ergebnis += i.spaltenName2.toDouble()
        }
        return ergebnis.toString()
    }
    }
