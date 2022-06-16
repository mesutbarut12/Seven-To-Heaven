package com.barut.unterkontenverwaltung.save

import android.content.Context
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.barut.unterkontenverwaltung.allgemein.sqlite.SQliteInit
import com.barut.unterkontenverwaltung.save.GetDataForSave
import com.barut.unterkontenverwaltung.save.SaveAndPut
import com.barut.unterkontenverwaltung.save.SaveModel


class GetAndSetDataInFirebase(val context: Context) {


    val sqlinit = SQliteInit(context)
    val saveAndPut = SaveAndPut(
        context,
        sqlinit.einnahme(),
        sqlinit.ausgabe(),
        sqlinit.unterkonto()
    )




    fun datenSpeichern(window : Window,swipe : SwipeRefreshLayout) {
        swipeRefreshing(true,swipe)
        windowsNotTouchable(window)
        val getDataForSave = GetDataForSave(context)
        val model = SaveModel(
            getDataForSave.getUnterkonto(),
            getDataForSave.getEinnahme(), getDataForSave.getAusgaben()
        )

        saveAndPut.save(model, object : DataFinish{
            override fun finish(boolean: Boolean) {
                if(boolean == true){
                    windowsTouchable(window)
                    swipeRefreshing(false,swipe)
                    Toast.makeText(context,"Daten erfolgreich gespeichert!",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    fun datenZiehen(window: Window,swipe: SwipeRefreshLayout){
        windowsNotTouchable(window)
        swipeRefreshing(true,swipe)
        saveAndPut.getData(object : DataFinish{
            override fun finish(boolean: Boolean) {
                if(boolean == true){
                    windowsTouchable(window)
                    swipeRefreshing(false,swipe)
                    Toast.makeText(context,"Daten erfolgreich gezogen!",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun windowsNotTouchable(window : Window){
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    fun windowsTouchable(window: Window){
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }
    fun swipeRefreshing(value : Boolean,swipe: SwipeRefreshLayout){
        swipe.isRefreshing = value
    }
}
