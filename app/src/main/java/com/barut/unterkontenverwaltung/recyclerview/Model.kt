package com.barut.unterkontenverwaltung.recyclerview

data class Model(val spaltenName1 : String, val spaltenName2 : String,
                 val datum : String, val databaseType : String, val id : String)


data class NewModel(val spaltenName1 : ArrayList<String>, val spaltenName2 : ArrayList<String>,
                 val datum : ArrayList<String>, val databaseType : ArrayList<String>, val id : ArrayList<String>)
data class ZumAusrechnenVonSaldoMitAusgaben(val unterkontenName : ArrayList<String>,
                                            val ausgabenName : List<String>, val ausgaben : ArrayList<String>,
                                            val einnahmeAusgerechnet : ArrayList<String>    )



