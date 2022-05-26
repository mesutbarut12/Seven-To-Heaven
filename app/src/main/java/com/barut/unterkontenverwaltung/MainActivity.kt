package com.barut.unterkontenverwaltung

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.calculate.Calculate
import com.barut.unterkontenverwaltung.action.GetData
import com.barut.unterkontenverwaltung.action.TransferDataFromPopupToSetItem
import com.barut.unterkontenverwaltung.action.PopupAlertDialogForCreateItem
import com.barut.unterkontenverwaltung.action.SetItem
import com.barut.unterkontenverwaltung.recyclerview.Model
import com.barut.unterkontenverwaltung.recyclerview.StartRecyclerView
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    private lateinit var add : FloatingActionButton
    private lateinit var showItems : FloatingActionButton
    private lateinit var showCalculateBetter : FloatingActionButton
    private lateinit var sqLiteMainEinkommen: SQLiteMain
    private lateinit var sqLiteMainUnterkonto: SQLiteMain
    private lateinit var sqLiteMainAusgabe: SQLiteMain
    private lateinit var recyclerView : RecyclerView


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Initalisiert alle views die an die verschiedenne klassen weiter gegeben werden!
        initAllViews()
        //Plus button wird geklickt! User darf sich aussuchen unterkonto oder Einnahme hinzufügen. je nachdem was er ausgewählt hat
        //wird seine eingaben im unterkonto oder im Einkommen sqlite datenbank gespeichert!

        popUpAlertDialogForSetDataInSQLite()
        showItemsInRecyclerViewClickListener()
        Calculate(sqLiteMainEinkommen,sqLiteMainUnterkonto,sqLiteMainAusgabe).calculate()


        showCalCulateDataBetterClickListener()
        showCalculateDataInRecyclerView()



    }

    fun showCalculateDataInRecyclerView(){
        val rechner = Calculate(sqLiteMainEinkommen,sqLiteMainUnterkonto,sqLiteMainAusgabe)
        val rechner1 = rechner.calculate()
        val rechner2 = rechner.calculateBetter(rechner1)
        val rechner3 = rechner.calculateWithAusgaben(rechner2)
        val getAusgabenAll = rechner.returnAusgaben()

        if(rechner3.isNotEmpty()) {
            val tvGesamtSaldo: TextView = findViewById(R.id.tvGesamtSaldo2)
            val tvGesamtAusgaben : TextView = findViewById(R.id.tvgesamtAusgaben)
            val tvVerfugbarerSaldo : TextView = findViewById(R.id.tvVerfugbaererSaldo)
            val gesamtSaldo = rechner3.get(0).datum
            val gesamtSaldoSplitted = gesamtSaldo.split(",")
            val f =  DecimalFormat("#0.00")
            val ergebnis = gesamtSaldoSplitted[1].toDouble() - getAusgabenAll.toDouble()
            tvGesamtSaldo.setText("Gesamt Saldo :\n ${f.format(gesamtSaldoSplitted[1].toDouble())}")
            tvGesamtAusgaben.setText("Gesamt Ausgaben :\n ${f.format(getAusgabenAll.toDouble())}")
            tvVerfugbarerSaldo.setText("Verfügbarer Saldo :\n ${f.format(ergebnis)}")
        }
        StartRecyclerView(this,recyclerView,rechner3,R.layout.end_model_show_datas_in_recyclerview,"EndShowDataCalculate",
            null)
    }
    fun showCalCulateDataBetterClickListener(){
        showCalculateBetter.setOnClickListener {
            //showCalCulateDataBetter()
            showCalculateDataInRecyclerView()

        }
    }



    fun popUpAlertDialogForSetDataInSQLite(){
        val popUp = PopupAlertDialogForCreateItem(this,add).setAlertDialogForSetUnterkontoOrEinnahme(object : TransferDataFromPopupToSetItem{
            override fun getClick(klick: Int, view: View, alertdialog: AlertDialogMain) {
                SetItem(klick,this@MainActivity,view,sqLiteMainUnterkonto.readData()).getData(object : GetData{
                    override fun getData(model: Model) {
                        if(model.databaseType == "Unterkonto"){
                            alertdialog.cancelDialog()
                            sqLiteMainUnterkonto.setData(model)
                            Toast.makeText(this@MainActivity,"erfolgreich hinzugefügt",Toast.LENGTH_LONG).show()
                        } else if (model.databaseType == "Einnahme") {
                            alertdialog.cancelDialog()
                            sqLiteMainEinkommen.setData(model)
                            Toast.makeText(this@MainActivity,"erfolgreich hinzugefügt",Toast.LENGTH_LONG).show()

                        } else if(model.databaseType == "Ausgabe"){
                            alertdialog.cancelDialog()
                            sqLiteMainAusgabe.setData(model)
                            Toast.makeText(this@MainActivity,"erfolgreich hinzugefügt",Toast.LENGTH_LONG).show()

                        }
                        showCalculateDataInRecyclerView()
                    }
                })


            }

        })

    }
    fun showItemsInRecyclerViewClickListener(){
        showItems.setOnClickListener {
            showItemsInRecyclerView()
        }
    }
    fun showItemsInRecyclerView(){

            val dataEinkommen = sqLiteMainEinkommen.readData()
            val dataUnterkonto = sqLiteMainUnterkonto.readData()
            val dataAusgabe = sqLiteMainAusgabe.readData()
            val arraylist : ArrayList<Model> = arrayListOf()

        for(i in dataEinkommen){
            arraylist.add(i)
        }
         for(i in dataUnterkonto){
                arraylist.add(i)
        }
        for(i in dataAusgabe){
                arraylist.add(i)
        }
            arraylist.sortWith(compareBy({ it.databaseType },{it.datum}))
            StartRecyclerView(this,recyclerView,
                arraylist,R.layout.show_items,"ShowItems",null)
    }



    fun initAllViews(){
        add  =  findViewById(R.id.addItem)
        showItems  =  findViewById(R.id.showList)
        showCalculateBetter  =  findViewById(R.id.showCalculateBetter)
        sqLiteMainEinkommen = SQLiteMain(this@MainActivity,"Einkommen","Einkommen",
            "unterkonto","datum","echtZeitDatum","databaseType","id")
        sqLiteMainUnterkonto = SQLiteMain(this@MainActivity,"Unterkonto","Unterkonto",
            "name","prozent","datum","databaseType","id")
        sqLiteMainAusgabe = SQLiteMain(this@MainActivity,"Ausgabe","Ausgabe",
            "unterkonto","ausgabe","datum","databaseType","id")

        recyclerView = findViewById(R.id.recyclerView)


    }



}