package com.example.catapp


import com.example.catapp.data.Cat
import retrofit2.Call
import retrofit2.http.GET

interface CatApi {
    /* https://api.thecatapi.com/v1/images?api_key=6abe3861-f0dd-4b76-af68-fedcf94621e5 */

    @GET("api/get")
    fun getData(): Call<MutableList<Cat>>

}