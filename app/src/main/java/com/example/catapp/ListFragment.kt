package com.example.catapp


import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.data.Cat
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ListFragment : Fragment(){
    lateinit var adapter:CatAdapter
    lateinit var rvCats:RecyclerView
    lateinit var nsvCats:NestedScrollView
    lateinit var catApi:CatApi
    lateinit var call:Call<MutableList<Cat>>
    lateinit var progressBar: ProgressBar
    var page = 1
    val limit = 20
    var cats:MutableList<Cat> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCats = view.findViewById(R.id.rvCats)
        nsvCats = view.findViewById(R.id.nsvCats)
        progressBar = view.findViewById(R.id.progressBar)
        rvCats.layoutManager = LinearLayoutManager(context)
        adapter = CatAdapter(mutableListOf(),requireContext(), requireActivity())


        getData()

        nsvCats.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v!!.getChildAt(0).measuredHeight - v.measuredHeight){
                progressBar.visibility = View.VISIBLE
                page++
                getData()

            }
        })

    }



    private fun getData(){
        val logger = HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY }

        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(logger)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()

        val retrofit: CatApi by lazy {
            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.thecatapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CatApi::class.java)
        }

        retrofit
            .getData(limit, page)
            .enqueue(object:Callback<MutableList<Cat>>{
            override fun onResponse(call: Call<MutableList<Cat>>, response: Response<MutableList<Cat>>) {
                progressBar.visibility = View.GONE
                Log.d("RETRO",response.body().toString())
                cats.addAll(response.body()!!)
                rvCats.adapter = CatAdapter(cats,requireContext(), requireActivity())
                //adapter.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<MutableList<Cat>>, t: Throwable) {
                Log.d("RETRO","FAILED")
            }
        })

    }
}