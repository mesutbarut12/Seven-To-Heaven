package com.barut.unterkontenverwaltung.recyclerview

data class Model(val spaltenName1 : String, val spaltenName2 : String,
                 val echtZeitDatum : String, val databaseType : String, val id : String, val beschreibung : String,
                 val userInputDatum : String)


data class NewModel(val spaltenName1 : ArrayList<String>, val spaltenName2 : ArrayList<String>,
                 val datum : ArrayList<String>, val databaseType : ArrayList<String>, val id : ArrayList<String>)




