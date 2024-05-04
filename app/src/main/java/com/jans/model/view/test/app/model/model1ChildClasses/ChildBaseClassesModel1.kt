package com.jans.model.view.test.app.model.model1ChildClasses


import com.google.gson.annotations.SerializedName

data class ChildBaseClassesModel1(
    @SerializedName("singleItem")
    val singleItem: SingleItem? = SingleItem(),
    @SerializedName("status")
    val status: String? = ""
) {
    data class SingleItem(
        @SerializedName("beschreibung")
        val beschreibung: String? = "",
        @SerializedName("bezeichnung")
        val bezeichnung: String? = "",
        @SerializedName("bild")
        val bild: Bild? = Bild(),
        @SerializedName("id")
        val id: Int? = 0,
        @SerializedName("isDetailsWebsite")
        val isDetailsWebsite: Boolean? = false,
        @SerializedName("urlDetails")
        val urlDetails: String? = ""
    ) {
        data class Bild(
            @SerializedName("bezeichnung")
            val bezeichnung: String? = "",
            @SerializedName("id")
            val id: Int? = 0,
            @SerializedName("url")
            val url: Any? = Any()
        )
    }
}