package com.barut.unterkontenverwaltung.HauptAnzeige.longclickdeleteitem.recyclerview.unterkonto

data class HALCDModelUnterkonto(
    val datum: String, val unterkonto: String, val prozent: String,
    val beschreibung: String, val id: String
)

data class HALCDModelEinnahme(
    val datum: String, val einnahme: String, val beschreibung: String,
    val id: String
)


