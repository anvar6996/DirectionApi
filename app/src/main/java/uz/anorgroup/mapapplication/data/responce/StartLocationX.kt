package uz.anorgroup.mapapplication.data.responce


import com.google.gson.annotations.SerializedName

data class StartLocationX(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)