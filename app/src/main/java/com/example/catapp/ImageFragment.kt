package com.example.catapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.catapp.databinding.FragmentImageBinding

private const val URL_PARAM = "url_param"

class ImageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var url_param: String? = null
    lateinit var binding:FragmentImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url_param = it.getString(URL_PARAM)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param url_param URL parameter.
         * @return A new instance of fragment ImageFragment.
         */
        @JvmStatic
        fun newInstance(url_param: String) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putString(URL_PARAM, url_param)
                }
            }
    }
}