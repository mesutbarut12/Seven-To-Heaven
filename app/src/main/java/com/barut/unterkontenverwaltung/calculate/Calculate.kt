package com.barut.unterkontenverwaltung.calculate

import com.barut.unterkontenverwaltung.sqlite.SQLiteMain

class Calculate(val geld : SQLiteMain, val unterkonto : SQLiteMain) {


    //Geld
    //Spaltenname 1 = Summe
    //Spaltenname 2 = Datum

    fun getDataFromDataBase(){
        var model : CalculateModel
        var arrayList : ArrayList<CalculateModel> = arrayListOf()
        for(i in geld.readData()){
            model = CalculateModel(i.spaltenName1,i.spaltenName2)
            arrayList.add(model)
        }
        for(i in unterkonto.readData()){
            model = CalculateModel(i.spaltenName1,i.spaltenName2)
            arrayList.add(model)
        }
        for(i in arrayList){
            println(i.spaltenName1)
        }




    }






}