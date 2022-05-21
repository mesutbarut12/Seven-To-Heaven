package com.barut.unterkontenverwaltung

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.einnahmeunterkontosetzen.GetData
import com.barut.unterkontenverwaltung.einnahmeunterkontosetzen.TransferDataFromPopupToSetItem
import com.barut.unterkontenverwaltung.einnahmeunterkontosetzen.PopupAlertDialogForCreateItem
import com.barut.unterkontenverwaltung.einnahmeunterkontosetzen.SetItem
import com.barut.unterkontenverwaltung.recyclerview.RecylcerViewModel
import com.barut.unterkontenverwaltung.showitems.ShowItems
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain
import com.barut.unterkontenverwaltung.sqlite.SQLiteModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate


class MainActivity : AppCompatActivity() {

    private lateinit var add : FloatingActionButton
    private lateinit var showItems : FloatingActionButton
    private lateinit var sqLiteMainEinkommen: SQLiteMain
    private lateinit var sqLiteMainUnterkonto: SQLiteMain
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
        showItemsInRecyclerView()




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
                        } else {
                            alertdialog.cancelDialog()
                            sqLiteMainEinkommen.setData(model)
                            Toast.makeText(this@MainActivity,"erfolgreich hinzugefügt",Toast.LENGTH_LONG).show()

                        }

                    }
                })


            }

        })

    }
    fun showItemsInRecyclerView(){
        showItems.setOnClickListener {
            val dataEinkommen = sqLiteMainEinkommen.readData()
            val dataUnterkonto = sqLiteMainUnterkonto.readData()
            val arraylist : ArrayList<SQLiteModel> = arrayListOf()

            for((i,y) in dataEinkommen.zip(dataUnterkonto)){
                arraylist.add(y)
                arraylist.add(i)
        }

            val showItems = ShowItems(this, recyclerView, arraylist)
            showItems.transformSqlToRecyclerModel()
            showItems.showItems()



        }
    }
    fun initAllViews(){
        add  =  findViewById(R.id.addItem)
        showItems  =  findViewById(R.id.showList)
        sqLiteMainEinkommen = SQLiteMain(this@MainActivity,"Einkommen","Einkommen",
            "unterkonto","datum","echtZeitDatum","databaseType","id")
        sqLiteMainUnterkonto = SQLiteMain(this@MainActivity,"Unterkonto","Unterkonto",
            "name","prozent","datum","databaseType","id")
        recyclerView = findViewById(R.id.recyclerView)

    }


}