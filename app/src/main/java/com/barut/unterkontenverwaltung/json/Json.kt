package com.barut.unterkontenverwaltung.json

import com.google.gson.Gson

class Json {

    init {
        val einkauf: HashMap<String, String> = hashMapOf()
        einkauf.set("id", "Test")
        einkauf.set("unterkonto", "Test")
        einkauf.set("datum", "Test")
        einkauf.set("echtZeitDatum", "Test")
        einkauf.set("beschreibung", "Test")
        einkauf.set("databaseType", "Test")
        val ausgabe: HashMap<String, String> = hashMapOf()
        ausgabe.set("id","test")
        ausgabe.set("unterkonto","test")
        ausgabe.set("ausgabe","test")
        ausgabe.set("datum","test")
        ausgabe.set("beschreibung","test")
        ausgabe.set("databaseType","test")
        val unterkonto: HashMap<String, String> = hashMapOf()
        unterkonto.set("id","test")
        unterkonto.set("name","test")
        unterkonto.set("prozent","test")
        unterkonto.set("datum","test")
        unterkonto.set("beschreibung","test")
        unterkonto.set("databaseType","test")

        val uebersichtUnterkonten: HashMap<String, HashMap<String, String>> = hashMapOf()
        uebersichtUnterkonten.set("Einkauf", einkauf)
        uebersichtUnterkonten.set("Ausgabe",ausgabe)
        uebersichtUnterkonten.set("Unterkonto",unterkonto)

        val json = Gson()
        println(json.toJson(uebersichtUnterkonten))


    }
}