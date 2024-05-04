package com.jans.model.view.test.app.model.model1BaseModelClasses

data class Item(
    val beschreibung: String,
    val bezeichnung: String,
    val bild: Bild,
    val bilder: List<Bilder>,
    val `data`: Data,
    val dateien: List<Dateien>,
    val icon: Icon,
    val id: Int,
    val isDetailsWebsite: Boolean,
    val kategorien: List<Kategorien>,
    val urlDetails: String,
    val urlPopUp: String
)