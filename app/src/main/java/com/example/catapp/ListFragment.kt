package com.example.catapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.data.Cat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListFragment : Fragment(){
    lateinit var cats: MutableList<Cat>
    lateinit var rvCats:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val catApi = retrofit.create(CatApi::class.java)
        val call = catApi.getData(20)
        rvCats = view.findViewById(R.id.rvCats)
        rvCats.layoutManager = LinearLayoutManager(context)
        call.enqueue(object:Callback<MutableList<Cat>>{
            override fun onResponse(call: Call<MutableList<Cat>>, response: Response<MutableList<Cat>>) {
                val responseBody = response.body()!!
                cats = responseBody
                val adapter = CatAdapter(cats)
                adapter.context = context!!
                rvCats.adapter = adapter
            }

            override fun onFailure(call: Call<MutableList<Cat>>, t: Throwable) {

            }

        })

    }




}