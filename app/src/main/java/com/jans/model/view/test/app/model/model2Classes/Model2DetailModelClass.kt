package com.jans.model.view.test.app.model.model2Classes


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Model2DetailModelClass(
    @SerializedName("singleItem")
    @Expose
    var singleItem: SingleItem = SingleItem(),
    @SerializedName("status")
    @Expose
    var status: String = ""
) {
    data class SingleItem(
        @SerializedName("beschreibung")
        @Expose
        var beschreibung: String = "",
        @SerializedName("beschreibung2")
        @Expose
        var beschreibung2: String = "",
        @SerializedName("beschreibung3")
        @Expose
        var beschreibung3: String = "",
        @SerializedName("bezeichnung")
        @Expose
        var bezeichnung: String = "",
        @SerializedName("bild")
        @Expose
        var bild: String = "",
        @SerializedName("email")
        @Expose
        var email: String = "",
        @SerializedName("extra1")
        @Expose
        var extra1: String = "",
        @SerializedName("extra2")
        @Expose
        var extra2: String = "",
        @SerializedName("extra3")
        @Expose
        var extra3: String = "",
        @SerializedName("extra4")
        @Expose
        var extra4: String = "",
        @SerializedName("extra5")
        @Expose
        var extra5: String = "",
        @SerializedName("fax")
        @Expose
        var fax: String = "",
        @SerializedName("homepage")
        @Expose
        var homepage: String = "",
        @SerializedName("id")
        @Expose
        var id: Int = 0,
        @SerializedName("kategorien")
        @Expose
        var kategorien: List<Kategorien> = listOf(),
        @SerializedName("lat")
        @Expose
        var lat: String = "",
        @SerializedName("lng")
        @Expose
        var lng: String = "",
        @SerializedName("mobil")
        @Expose
        var mobil: String = "",
        @SerializedName("ort")
        @Expose
        var ort: String = "",
        @SerializedName("plz")
        @Expose
        var plz: String = "",
        @SerializedName("strasse")
        @Expose
        var strasse: String = "",
        @SerializedName("telefon")
        @Expose
        var telefon: String = "",
        @SerializedName("telefon2")
        @Expose
        var telefon2: String = "",
        @SerializedName("urlDetails")
        @Expose
        var urlDetails: String = ""
    ) {
        data class Kategorien(
            @SerializedName("bezeichnung")
            @Expose
            var bezeichnung: String = "",
            @SerializedName("id")
            @Expose
            var id: Int = 0
        )
    }
}