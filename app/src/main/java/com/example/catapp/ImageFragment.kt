package com.example.catapp

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.catapp.databinding.FragmentImageBinding

private const val IMAGE_PARAM = "url_param"

class ImageFragment : Fragment() {

    private var image_param: ByteArray? = null
    lateinit var binding:FragmentImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            image_param = it.getByteArray(IMAGE_PARAM)

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
        binding.imageFullscreen.setImageBitmap(BitmapFactory.decodeByteArray(image_param, 0, image_param!!.size))
        binding.btnDownload.setOnClickListener{
            // Это код для скачивания файла

            /*val filename = url.substringAfterLast("/")
            val request = DownloadManager.Request(Uri.parse(url))
                .setTitle(filename)
                .setDestinationInExternalFilesDir(context, context.getString(R.string.app_name), filename)
            (context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager)
                .enqueue(request)*/
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param image_param ByteArray parameter.
         * @return A new instance of fragment ImageFragment.
         */
        @JvmStatic
        fun newInstance(imageByteArray: ByteArray) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putByteArray(IMAGE_PARAM, imageByteArray)
                }
            }
    }
}