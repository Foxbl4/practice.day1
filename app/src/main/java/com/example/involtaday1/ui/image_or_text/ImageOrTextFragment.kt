package com.example.involtaday1.ui.image_or_text

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.involtaday1.R
import kotlinx.android.synthetic.main.fragment_text_or_image.*


class ImageOrTextFragment : Fragment() {

    private val imageOrTextAdapter: ListAdapter by lazy {
        ListAdapter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_text_or_image, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_or_image_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = imageOrTextAdapter
        }
    }
}