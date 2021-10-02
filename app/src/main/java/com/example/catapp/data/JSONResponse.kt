package com.example.catapp.data

class JSONResponse {
    private lateinit var cats:Array<Cat>
    public fun getCats():Array<Cat>{return cats}
    public fun setCats(cats:Array<Cat>){this.cats = cats}
}