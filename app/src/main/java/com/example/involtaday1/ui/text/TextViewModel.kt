package com.example.involtaday1.ui.text

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.involtaday1.R
import kotlinx.android.synthetic.main.text_recycler_view.view.*


class TextViewModel(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.text_recycler_view, parent, false)) {

    fun bind(mText: String) {
        itemView.list_text.text = mText
    }

}

class ListAdapter(private val list: List<String>) : RecyclerView.Adapter<TextViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewModel {
        val inflater = LayoutInflater.from(parent.context)
        return TextViewModel(inflater, parent)
    }

    override fun onBindViewHolder(holder: TextViewModel, position: Int) {
        val listText: String = list[position]
        holder.bind(listText)
    }

    override fun getItemCount(): Int = list.size
}