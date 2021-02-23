package com.example.involtaday1.ui.database

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.involtaday1.R
import kotlinx.android.synthetic.main.fragment_database.*
import org.jetbrains.anko.support.v4.toast


class DataBaseFragment : Fragment() {

    private var dbHandler: DatabaseHandler? = null


    private var imgAndTxtArray: MutableList<DataBaseModel> = mutableListOf()
    private val justImg = listOf(
        R.drawable.image_1, R.drawable.image_2, R.drawable.image_3,
        R.drawable.image_4, R.drawable.image_5, R.drawable.image_6, R.drawable.image_7,
        R.drawable.image_8, R.drawable.image_9, R.drawable.image_10
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dbHandler = DatabaseHandler(requireActivity())
        return inflater.inflate(R.layout.fragment_database, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_db_insert.setOnClickListener { addRVText() }
        btn_db_delete.setOnClickListener { delRVText() }
        btn_bd_search.setOnClickListener {
            if (et_bd_search.length() == 0 ){
                getAll()
            } else if (et_bd_search.length() > 0 ){
                findNotAll()
            }

            database_recycler.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = ListAdapter(imgAndTxtArray)
            }
        }
    }

    private fun validation(): Boolean {
        var validate = false
        if (et_bd_val.text.toString() != "") {
            validate = true
        } else {
            validate = false
            toast("Изменений в базе данных не производилось!")
        }
        return validate
    }

    private fun addRVText() {

            if (validation()) {
                val user = DBU()
                var success = false
                user.values = et_bd_val.text.toString()
                success = dbHandler!!.addValue(user)

                if (success) {
                    toast("База данных была построена с введёным элементом!")
                }
                val textList =  dbHandler!!.getAllColumns()
                imgAndTxtArray.add(DataBaseModel(textList[textList.size-1], justImg.random()))
            }
    }

    private fun delRVText() {

        if (validation()) {
            dbHandler!!.delete(et_bd_val.text.toString())
            imgAndTxtArray = mutableListOf()
            getAll()
        }
    }

    private fun getAll() {
            val textList =  dbHandler!!.getAllColumns()
            for (i in textList.indices){
            imgAndTxtArray.add(DataBaseModel(textList[i], justImg.random()))
            }
    }

    private fun findNotAll(){
        val findValue = dbHandler!!.find(et_bd_search.text.toString())
        imgAndTxtArray = mutableListOf()
        imgAndTxtArray.add(DataBaseModel(findValue,justImg.random()))
    }
}