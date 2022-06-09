package com.barut.unterkontenverwaltung.calculate

import com.barut.unterkontenverwaltung.recyclerview.NewModel
import com.barut.unterkontenverwaltung.sqlite.SQliteUnterkonto

class Calculate(val geld: SQliteUnterkonto, val unterkonto: SQliteUnterkonto, val ausgaben: SQliteUnterkonto) {


    fun calculate(): CalculateModel {

        var gesamtSaldo = getGesamtSaldo()
        var gesamtAusgaben = getGesamtAusgaben()
        var verfugbarerSaldo = getVerfugbarerSaldo()
        var getUnterkontenAnzahl = getUnterkontenAnzahl()
        var getProzenteGesamt = getProzenteInsgesamt()
        var getBeschreibung = getBeschreibungVorhandenMainActivity()

        //val f = DecimalFormat("#0.0")
        //return CalculateModel(f.format(gesamtSaldo).toDouble(),f.format(gesamtAusgaben).toDouble(),
        //    f.format(verfugbarerSaldo).toDouble(),f.format(getUnterkontenAnzahl).toDouble(),f.format(getProzenteGesamt).toDouble())

        return CalculateModel(
            gesamtSaldo,
            gesamtAusgaben,
            verfugbarerSaldo,
            getUnterkontenAnzahl,
            getProzenteGesamt,getBeschreibung
        )
    }

    fun getGesamtSaldo(): Double {
        var ergebnis = 0.0
        for (i in geld.readData()) {
            ergebnis += i.name.toDouble()

        }

        return ergebnis
    }

    fun getGesamtAusgaben(): Double {
        var ergebnis = 0.0
        for (i in ausgaben.readData()) {

            ergebnis += i.name.toDouble()
        }
        return ergebnis
    }

    fun getVerfugbarerSaldo(): Double {
        var ergebnis = 0.0
        var gesamtSaldo = getGesamtSaldo()
        var gesamtAusgaben = getGesamtAusgaben()
        ergebnis = gesamtSaldo - gesamtAusgaben
        return ergebnis
    }

    fun getUnterkontenAnzahl(): Double {
        return unterkonto.readData().size.toDouble()
    }

    fun getProzenteInsgesamt(): Double {
        var ergebnis = 0.0
        for (i in unterkonto.readData()) {
            ergebnis += i.prozent.toDouble()
        }
        return ergebnis
    }

    fun getBeschreibungVorhandenMainActivity() : String{
        var beschreibungVorhanden = 0
        var beschreibungNichtVorhanden = 0

        for (i in geld.readData()){
            if(i != null) {
                if (i.beschreibung.isNotEmpty()) {
                    beschreibungVorhanden += 1
                } else {
                    beschreibungNichtVorhanden += 1
                }
            } else {
                return " "
            }
        }


        if(beschreibungVorhanden > 0 && beschreibungNichtVorhanden == 0){
            return "✓"
        } else if(beschreibungVorhanden == 0 && beschreibungNichtVorhanden > 0 ){
            return "X"
        } else if(beschreibungVorhanden > 0 && beschreibungNichtVorhanden > 0 ){
            return "O"
        } else if(beschreibungVorhanden == 0 && beschreibungNichtVorhanden == 0){
            return "X"
        }
        return "Fehler"
    }


    fun calculateData(): NewModel {
        var unterkonten = getUnterkonto()
        var ausgabenName = getAusgabenName()
        var prozentualeEinteilung = getProzentualeEinteilung()
        var saldoFürDasUnterkonto = getSaldoFürDasUnterkonto()
        var ausgaben = getAusgaben()
        var beschreibungVorhandenOderNicht = getBeschreibungVorhanden()



        var model = NewModel(
            unterkonten, prozentualeEinteilung, ausgaben, saldoFürDasUnterkonto,
            beschreibungVorhandenOderNicht
        )
        return model
    }

    fun getUnterkonto(): ArrayList<String> {
        var arrayList: ArrayList<String> = arrayListOf()
        for (i in unterkonto.readData()) {
            arrayList.add(i.name)


        }
        return arrayList
    }

    fun getProzentualeEinteilung(): ArrayList<String> {
        var arrayList: ArrayList<String> = arrayListOf()
        for (i in unterkonto.readData()) {
            arrayList.add(i.prozent)
        }
        return arrayList
    }



    fun getSaldoFürDasUnterkonto(): ArrayList<String> {
        var arrayList: ArrayList<String> = arrayListOf()
        var ergebnis = 0.0
        for (i in unterkonto.readData()) {
            ergebnis = (getGesamtSaldo() / 100) * i.prozent.toDouble()
            arrayList.add(ergebnis.toString())
        }

        return arrayList
    }

    fun getAusgabenName(): List<String> {
        var setList: MutableSet<String>
        setList = mutableSetOf()
        for (i in ausgaben.readData()) {
            setList.add(i.prozent)
        }

        return setList.toList()
    }

    fun getAusgaben(): ArrayList<String> {
        var arrayList: ArrayList<String> = arrayListOf()
        for (y in unterkonto.readData()) {
            var ergebnis = 0.0
            for (i in ausgaben.readData()) {
                if (i.prozent == y.name) {
                    ergebnis += i.name.toDouble()

                }
            }
            arrayList.add(ergebnis.toString())
        }
        return arrayList
    }

    fun getBeschreibungVorhanden(): ArrayList<String> {
        var arrayList: ArrayList<String> = arrayListOf()
        for (i in unterkonto.readData()) {
            if(i.beschreibung.isNotEmpty()){
                arrayList.add("unterkonto")
            } else {
                arrayList.add("uLeer")
            }
        }
        for (i in ausgaben.readData()) {
            if(i.beschreibung.isNotEmpty()){
                arrayList.add("ausgabe")
            } else {
                arrayList.add("aLeer")

            }
        }


        return arrayList
    }

}

data class CalculateModel(
    val gesamtSaldo: Double, val gesamtAusgaben: Double, val verfugbarerSaldo: Double,
    val unterKontenAnzahl: Double, val prozenteGesamt: Double,val beschreibungVorhandenODerNicht : String
)