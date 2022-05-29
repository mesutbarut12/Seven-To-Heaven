package com.barut.unterkontenverwaltung.recyclerview.binder

import android.app.AlertDialog
import android.content.DialogInterface
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.recyclerview.RecyclerViewHolderMain
import com.barut.unterkontenverwaltung.recyclerview.Model
import com.barut.unterkontenverwaltung.recyclerview.StartRecyclerView
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ShowAllDatasInRecyclerViewBinder(
    val holder: RecyclerViewHolderMain, val id: String,
    val inhalt: ArrayList<Model>,
    val recyclerView: RecyclerView
) {


    private val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yy ")
    private val date = Date()
    private lateinit var image: ImageView
    private lateinit var ivDelete: ImageView
    private lateinit var tvspaltenname1: TextView
    private lateinit var tvspaltenname2: TextView
    private lateinit var echtzeitDatum: TextView
    private lateinit var ivEdit: ImageView

    val sqLiteMainEinkommen = SQLiteMain(
        holder.itemView.context, "Einkommen", "Einkommen",
        "unterkonto", "datum", "echtZeitDatum", "databaseType", "id"
    )
    val sqLiteMainUnterkonto = SQLiteMain(
        holder.itemView.context, "Unterkonto", "Unterkonto",
        "name", "prozent", "datum", "databaseType", "id"
    )
    val sqLiteMainAusgabe = SQLiteMain(
        holder.itemView.context, "Ausgabe", "Ausgabe",
        "unterkonto", "ausgabe", "datum", "databaseType", "id"
    )

    fun onStart() {
        init()
        if (holder.differntHolder() != null) {
            if (id == "ShowItems") {

                if (inhalt.get(holder.adapterPosition).databaseType == "Unterkonto") {
                    image.setImageResource(R.drawable.ic_unterkonto)
                } else if (inhalt.get(holder.adapterPosition).databaseType == "Einnahme") {
                    image.setImageResource(R.drawable.ic_input)
                } else {
                    image.setImageResource(R.drawable.ic_output)
                }
                tvspaltenname1.setText(inhalt.get(holder.adapterPosition).spaltenName1)
                tvspaltenname2.setText(inhalt.get(holder.adapterPosition).spaltenName2)
                echtzeitDatum.setText(inhalt.get(holder.adapterPosition).datum)
                onDelete(ivDelete)
                onEdit(ivEdit)
            }
        } else {
            Toast.makeText(holder.itemView.context, "Fehler beim Laden", Toast.LENGTH_LONG).show()
        }
    }

    private fun onDelete(ivDelete: ImageView) {
        ivDelete.setOnClickListener {
            if (inhalt.get(holder.adapterPosition).databaseType == "Unterkonto") {
                var adapter = holder.adapterPosition

                if (sqLiteMainAusgabe.readData().isNotEmpty()) {
                    for (i in sqLiteMainAusgabe.readData()) {

                        if (i.spaltenName2 == inhalt.get(adapter).spaltenName1) {
                            alertDialog(object : AlertDialogUser {
                                override fun getClick(klick: Int) {
                                    if (klick == 1) {
                                        sqLiteMainAusgabe.deleteArgs(inhalt.get(adapter).spaltenName1)
                                        sqLiteMainUnterkonto.deleateItem(
                                            inhalt.get(adapter)
                                                .spaltenName1, inhalt.get(adapter).spaltenName2
                                        )
                                    } else if (klick == 2) {
                                        sqLiteMainUnterkonto.deleateItem(
                                            inhalt.get(adapter)
                                                .spaltenName1, inhalt.get(adapter).spaltenName2
                                        )
                                    }
                                    val updateData = updateData()
                                    StartRecyclerView(
                                        holder.itemView.context, recyclerView,
                                        updateData, R.layout.show_items, "ShowItems", null, null
                                    )

                                }

                            })
                        } else {
                            sqLiteMainUnterkonto.deleateItem(
                                inhalt.get(adapter)
                                    .spaltenName1, inhalt.get(adapter).spaltenName2
                            )
                        }
                    }
                } else {
                    sqLiteMainUnterkonto.deleateItem(
                        inhalt.get(adapter)
                            .spaltenName1, inhalt.get(adapter).spaltenName2
                    )
                }


            } else if (inhalt.get(holder.adapterPosition).databaseType == "Einnahme") {
                sqLiteMainEinkommen.deleateItem(
                    inhalt.get(holder.adapterPosition)
                        .spaltenName1, inhalt.get(holder.adapterPosition).spaltenName2
                )
            } else {
                sqLiteMainAusgabe.deleateItem(
                    inhalt.get(holder.adapterPosition)
                        .spaltenName1, inhalt.get(holder.adapterPosition).spaltenName2
                )
            }
            val updateData = updateData()
            StartRecyclerView(
                holder.itemView.context, recyclerView,
                updateData, R.layout.show_items, "ShowItems", null, null
            )


        }
    }

    private fun updateData(): ArrayList<Model> {
        val arrayList: ArrayList<Model> = arrayListOf()
        for (i in sqLiteMainEinkommen.readData()) {
            arrayList.add(i)
        }
        for (i in sqLiteMainUnterkonto.readData()) {
            arrayList.add(i)
        }
        for (i in sqLiteMainAusgabe.readData()) {
            arrayList.add(i)
        }
        arrayList.sortWith(compareBy({ it.databaseType }, { it.datum }))
        return arrayList
    }

    private fun alertDialog(getClick: AlertDialogUser) {
        val alert = AlertDialog.Builder(holder.itemView.context)
        alert.setTitle("!!!1ACHTUNG!!!")
        alert.setMessage(
            "Du bist grad dabei ein Unterkonto zu löschen, sollen die damit verbundenen ausgaben" +
                    " mit gelöscht werden?"
        )
        alert.setPositiveButton("JA", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                getClick.getClick(1)

            }

        }).setNegativeButton("Nein", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                getClick.getClick(2)
            }

        }).create().show()
    }

    fun onEdit(ivEdit: ImageView) {
        ivEdit.setOnClickListener {
            val alert = AlertDialogMain(holder.itemView.context, R.layout.add_item)
            val view = alert.setLayout()
            val tvDescription1 = view.findViewById<TextView>(R.id.tvdescription1)
            val tvDescription2 = view.findViewById<TextView>(R.id.tvdescription2)
            val etInput1 = view.findViewById<EditText>(R.id.etInput1)
            val etInput2 = view.findViewById<EditText>(R.id.etInput2)
            val btEdit = view.findViewById<Button>(R.id.btSaveItems)
            alert.createDialog()

            if (inhalt.get(holder.adapterPosition).databaseType == "Ausgabe") {
                tvDescription1.setText("Summe ändern!")
                tvDescription2.setText("Name ändern!")
                etInput1.setText(inhalt.get(holder.adapterPosition).spaltenName1)
                etInput2.setText(inhalt.get(holder.adapterPosition).spaltenName2)
            } else if (inhalt.get(holder.adapterPosition).databaseType == "Unterkonto") {
                tvDescription1.setText("Name ändern!")
                tvDescription2.setText("Prozent ändern!")
                etInput1.setText(inhalt.get(holder.adapterPosition).spaltenName1)
                etInput2.setText(inhalt.get(holder.adapterPosition).spaltenName2)
            } else if (inhalt.get(holder.adapterPosition).databaseType == "Einnahme") {
                tvDescription1.setText("Summe ändern!")
                tvDescription2.setText("Datum ändern!")
                etInput1.setText(inhalt.get(holder.adapterPosition).spaltenName1)
                etInput2.setText(inhalt.get(holder.adapterPosition).spaltenName2)
            }

            btEdit.setOnClickListener {
                val model = Model(
                    etInput1.text.toString(),
                    etInput2.text.toString(),
                    "Tag der erstellung ${dateFormat.format(date)}",
                    inhalt.get(holder.adapterPosition).databaseType,
                    ""
                )

                if (inhalt.get(holder.adapterPosition).databaseType == "Ausgabe") {
                    sqLiteMainAusgabe.updateData(
                        inhalt.get(holder.adapterPosition).spaltenName2,
                        model
                    )
                    alert.cancelDialog()
                } else if (inhalt.get(holder.adapterPosition).databaseType == "Unterkonto") {

                    val model = checkProzentIsNot100(
                        etInput2.text.toString(),
                        etInput2.text.toString(),
                        etInput1.text.toString()
                    )
                    if (model != null) {
                        sqLiteMainUnterkonto.updateData(
                            inhalt.get(holder.adapterPosition).spaltenName2,
                            model
                        )
                    }
                    alert.cancelDialog()
                } else if (inhalt.get(holder.adapterPosition).databaseType == "Einnahme") {
                    sqLiteMainEinkommen.updateData(
                        inhalt.get(holder.adapterPosition).spaltenName2,
                        model
                    )
                    alert.cancelDialog()
                }

            }


        }
    }

    fun init() {
        val showlist = holder.differntHolder()
        image = showlist!!.get(0) as ImageView
        ivDelete = showlist!!.get(1) as ImageView
        tvspaltenname1 = showlist!!.get(2) as TextView
        tvspaltenname2 = showlist!!.get(3) as TextView
        echtzeitDatum = showlist!!.get(4) as TextView
        ivEdit = showlist!!.get(5) as ImageView
    }

    fun checkProzentIsNot100(
        prozentZahl: String, etInput1: String,
        etInput2: String
    ): Model? {
        var model: Model
        val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yy ")
        val date = Date()

        var ergebnis = prozentZahl.toDouble()
        println(prozentZahl + " Prozentzahl")
        println(holder.adapterPosition.toString() + " Adapter Position")
        for (i in inhalt) {
            if (i.databaseType == "Unterkonto") {
                if (i.spaltenName2 == inhalt.get(holder.adapterPosition).spaltenName2) {

                } else {
                    println(i.spaltenName2 + " Unterkonten Prozent!")
                    ergebnis += i.spaltenName2.toDouble()
                }
            }
        }
        if (ergebnis > 100.00) {
            Toast.makeText(
                holder.itemView.context,
                "Dieser schritt wird die 100% grenze überschreiten bitte versuche es erneut!",
                Toast.LENGTH_LONG
            ).show()
            return null

        } else {
            model = Model(
                etInput2,
                etInput1,
                "Tag der erstellung ${dateFormat.format(date)}",
                inhalt.get(holder.adapterPosition).databaseType,
                ""
            )
        }

        return model
    }
}

interface AlertDialogUser {
    fun getClick(klick: Int)
}