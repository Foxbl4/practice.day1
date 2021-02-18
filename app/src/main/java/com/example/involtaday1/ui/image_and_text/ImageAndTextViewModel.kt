package com.example.involtaday1.ui.image_and_text

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.involtaday1.R

data class ImageAndTextModel(var mText: String, var imageResource: Int)

class ImageAndTextViewModel(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.text_and_image_recycler_view, parent, false)) {

    private var mTextView: TextView? = itemView.findViewById(R.id.list_text_in_IAT)
    private var mImageView: ImageView = itemView.findViewById(R.id.list_image_in_IAT)

    fun bind(mImage: ImageAndTextModel, textAndImagesAdapter: ImageAndTextFragment) {
        mTextView?.text = mImage.mText
        Glide.with(textAndImagesAdapter).load(mImage.imageResource).into(mImageView)
    }
}

class ListAdapter(textAndImagesAdapter: ImageAndTextFragment) : RecyclerView.Adapter<ImageAndTextViewModel>() {
    private val mTextAndImagesAdapter = textAndImagesAdapter

    private val imgAndTxtArray = listOf(
        ImageAndTextModel("Кошечка Исъкьюзми", R.drawable.image_1),
        ImageAndTextModel("Ушастик - Глазастик", R.drawable.image_2),
        ImageAndTextModel("Кошка-истеричка", R.drawable.image_3),
        ImageAndTextModel("Приветствие Кошки",R.drawable.image_4),
        ImageAndTextModel("Кошка из общаги", R.drawable.image_5),
        ImageAndTextModel("Кошка проснулась к первой паре", R.drawable.image_6),
        ImageAndTextModel("Кошка хлебушек", R.drawable.image_7),
        ImageAndTextModel("Кошка после 5-ти пар мат. анализа", R.drawable.image_8),
        ImageAndTextModel("Когда не хватило на доширак", R.drawable.image_9),
        ImageAndTextModel("Когда осознал что детство кончилось", R.drawable.image_10)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAndTextViewModel {
        val inflater = LayoutInflater.from(parent.context)
        return ImageAndTextViewModel(inflater, parent)
    }
    override fun onBindViewHolder(holder: ImageAndTextViewModel, position: Int) {
        val listImageAndText: ImageAndTextModel = imgAndTxtArray[position]
        holder.bind(listImageAndText,mTextAndImagesAdapter)
    }
    override fun getItemCount(): Int = imgAndTxtArray.size
}