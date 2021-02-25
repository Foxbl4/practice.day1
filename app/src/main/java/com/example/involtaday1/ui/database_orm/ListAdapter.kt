package com.example.involtaday1.ui.database_orm

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.involtaday1.R
import kotlinx.android.synthetic.main.text_and_image_recycler_view.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var userList = emptyList<User>()

    private val justImg = listOf(
        R.drawable.image_1, R.drawable.image_2, R.drawable.image_3,
        R.drawable.image_4, R.drawable.image_5, R.drawable.image_6, R.drawable.image_7,
        R.drawable.image_8, R.drawable.image_9, R.drawable.image_10
    )

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).
        inflate(R.layout.text_and_image_recycler_view, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.list_text_in_IAT.text = "${currentItem.id}. ${currentItem.values}"
        holder.itemView.list_image_in_IAT.setImageResource(justImg.random())
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

}