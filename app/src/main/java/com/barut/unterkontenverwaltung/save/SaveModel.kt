package com.barut.unterkontenverwaltung.save

import com.barut.unterkontenverwaltung.recyclerview.AusgabenModel
import com.barut.unterkontenverwaltung.recyclerview.EDirektModel
import com.barut.unterkontenverwaltung.recyclerview.EinkommenModel
import com.barut.unterkontenverwaltung.recyclerview.UnterkontoModel

data class SaveModel(
    val unterkontoModel: ArrayList<UnterkontoModel>,
    val einkommenModel: ArrayList<EinkommenModel>,
    val ausgabenModel: ArrayList<AusgabenModel>,
    val edirekt : ArrayList<EDirektModel>
)
