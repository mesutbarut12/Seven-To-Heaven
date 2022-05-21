package com.barut.unterkontenverwaltung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.barut.unterkontenverwaltung.einnahmeunterkontosetzen.GetData
import com.barut.unterkontenverwaltung.einnahmeunterkontosetzen.TransferDataFromPopupToSetItem
import com.barut.unterkontenverwaltung.einnahmeunterkontosetzen.PopupAlertDialogForCreateItem
import com.barut.unterkontenverwaltung.einnahmeunterkontosetzen.SetItem
import com.barut.unterkontenverwaltung.sqlite.SQLiteMain
import com.barut.unterkontenverwaltung.sqlite.SQLiteModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private lateinit var add : FloatingActionButton
    private lateinit var sqLiteMain: SQLiteMain


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initalisiert alle views die an die verschiedenne klassen weiter gegeben werden!
        initAllViews()


       val popUp = PopupAlertDialogForCreateItem(this,add).setAlertDialogForSetUnterkontoOrEinnahme(object : TransferDataFromPopupToSetItem{
           override fun getClick(klick: Int, view: View) {
                SetItem(klick,this@MainActivity,view).getData(object : GetData{
                    override fun getData(model: SQLiteModel) {
                        
                    }
                })


           }

       })

    }

    fun initAllViews(){
        add  =  findViewById(R.id.addItem)
        sqLiteMain = SQLiteMain(this@MainActivity,"des","test",
            "unterkonto","einnahme","id")

    }

}