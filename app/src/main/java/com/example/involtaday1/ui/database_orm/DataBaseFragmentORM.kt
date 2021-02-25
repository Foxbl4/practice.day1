package com.example.involtaday1.ui.database_orm


import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.involtaday1.R
import kotlinx.android.synthetic.main.fragment_database.*
import kotlinx.android.synthetic.main.fragment_database.view.*
import kotlinx.android.synthetic.main.fragment_text_and_image.view.*
import kotlinx.android.synthetic.main.text_and_image_recycler_view.view.*
import org.jetbrains.anko.support.v4.toast


class DataBaseFragmentORM : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_database, container, false)

        val adapter = ListAdapter()

        val recyclerView = view.database_recycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        view.btn_db_insert.setOnClickListener {
            insertDataToDataBase()
        }

        view.btn_db_delete.setOnClickListener {
            deleteUser()
        }

        view.btn_bd_search.setOnClickListener{
            if (et_bd_search.text.toString().isNotEmpty()) {
                val testList: MutableList<User> = mutableListOf()
                val mValue = et_bd_search.text.toString().substringAfter(".")
                val id: Int = et_bd_search.text.toString().substringBefore(".").toInt()
                testList.add(User(id, mValue))
                mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {
                    adapter.setData(testList)
                })
            } else{
                mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
                mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
                    adapter.setData(user)
                })
            }
        }

        return view
    }

    private fun deleteUser() {
        val mValue = et_bd_val.text.toString().substringAfter(".")
        val id : Int = et_bd_val.text.toString().substringBefore(".").toInt()
        if (inputCheck(mValue)) {
            val user = User(id, mValue)
            mUserViewModel.deleteUser(user)
            toast("Successfully removed")
        }
    }

    private fun insertDataToDataBase() {
        val value = et_bd_val.text.toString()

        if (inputCheck(value)){
            val user = User(0,value)
            mUserViewModel.addUser(user)
            toast("Successfully added!")

        }
    }

    private fun inputCheck(value: String):Boolean{
        return !(TextUtils.isEmpty(value))
    }

}