package com.jans.model.view.test.app.model.model1Classes


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Model1BaseModelClass(
    @SerializedName("items")
    @Expose
    var items: List<Item> = listOf(),
    @SerializedName("status")
    @Expose
    var status: String = ""
) {
    data class Item(
        @SerializedName("beschreibung")
        @Expose
        var beschreibung: String = "",
        @SerializedName("bezeichnung")
        @Expose
        var bezeichnung: String = "",
        @SerializedName("bild")
        @Expose
        var bild: Bild? = null,
        @SerializedName("bilder")
        @Expose
        var bilder: List<Bilder> = listOf(),
        @SerializedName("data")
        @Expose
        var `data`: Data = Data(),
        @SerializedName("dateien")
        @Expose
        var dateien: List<Dateien> = listOf(),
        @SerializedName("icon")
        @Expose
        var icon: Icon = Icon(),
        @SerializedName("id")
        @Expose
        var id: Int = 0,
        @SerializedName("isDetailsWebsite")
        @Expose
        var isDetailsWebsite: Boolean = false,
        @SerializedName("kategorien")
        @Expose
        var kategorien: List<Kategorien> = listOf(),
        @SerializedName("urlDetails")
        @Expose
        var urlDetails: String = "",
        @SerializedName("urlPopUp")
        @Expose
        var urlPopUp: String = ""
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

        data class Bilder(
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

        data class Data(
            @SerializedName("coords")
            @Expose
            var coords: List<Coord> = listOf(),
            @SerializedName("options")
            @Expose
            var options: Options = Options(),
            @SerializedName("type")
            @Expose
            var type: String = ""
        ) {
            data class Coord(
                @SerializedName("lat")
                @Expose
                var lat: Double = 0.0,
                @SerializedName("lng")
                @Expose
                var lng: Double = 0.0
            )

            data class Options(
                @SerializedName("color")
                @Expose
                var color: String = "",
                @SerializedName("fill")
                @Expose
                var fill: Boolean = false,
                @SerializedName("fillOpacity")
                @Expose
                var fillOpacity: Double = 0.0,
                @SerializedName("mapId")
                @Expose
                var mapId: Int? = null,
                @SerializedName("opacity")
                @Expose
                var opacity: Double = 0.0,
                @SerializedName("radius")
                @Expose
                var radius: Double = 0.0,
                @SerializedName("stroke")
                @Expose
                var stroke: Boolean = false,
                @SerializedName("type")
                @Expose
                var type: String? = null,
                @SerializedName("weight")
                @Expose
                var weight: Int = 0
            )
        }

        data class Dateien(
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

        data class Icon(
            @SerializedName("bezeichnung")
            @Expose
            var bezeichnung: String = "",
            @SerializedName("farbe")
            @Expose
            var farbe: String = "",
            @SerializedName("form")
            @Expose
            var form: String = "",
            @SerializedName("icon")
            @Expose
            var icon: String = "",
            @SerializedName("icon_style")
            @Expose
            var iconStyle: String = "",
            @SerializedName("id")
            @Expose
            var id: Int = 0
        )

        data class Kategorien(
            @SerializedName("bezeichnung")
            @Expose
            var bezeichnung: String = "",
            @SerializedName("farbe")
            @Expose
            var farbe: String = "",
            @SerializedName("form")
            @Expose
            var form: String = "",
            @SerializedName("icon")
            @Expose
            var icon: String = "",
            @SerializedName("icon_style")
            @Expose
            var iconStyle: String = "",
            @SerializedName("id")
            @Expose
            var id: Int = 0
        )
    }
}