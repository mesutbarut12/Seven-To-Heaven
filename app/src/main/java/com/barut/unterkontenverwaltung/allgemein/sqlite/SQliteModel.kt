package com.barut.unterkontenverwaltung.recyclerview

data class UnterkontoModel(
    val name: String, val prozent: String,
    val datum: String, val databaseType: String, val id: String, val beschreibung: String,
)

data class EinkommenModel(
    val summe: String,
    val datum: String, val databaseType: String, val id: String, val beschreibung: String,
)
data class EDirektModel(
    val summe: String,
    val datum: String, val databaseType: String, val id: String, val beschreibung: String,
    val unterkonto: String
)
data class AusgabenModel(
    val unterkonto: String, val summe: String,
    val datum: String, val databaseType: String, val id: String, val beschreibung: String,
)

data class CalculateSqlModel(
    val unterkonto: String, val prozent: String,
    val ausgaben: String, val guthaben: String, val ergebnis: String, val id: String
)

data class UserIdModel(
    val id: String
)






