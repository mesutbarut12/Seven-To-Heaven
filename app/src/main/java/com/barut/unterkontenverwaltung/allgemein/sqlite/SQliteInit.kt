package com.barut.unterkontenverwaltung.allgemein.sqlite

import android.content.Context

class SQliteInit(val context: Context) {



    fun unterkonto(): SQliteUnterkonto {

        var unterkonto = SQliteUnterkonto(
            context, "Unterkonto", "unterkonto",
            "name", "prozent", "datum", "datanaseType", "id", "beschreibung"
        )
        return unterkonto
    }

    fun einnahme(): SQliteEinkommen {
        var einkommen = SQliteEinkommen(
            context, "Einkommen", "einkommen", "summe",
            "datum", "databaseType", "id", "beschreibung"
        )
        return einkommen
    }
    fun eDirekt(): SQLiteEDirektHinzufugen {
        var eDirekt = SQLiteEDirektHinzufugen(
            context, "EDirekt", "edirekt", "summe",
            "datum", "databaseType", "id", "beschreibung"
        )
        return eDirekt
    }

    fun ausgabe(): SQliteAusgaben {
        var ausgabe = SQliteAusgaben(
            context, "Ausgabe", "ausgabe", "name", "summe",
            "datum", "databaseType", "id", "beschreibung"
        )
        return ausgabe
    }

    fun userId(): SQliteUserID {
        var userId = SQliteUserID(context, "UserID", "userId", "id")

        return userId
    }
    fun sqlCalculate() : SQliteCalculate {
        var calculate = SQliteCalculate(context,"Calculate","calculate",
        "unterkonto","prozent","ausgaben","guthaben","ergebnis",
        "id")
        return calculate
    }


}