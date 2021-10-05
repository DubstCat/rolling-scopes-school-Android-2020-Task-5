package com.example.catapp


import com.example.catapp.data.Cat
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

const val key = "6abe3861-f0dd-4b76-af68-fedcf94621e5"

interface CatApi {
    /* https://api.thecatapi.com/v1/images?api_key=6abe3861-f0dd-4b76-af68-fedcf94621e5 */


    @Headers("x-api-key:$key")
    @GET("images/search")
    fun getData(
        @Query("limit")limit:Int,
        @Query("page")page:Int = 1,

    ): Call<MutableList<Cat>>

}