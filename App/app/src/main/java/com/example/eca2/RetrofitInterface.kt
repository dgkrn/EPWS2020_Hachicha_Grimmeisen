package com.example.eca2

import retrofit2.Call
import retrofit2.http.GET


interface RetrofitInterface {
    @get:GET("posts")
    val posts : Call<List<PostModel?>?>?

    companion object{
        //     const val BASE_URL = "https://services7.arcgis.com/mOBPykOjAyBO2ZKk/arcgis/rest/services/RKI_Landkreisdaten/FeatureServer/0/query?where=1%3D1&outFields=*&outSR=4326&f=json"
        const val BASE_URL = "https://services7.arcgis.com/mOBPykOjAyBO2ZKk/arcgis/rest/services/RKI_Landkreisdaten/FeatureServer/0/query?where=1%3D1&outFields=*&outSR=4326&f=json"
    }

}