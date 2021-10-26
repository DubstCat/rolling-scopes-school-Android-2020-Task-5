package com.example.catapp.data

class JSONResponse {
    private lateinit var cats:MutableList<Cat>
    public fun getCats():MutableList<Cat>{return cats}
    public fun setCats(cats:MutableList<Cat>){this.cats = cats}
}