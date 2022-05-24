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
import com.barut.unterkontenverwaltung.einnahmeunterkontosetzen.GetData
import com.barut.unterkontenverwaltung.einnahmeunterkontosetzen.TransferDataFromPopupToSetItem
import com.barut.unterkontenverwaltung.einnahmeunterkontosetzen.PopupAlertDialogForCreateItem
import com.barut.unterkontenverwaltung.einnahmeunterkontosetzen.SetItem
import com.barut.unterkontenverwaltung.recyclerview.RecylcerViewModel
import com.barut.unterkontenverwaltung.recyclerview.StartRecyclerView
import com.barut.unterkontenverwaltung.showexistingunterkonten.ShowExistingUnterkontenInRecyclerView
import com.barut.unterkontenverwaltung.showitems.ShowItems
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain
import com.barut.unterkontenverwaltung.sqlite.SQLiteModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat
import java.time.LocalDate


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

        //showCalCulateDataClickListener()
        //showCalCulateDataBetter()
        showCalCulateDataBetterClickListener()
        showCalculateWithAusgaben()

        //updates
        updateGesamtSaldo()

    }
    fun updateGesamtSaldo(){
        val rechner = Calculate(sqLiteMainEinkommen,sqLiteMainUnterkonto,sqLiteMainAusgabe)
        val rechner1 = rechner.calculate()
        val rechner2 = rechner.calculateBetter(rechner1)
        val rechner3 = rechner.calculateWithAusgaben(rechner2)
        if(rechner3.isNotEmpty()) {
            val tvGesamtSaldo: TextView = findViewById(R.id.tvGesamtSaldo2)
            val gesamtSaldo = rechner3.get(0).date
            val gesamtSaldoSplitted = gesamtSaldo.split(",")
            val f =  DecimalFormat("#0.00")
            tvGesamtSaldo.setText("Gesamt Saldo : ${f.format(gesamtSaldoSplitted[1].toDouble())}")
        }
    }
    fun showCalculateWithAusgaben(){
        val rechner = Calculate(sqLiteMainEinkommen,sqLiteMainUnterkonto,sqLiteMainAusgabe)
        val rechner1 = rechner.calculate()
        val rechner2 = rechner.calculateBetter(rechner1)
        val rechner3 = rechner.calculateWithAusgaben(rechner2)
        val getAusgabenAll = rechner.returnAusgaben()

        if(rechner3.isNotEmpty()) {
            val tvGesamtSaldo: TextView = findViewById(R.id.tvGesamtSaldo2)
            val tvGesamtAusgaben : TextView = findViewById(R.id.tvgesamtAusgaben)
            val tvVerfugbarerSaldo : TextView = findViewById(R.id.tvVerfugbaererSaldo)
            val gesamtSaldo = rechner3.get(0).date
            val gesamtSaldoSplitted = gesamtSaldo.split(",")
            val f =  DecimalFormat("#0.00")
            val ergebnis = gesamtSaldoSplitted[1].toDouble() - getAusgabenAll.toDouble()
            tvGesamtSaldo.setText("Gesamt Saldo : ${f.format(gesamtSaldoSplitted[1].toDouble())}")
            tvGesamtAusgaben.setText("Gesamt Ausgaben : ${f.format(getAusgabenAll.toDouble())}")
            tvVerfugbarerSaldo.setText("Verfügbarer Saldo : ${f.format(ergebnis)}")
        }
        StartRecyclerView(this,recyclerView,rechner3,R.layout.end_model_show_datas_in_recyclerview,"EndShowDataCalculate",
            null)
    }
    fun showCalCulateData(){
        val rechner = Calculate(sqLiteMainEinkommen,sqLiteMainUnterkonto,sqLiteMainAusgabe)
        val rechner1 = rechner.calculate()
        StartRecyclerView(this,recyclerView,rechner1,R.layout.show_calculate,"ShowCalculateData",
        null)
    }
    fun showCalCulateDataClickListener(){

            showCalCulateData()
            updateGesamtSaldo()

    }
    fun showCalCulateDataBetterClickListener(){
        showCalculateBetter.setOnClickListener {
            //showCalCulateDataBetter()
            showCalculateWithAusgaben()
            updateGesamtSaldo()
        }
    }
    fun showCalCulateDataBetter(){
        val rechner = Calculate(sqLiteMainEinkommen,sqLiteMainUnterkonto,sqLiteMainAusgabe)
        val rechner1 = rechner.calculate()
        val rechner2 = rechner.calculateBetter(rechner1)
        StartRecyclerView(this,recyclerView,rechner2,R.layout.show_calculate,"ShowCalculateData",
        null)
    }
    fun popUpAlertDialogForSetDataInSQLite(){
        val popUp = PopupAlertDialogForCreateItem(this,add).setAlertDialogForSetUnterkontoOrEinnahme(object : TransferDataFromPopupToSetItem{
            override fun getClick(klick: Int, view: View, alertdialog: AlertDialogMain) {
                SetItem(klick,this@MainActivity,view).getData(object : GetData{
                    override fun getData(model: SQLiteModel) {
                        if(model.databaseTyp == "Unterkonto"){
                            alertdialog.cancelDialog()
                            sqLiteMainUnterkonto.setData(model)
                            Toast.makeText(this@MainActivity,"erfolgreich hinzugefügt",Toast.LENGTH_LONG).show()
                        } else if (model.databaseTyp == "Einnahme") {
                            alertdialog.cancelDialog()
                            sqLiteMainEinkommen.setData(model)
                            Toast.makeText(this@MainActivity,"erfolgreich hinzugefügt",Toast.LENGTH_LONG).show()

                        } else if(model.databaseTyp == "Ausgabe"){
                            alertdialog.cancelDialog()
                            sqLiteMainAusgabe.setData(model)
                            Toast.makeText(this@MainActivity,"erfolgreich hinzugefügt",Toast.LENGTH_LONG).show()

                        }
                        updateGesamtSaldo()
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
            val arraylist : ArrayList<SQLiteModel> = arrayListOf()

        for(i in dataEinkommen){
            arraylist.add(i)
        }
         for(i in dataUnterkonto){
                arraylist.add(i)
        }
        for(i in dataAusgabe){
                arraylist.add(i)
        }

            arraylist.sortWith(compareBy({ it.databaseTyp },{it.echtZeitDatum}))
            val showItems = ShowItems(this, recyclerView, arraylist)
            showItems.transformSqlToRecyclerModel()
            showItems.showItems()




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