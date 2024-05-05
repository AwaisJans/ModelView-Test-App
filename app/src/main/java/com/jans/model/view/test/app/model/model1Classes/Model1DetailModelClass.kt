package com.jans.model.view.test.app.model.model1Classes


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Model1DetailModelClass(
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
        @SerializedName("bezeichnung")
        @Expose
        var bezeichnung: String = "",
        @SerializedName("bild")
        @Expose
        var bild: Bild = Bild(),
        @SerializedName("id")
        @Expose
        var id: Int = 0,
        @SerializedName("isDetailsWebsite")
        @Expose
        var isDetailsWebsite: Boolean = false,
        @SerializedName("urlDetails")
        @Expose
        var urlDetails: String = ""
    ) {
        data class Bild(
            @SerializedName("bezeichnung")
            @Expose
            var bezeichnung: String = "",
            @SerializedName("id")
            @Expose
            var id: Int = 0,
            @SerializedName("url")
            @Expose
            var url: String = ""
        )
    }
}