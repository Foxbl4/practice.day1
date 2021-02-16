package com.example.involtaday1.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.involtaday1.R
import kotlinx.android.synthetic.main.fragment_image.*



class ImageFragment : Fragment() {

    private val imgArray = listOf(R.drawable.image_1, R.drawable.image_2, R.drawable.image_3,
        R.drawable.image_4, R.drawable.image_5, R.drawable.image_6,R.drawable.image_7,
        R.drawable.image_8, R.drawable.image_9, R.drawable.image_10)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_image, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        image_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(imgArray)
        }
    }
}