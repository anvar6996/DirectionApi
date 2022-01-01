package uz.anorgroup.mapapplication.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import uz.anorgroup.mapapplication.data.responce.MapResponse

interface MapApi {

    @GET("json")
    suspend fun getDirection(
        @Query("origin", encoded = true) origin: String,
        @Query("destination", encoded = true) destination: String,
        @Query("key", encoded = true) key: String,
    ): Response<MapResponse>

}