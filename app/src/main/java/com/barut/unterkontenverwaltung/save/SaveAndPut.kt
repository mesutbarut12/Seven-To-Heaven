package com.barut.unterkontenverwaltung.save

import android.content.Context
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQLiteEDirekt
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteAusgaben
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteEinkommen
import com.barut.unterkontenverwaltung.recyclerview.UnterkontoModel
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteUnterkonto
import com.barut.unterkontenverwaltung.mainactivity.userId.UserID
import com.barut.unterkontenverwaltung.recyclerview.AusgabenModel
import com.barut.unterkontenverwaltung.recyclerview.EDirektModel
import com.barut.unterkontenverwaltung.recyclerview.EinkommenModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SaveAndPut(
    val context: Context, val einkommen: SQliteEinkommen,
    val ausgabe: SQliteAusgaben,
    val unterkonto: SQliteUnterkonto,
    val edirekt : SQLiteEDirekt
) {

    private val db = Firebase.firestore
    private val getUserId = UserID(context).getUserId()

    fun save(inhalt:  SaveModel, datafinsih: DataFinish?) {

        db.collection("Speicher").document(getUserId).set(inhalt)
            .addOnSuccessListener {
                if (datafinsih != null) {
                    datafinsih!!.finish(true)
                }
            }

    }

    fun getData(
        datafinsih: DataFinish?

    ) {
        var zahl = 0
        val getDb = db.collection("Speicher").document(getUserId)
        getDb.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    if (document.data != null) {
                        deleteDataBaseValue("Einkommen")
                        deleteDataBaseValue("Ausgabe")
                        deleteDataBaseValue("Unterkonto")
                        deleteDataBaseValue("EDirekt")
                        for (i in document.data!!) {
                            val y = i.value as ArrayList<Map<String, String>>
                            println(y)
                            if (y.isNotEmpty()) {
                                y.forEach {

                                    if (it["databaseType"] == "einkommen") {
                                        val model = EinkommenModel(
                                            it["summe"].toString(),
                                            it["datum"].toString(),
                                            it["databaseType"].toString(),
                                            it["id"].toString(),
                                            it["beschreibung"].toString(),


                                        )

                                        einkommen.setData(model)
                                        if(datafinsih != null) {
                                            datafinsih!!.finish(true)
                                        }
                                    } else if (it["databaseType"] == "ausgabe") {
                                        val model = AusgabenModel(
                                            it["unterkonto"].toString(),
                                            it["summe"].toString(),
                                            it["datum"].toString(),
                                            it["databaseType"].toString(),
                                            it["id"].toString(),
                                            it["beschreibung"].toString(),

                                        )

                                        ausgabe.setData(model)
                                        if(datafinsih != null) {
                                            datafinsih!!.finish(true)
                                        }
                                    } else if (it["databaseType"] == "unterkonto") {
                                        val model = UnterkontoModel(
                                            it["name"].toString(),
                                            it["prozent"].toString(),
                                            it["datum"].toString(),
                                            it["databaseType"].toString(),
                                            it["id"].toString(),
                                            it["beschreibung"].toString(),

                                        )

                                        unterkonto.setData(model)
                                        if(datafinsih != null) {
                                            datafinsih!!.finish(true)
                                        }

                                    } else if(it["databaseType"] == "eDirekt"){
                                        val model = EDirektModel(
                                            it["summe"].toString(),
                                            it["datum"].toString(),
                                            it["databaseType"].toString(),
                                            it["id"].toString(),
                                            it["beschreibung"].toString(),
                                            it["unterkonto"].toString()

                                            )
                                        edirekt.setData(model)
                                        if(datafinsih != null){
                                            datafinsih!!.finish(true)
                                        }

                                    }


                                }
                            } else {
                                zahl += 1
                                if (zahl == 3) {
                                    if(datafinsih != null) {
                                        datafinsih!!.finish(true)
                                    }
                                }
                            }
                        }
                    }
                }

            }
    }

    fun deleteDataBaseValue(databaseType: String) {
        if (databaseType == "Einkommen") {
            for (i in einkommen.readData()) {
                val model = EinkommenModel(i.summe,i.datum,i.databaseType,i.id,i.beschreibung)
                einkommen.deleateItem(model)
            }
        } else if (databaseType == "Ausgabe") {
            for (i in ausgabe.readData()) {
                val model = AusgabenModel(i.unterkonto,i.summe,i.datum,i.databaseType,i.id,i.beschreibung)
                ausgabe.deleateItem(model)
            }
        } else if (databaseType == "Unterkonto") {
            for (i in unterkonto.readData()) {
                val model = UnterkontoModel(i.name,i.prozent,i.datum,i.databaseType,i.id,i.beschreibung)
                unterkonto.deleateItem(model)
            }
        }
    }

    fun userIdIsExists(userId: String, userIDExistsInterface: UserIDExistsInterface) {
        var zahl = 0
        db.collection("Speicher").get().addOnSuccessListener {
            it.forEach {
                if (userId == it.id) {
                    zahl = 1
                }
            }
            if (zahl == 1) {
                userIDExistsInterface.getData(true)
            } else if (zahl == 0) {
                userIDExistsInterface.getData(false)
            }
        }

    }
}

interface DataFinish {
    fun finish(boolean: Boolean)
}

interface UserIDExistsInterface {
    fun getData(boolean: Boolean)
}

