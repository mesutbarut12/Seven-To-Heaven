package com.barut.unterkontenverwaltung.json

import android.content.Context
import android.widget.Toast
import com.barut.unterkontenverwaltung.recyclerview.Model
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Json(
    val context: Context, val einkommen: SQLiteMain,
    val ausgabe: SQLiteMain,
    val unterkonto: SQLiteMain
) {

    private val db = Firebase.firestore
    private lateinit var model: Model
    fun save(inhalt: HashMap<String, ArrayList<Model>>, userId: String,datafinsih : DataFinish) {

        db.collection("Speicher").document(userId).set(inhalt)
            .addOnSuccessListener {
                datafinsih.finish(true)
            }

    }

    fun getData(
        userId: String,
        datafinsih: DataFinish

        ) {

        val getDb = db.collection("Speicher").document(userId)
        getDb.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    if (document.data != null) {
                        deleteDataBaseValue("Einkommen")
                        deleteDataBaseValue("Ausgabe")
                        deleteDataBaseValue("Unterkonto")
                        for (i in document.data!!) {
                            val y = i.value as ArrayList<Map<String, String>>
                            y.forEach {
                                if (it["databaseType"] == "Einnahme") {
                                    model = Model(
                                        it["spaltenName1"].toString(),
                                        it["spaltenName2"].toString(),
                                        it["datum"].toString(),
                                        it["databaseType"].toString(),
                                        it["id"].toString(),
                                        it["beschreibung"].toString()
                                    )

                                    einkommen.setData(model)
                                    datafinsih.finish(true)

                                } else if (it["databaseType"] == "Ausgabe") {
                                    model = Model(
                                        it["spaltenName1"].toString(),
                                        it["spaltenName2"].toString(),
                                        it["datum"].toString(),
                                        it["databaseType"].toString(),
                                        it["id"].toString(),
                                        it["beschreibung"].toString()
                                    )

                                    ausgabe.setData(model)
                                    datafinsih.finish(true)

                                } else if (it["databaseType"] == "Unterkonto") {
                                    model = Model(
                                        it["spaltenName1"].toString(),
                                        it["spaltenName2"].toString(),
                                        it["datum"].toString(),
                                        it["databaseType"].toString(),
                                        it["id"].toString(),
                                        it["beschreibung"].toString()
                                    )

                                    unterkonto.setData(model)
                                    datafinsih.finish(true)

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
                    einkommen.deleateItem(i.spaltenName1, i.spaltenName2)
                }
            } else if (databaseType == "Ausgabe") {
                for (i in ausgabe.readData()) {
                    ausgabe.deleateItem(i.spaltenName1, i.spaltenName2)
                }
            } else if (databaseType == "Unterkonto") {
                for (i in unterkonto.readData()) {
                    unterkonto.deleateItem(i.spaltenName1, i.spaltenName2)
                }
            }
        }
}
interface DataFinish{
    fun finish(boolean: Boolean)
}

