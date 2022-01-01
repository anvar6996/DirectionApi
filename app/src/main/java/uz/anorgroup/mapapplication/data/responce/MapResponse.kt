package uz.anorgroup.mapapplication.data.responce


import com.google.gson.annotations.SerializedName

data class MapResponse(
    @SerializedName("geocoded_waypoints")
    val geocodedWaypoints: List<GeocodedWaypoint>,
    @SerializedName("routes")
    val routes: List<Route>,
    @SerializedName("status")
    val status: String
)