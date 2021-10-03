package com.example.catapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.widget.NestedScrollView
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
    lateinit var adapter:CatAdapter
    lateinit var rvCats:RecyclerView
    lateinit var nsvCats:NestedScrollView
    lateinit var catApi:CatApi
    lateinit var call:Call<MutableList<Cat>>
    lateinit var progressBar: ProgressBar
    var page = 1
    val limit = 20
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

        refreshList()

        nsvCats.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v!!.getChildAt(0).measuredHeight - v.measuredHeight){
                progressBar.visibility = View.VISIBLE
                appendList()
            }
        })
    }

    fun appendList(){
        adapter = CatAdapter(adapter.cats.apply {addAll(getData())})
        adapter.context = requireContext()
        rvCats.adapter = adapter
    }

    fun refreshList(){
        adapter = CatAdapter(getData())
        adapter.context = requireContext()
        rvCats.adapter = adapter
    }

    fun getData():MutableList<Cat>{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        catApi = retrofit.create(CatApi::class.java)
        call = catApi.getData(limit)
        var cats:MutableList<Cat>?=null
        call.enqueue(object:Callback<MutableList<Cat>>{
            override fun onResponse(call: Call<MutableList<Cat>>, response: Response<MutableList<Cat>>) {
                progressBar.visibility = View.GONE
                cats = response.body()
            }
            override fun onFailure(call: Call<MutableList<Cat>>, t: Throwable) {
                // pass
            }
        })
        return cats!!
    }
}