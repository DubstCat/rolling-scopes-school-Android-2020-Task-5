package com.example.catapp


import com.example.catapp.data.Cat
import com.example.catapp.data.JSONResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    /* https://api.thecatapi.com/v1/images?api_key=6abe3861-f0dd-4b76-af68-fedcf94621e5 */

    @GET("v1/images/search")
    fun getData(@Query("limit")limit:Int, @Query("api_key")api_key:String="6abe3861-f0dd-4b76-af68-fedcf94621e5"): Call<MutableList<Cat>>

}