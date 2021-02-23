package com.example.involtaday1.ui.database

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.involtaday1.R
import kotlinx.android.synthetic.main.fragment_database.*


class DataBaseFragment : Fragment() {

    private var dbHandler: DatabaseHandler? = null
    private val textAndImagesAdapter: ListAdapter by lazy {
        ListAdapter(this)
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        dbHandler = DatabaseHandler(requireActivity())
       return inflater.inflate(R.layout.fragment_database, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database_recycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = textAndImagesAdapter
        }
    }
}