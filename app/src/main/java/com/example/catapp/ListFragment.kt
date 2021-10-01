package com.example.catapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catapp.data.Cat
import com.example.catapp.databinding.FragmentListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListFragment : Fragment(){
    lateinit var binding:FragmentListBinding
    lateinit var cats: MutableList<Cat>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.inflate(layoutInflater)
        binding.rvCats.layoutManager = LinearLayoutManager(context)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val catApi = retrofit.create(CatApi::class.java)
        val call = catApi.getData(20)
        callRetrofit(call)

    }

    fun <T> callRetrofit(call:Call<T>){
        call.enqueue(object :Callback<T>{
            override fun onResponse(call: Call<T>, response: Response<T>) {
                cats = response.body() as MutableList<Cat>
                binding.rvCats.adapter = CatAdapter(cats)
            }
            override fun onFailure(call: Call<T>, t: Throwable) {
                Log.d("TAG", "retrofit failed")
            }
        })
    }
}