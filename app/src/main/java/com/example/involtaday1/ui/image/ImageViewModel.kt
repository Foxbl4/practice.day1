package com.example.involtaday1.ui.image

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.involtaday1.R



class ImageViewModel(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.image_recycler_view, parent, false)) {

  private var mImageView: ImageView = itemView.findViewById(R.id.list_image)

    fun bind(mImage: Int, imageFragment: ImageFragment) {
        Glide.with(imageFragment).load(mImage).into(mImageView)
    }

}

class ListAdapter(imageFragment: ImageFragment) : RecyclerView.Adapter<ImageViewModel>() {

    private val mImageFragment = imageFragment
    private val imgArray = listOf(R.drawable.image_1, R.drawable.image_2, R.drawable.image_3,
        R.drawable.image_4, R.drawable.image_5, R.drawable.image_6,R.drawable.image_7,
        R.drawable.image_8, R.drawable.image_9, R.drawable.image_10)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewModel {
        val inflater = LayoutInflater.from(parent.context)
        return ImageViewModel(inflater, parent)
    }

    override fun onBindViewHolder(holder: ImageViewModel, position: Int) {
        val listImage: Int = imgArray[position]
        holder.bind(listImage, mImageFragment)

    }

    override fun getItemCount(): Int = imgArray.size


}