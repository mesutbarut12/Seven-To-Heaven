package com.barut.unterkontenverwaltung.recyclerview

data class UnterkontoModel(
    val name: String, val prozent: String,
    val datum: String, val databaseType: String, val id: String, val beschreibung: String,
)

data class EinkommenModel(
    val summe: String,
    val datum: String, val databaseType: String, val id: String, val beschreibung: String,
)
data class AusgabenModel(
    val unterkonto: String, val summe: String,
    val datum: String, val databaseType: String, val id: String, val beschreibung: String,
)
data class UserIdModel(
   val id : String
)

data class NewModel(
    val spaltenName1: ArrayList<String>, val spaltenName2: ArrayList<String>,
    val datum: ArrayList<String>, val databaseType: ArrayList<String>, val id: ArrayList<String>
)




