package uz.anorgroup.mapapplication.data.responce


import com.google.gson.annotations.SerializedName

data class OverviewPolyline(
    @SerializedName("points")
    val points: String
)