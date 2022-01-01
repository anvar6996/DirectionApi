package uz.anorgroup.mapapplication.data.responce


import com.google.gson.annotations.SerializedName

data class Polyline(
    @SerializedName("points")
    val points: String
)