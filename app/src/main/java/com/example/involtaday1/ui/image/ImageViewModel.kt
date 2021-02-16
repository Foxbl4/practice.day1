package com.example.involtaday1.ui.image

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.involtaday1.R


class ImageViewModel(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.image_recycler_view, parent, false)) {

    private var mImageView : ImageView = itemView.findViewById(R.id.list_image)

    fun bind(mImage: Int) {
        this.mImageView.setImageResource(mImage)
    }

}

class ListAdapter(private val list: List<Int>) : RecyclerView.Adapter<ImageViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewModel {
        val inflater = LayoutInflater.from(parent.context)
        return ImageViewModel(inflater, parent)
    }

    override fun onBindViewHolder(holder: ImageViewModel, position: Int) {
        val listImage: Int = list[position]
        holder.bind(listImage)
    }

    override fun getItemCount(): Int = list.size


}