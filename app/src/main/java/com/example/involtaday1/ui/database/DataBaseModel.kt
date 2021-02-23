package com.example.involtaday1.ui.database

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.involtaday1.R

data class DataBaseModel(var mText: String, var imageResource: Int)

class ImageAndTextViewModel(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.text_and_image_recycler_view, parent, false)) {

    private var mTextView: TextView? = itemView.findViewById(R.id.list_text_in_IAT)
    private var mImageView: ImageView = itemView.findViewById(R.id.list_image_in_IAT)

    fun bind(mImage: DataBaseModel) {
        mTextView?.text = mImage.mText
        mImageView.setImageResource(mImage.imageResource)
    }
}

class ListAdapter(textAndImagesAdapter: List<DataBaseModel>) : RecyclerView.Adapter<ImageAndTextViewModel>() {
    private val mTextAndImagesAdapter = textAndImagesAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAndTextViewModel {
        val inflater = LayoutInflater.from(parent.context)
        return ImageAndTextViewModel(inflater, parent)
    }
    override fun onBindViewHolder(holder: ImageAndTextViewModel, position: Int) {
        val listImageAndText: DataBaseModel = mTextAndImagesAdapter[position]
        holder.bind(listImageAndText)
    }
    override fun getItemCount(): Int = mTextAndImagesAdapter.size
}