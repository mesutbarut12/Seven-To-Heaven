package com.barut.unterkontenverwaltung.json

import com.barut.unterkontenverwaltung.recyclerview.Model
import com.google.gson.Gson

class Json(
    inhaltEinkauf: ArrayList<Model>,
    inhaltAusgabe: ArrayList<Model>,
    inhaltUnterkonto: ArrayList<Model>
) {

    init {
        val einkauf: HashMap<String, String> = hashMapOf()
        val arraylistEinkauf : ArrayList<HashMap<String,String>> = arrayListOf()
        if(inhaltEinkauf.isNotEmpty()) {
            for (i in 0..inhaltEinkauf.size-1) {
                einkauf.set("id", inhaltEinkauf.get(i).id)
                einkauf.set("unterkonto", inhaltEinkauf.get(i).spaltenName1)
                einkauf.set("datum", inhaltEinkauf.get(i).spaltenName2)
                einkauf.set("echtZeitDatum", inhaltEinkauf.get(i).datum)
                einkauf.set("beschreibung", inhaltEinkauf.get(i).beschreibung)
                einkauf.set("databaseType", inhaltEinkauf.get(i).databaseType)
                arraylistEinkauf.add(einkauf)
            }

        }
        val ausgabe: HashMap<String, String> = hashMapOf()
        ausgabe.set("id", "test")
        ausgabe.set("unterkonto", "test")
        ausgabe.set("ausgabe", "test")
        ausgabe.set("datum", "test")
        ausgabe.set("beschreibung", "test")
        ausgabe.set("databaseType", "test")
        val unterkonto: HashMap<String, String> = hashMapOf()
        unterkonto.set("id", "test")
        unterkonto.set("name", "test")
        unterkonto.set("prozent", "test")
        unterkonto.set("datum", "test")
        unterkonto.set("beschreibung", "test")
        unterkonto.set("databaseType", "test")

        val uebersichtUnterkonten: HashMap<String, HashMap<String, String>> = hashMapOf()
        //uebersichtUnterkonten.set("Einkauf", arraylistEinkauf)
        //uebersichtUnterkonten.set("Ausgabe", ausgabe)
        //uebersichtUnterkonten.set("Unterkonto", unterkonto)

        val json = Gson()
        println(json.toJson(inhaltEinkauf))


    }
}