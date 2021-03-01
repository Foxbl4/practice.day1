package com.example.involtaday1.ui.parse_json.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.involtaday1.R
import com.example.involtaday1.ui.parse_json.model.RadioStationModel

class RadioStationsListAdapter : RecyclerView.Adapter<RadioStationsListAdapter.RadioViewHolder>() {

    private val itemList = mutableListOf<RadioStationModel>()
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.vh_radio, parent, false)

        return RadioViewHolder(view)
    }

    override fun onBindViewHolder(holderRadio: RadioViewHolder, position: Int) {
        val item = itemList[position]

        holderRadio.apply {
            mParseImgLink.text = item.streamLink
            mParseText.text = item.name

            context?.let { context ->
                item.getImageUri()?.let { uri ->
                        Glide.with(context).load(uri).into(mParseIcon)
                }
            }

            if (item.isSelected) {
                itemView.setBackgroundResource(R.color.grey)
            } else {
                itemView.setBackgroundResource(android.R.color.transparent)
            }

            itemView.setOnClickListener {
                val pos = holderRadio.adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    itemList[pos].isSelected = !itemList[pos].isSelected
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun getItemCount(): Int = itemList.count()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        context = recyclerView.context
    }

    fun initList(list: List<RadioStationModel>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    fun getSelectedList(): List<RadioStationModel> {
        return itemList.filter { it.isSelected }
    }

    fun clearSelection() {
        itemList.forEachIndexed { index, model ->
            if (model.isSelected) {
                model.isSelected = false
                notifyItemChanged(index)
            }
        }
    }

    class RadioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mParseIcon: ImageView = view.findViewById(R.id.parse_icon)
        val mParseText: TextView = view.findViewById(R.id.parse_txt)
        val mParseImgLink: TextView = view.findViewById(R.id.parse_link)
    }
}