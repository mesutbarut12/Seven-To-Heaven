package com.barut.unterkontenverwaltung.recyclerview.binder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.barut.unterkontenverwaltung.R
import com.barut.unterkontenverwaltung.alertdialog.AlertDialogMain
import com.barut.unterkontenverwaltung.recyclerview.UnterkontoModel
import com.barut.unterkontenverwaltung.recyclerview.RecyclerViewHolderMain
import com.barut.unterkontenverwaltung.recyclerview.NewModel
import com.barut.unterkontenverwaltung.recyclerview.binder.viewpage2.ViewPage2Adapter
import com.google.android.material.tabs.TabLayout
import java.text.DecimalFormat
import kotlin.math.roundToInt

class ShowCalculateDataBinding(
    val holder: RecyclerViewHolderMain, val id: String,
    val newInhalt: NewModel,
    val recyclerView: RecyclerView, val inhalt: ArrayList<UnterkontoModel>
) {
    fun onStart() {
        if (holder.differntHolder() != null) {
            if (id == "EndShowDataCalculate") {
                longClickListener()
                val showlist = holder.differntHolder()
                val unterkonto = showlist!!.get(0) as TextView
                val prozent = showlist!!.get(1) as TextView
                val guthaben = showlist!!.get(2) as TextView
                val ausgaben = showlist!!.get(3) as TextView
                val ergebnis = showlist!!.get(4) as TextView
                val beschreibungAusgabe = showlist!!.get(5) as TextView
                val beschreibungUnterkonto = showlist!!.get(6) as TextView

                val f = DecimalFormat("#0.0")
                unterkonto.setText("${newInhalt.spaltenName1.get(holder.adapterPosition)}")
                prozent.setText(
                    "Prozentuale Einteilung :  ${
                        runden(
                            newInhalt.spaltenName2.get(holder.adapterPosition).toDouble()
                        )
                    }%"
                )
                guthaben.setText(
                    "Guthaben : ${
                        runden(
                            newInhalt.databaseType.get(holder.adapterPosition).toDouble()
                        )
                    }"
                )
                ausgaben.setText(
                    "Ausgaben : ${
                        runden(
                            newInhalt.datum.get(holder.adapterPosition).toDouble()
                        )
                    }"
                )
                var ergebnis1 =
                    newInhalt.databaseType.get(holder.adapterPosition)
                        .toDouble() - newInhalt.datum.get(
                        holder.adapterPosition
                    ).toDouble()
                ergebnis.setText("Saldo : ${runden(ergebnis1)}")

                beschreibungAusgabe.setText("X")
                beschreibungUnterkonto.setText("X")


                for (i in newInhalt.id) {

                    if (i == "unterkonto") {
                        beschreibungUnterkonto.setText("✓")
                    } else if (i == "ausgabe") {
                        beschreibungAusgabe.setText("✓")
                    } else if (i == "aLeer") {
                        beschreibungAusgabe.setText("X")
                    } else if (i == "uLeer") {
                        beschreibungUnterkonto.setText("X")
                    }
                }
            }
        }
    }

    fun runden(double: Double): Double {
        var roundOff = (double * 100.0).roundToInt() / 100.0
        return roundOff
    }

    fun longClickListener() {
        holder.itemView.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(p0: View?): Boolean {
                val alert = initAlertDialog()
                val view = getViewAlertDialog(alert)
                val viewPager2 = initViewPager(view)
                createViewPager(viewPager2, getDataForTabLayout2(0))
                alert.createDialog()
                tabLayoutSelectedListener(view, viewPager2)
                return true
            }

        })
    }

    fun tabLayoutSelectedListener(view: View, viewPager2: ViewPager2) {
        val tablayout2 = initTabLayout2(view)
        codeForTabLayout2(tablayout2,viewPager2)
    }

    fun initAlertDialog(): AlertDialogMain {
        val alert =
            AlertDialogMain(holder.itemView.context, R.layout.item_click_long_listener)
        return alert
    }

    fun getViewAlertDialog(alert: AlertDialogMain): View {
        val view = alert.setLayout()
        return view
    }

    fun initViewPager(view: View): ViewPager2 {
        val viewpager2: ViewPager2 = view.findViewById(R.id.viewpager2)
        return viewpager2
    }

    fun initTabLayout2(view: View): TabLayout {
        val tablayout = view.findViewById<TabLayout>(R.id.tabLayout2)
        return tablayout
    }

    fun createViewPager(viewPager2: ViewPager2, inhalt1: ArrayList<UnterkontoModel>) {
        val adapter = ViewPage2Adapter(inhalt1)
        viewPager2.adapter = adapter
    }

    fun getDataForTabLayout2(position: Int): ArrayList<UnterkontoModel> {
        val arrayList: ArrayList<UnterkontoModel> = arrayListOf()
        for (i in inhalt) {
            if (position == 0) {
                if (i.databaseType == "Unterkonto") {
                    arrayList.add(i)
                }
            } else if (position == 1) {
                if (i.databaseType == "Einnahme") {
                    arrayList.add(i)
                }
            } else if (position == 2) {
                if (i.databaseType == "Ausgabe") {
                    arrayList.add(i)
                }
            }
        }
        return arrayList
    }

    fun codeForTabLayout2(tablayout2 : TabLayout,viewPager2: ViewPager2){


                tablayout2.getTabAt(0)?.setText("Unterkonto")
                tablayout2.getTabAt(1)?.setText("Einkommen")
                tablayout2.getTabAt(2)?.setText("Ausgabe")
                tablayout2.addOnTabSelectedListener(object :
                    TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab1: TabLayout.Tab?) {
                        if (tab1 != null) {
                            if (tab1.position == 0) {
                                createViewPager(viewPager2, getDataForTabLayout2(0))

                            } else if (tab1.position == 1) {
                                createViewPager(viewPager2, getDataForTabLayout2(1))

                            } else if (tab1.position == 2) {
                                createViewPager(viewPager2, getDataForTabLayout2(2))


                            }
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {

                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {
                    }
                })

            }


}