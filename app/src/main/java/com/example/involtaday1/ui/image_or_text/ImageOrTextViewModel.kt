package com.example.involtaday1.ui.image_or_text


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.involtaday1.R


abstract class TextOrImageModel {
    class Text(val mText: String) : TextOrImageModel()
    class Image(val imageResource: Int) : TextOrImageModel()
}

class OnlyImagesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.findViewById(R.id.list_image)
}

class OnlyTextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtView: TextView = view.findViewById(R.id.list_text)
}

class ListAdapter(imageOrTextFragment: ImageOrTextFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val mImageOrTextFragment = imageOrTextFragment
    private val imgOrTxtArray = listOf(
        TextOrImageModel.Text("Кошечка Исъкьюзми"),
        TextOrImageModel.Image(R.drawable.image_1),
        TextOrImageModel.Text("Ушастик - Глазастик"),
        TextOrImageModel.Image(R.drawable.image_2),
        TextOrImageModel.Text("Кошка-истеричка"),
        TextOrImageModel.Image(R.drawable.image_3),
        TextOrImageModel.Text("Приветствие Кошки"),
        TextOrImageModel.Image(R.drawable.image_4),
        TextOrImageModel.Text("Кошка из общаги"),
        TextOrImageModel.Image(R.drawable.image_5),
        TextOrImageModel.Text("Кошка проснулась к первой паре"),
        TextOrImageModel.Image(R.drawable.image_6),
        TextOrImageModel.Text("Кошка хлебушек"),
        TextOrImageModel.Image(R.drawable.image_7),
        TextOrImageModel.Text("Кошка после 5-ти пар мат. анализа"),
        TextOrImageModel.Image(R.drawable.image_8),
        TextOrImageModel.Text("Когда не хватило на доширак"),
        TextOrImageModel.Image(R.drawable.image_9),
        TextOrImageModel.Text("Когда осознал что детство кончилось"),
        TextOrImageModel.Image(R.drawable.image_10)
    )

    override fun getItemViewType(position: Int): Int {
        return when (val model = imgOrTxtArray[position]) {
            is TextOrImageModel.Image -> 0
            is TextOrImageModel.Text -> 1
            else -> throw IllegalArgumentException("Bad model type: $model")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            0 -> {
                val view = inflater.inflate(R.layout.image_recycler_view, parent, false)
                OnlyImagesViewHolder(view)
            }
            1 -> {
                val view = inflater.inflate(R.layout.text_recycler_view, parent, false)
                OnlyTextViewHolder(view)
            }
            else -> throw IllegalArgumentException("Bad view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listTextOrImage = imgOrTxtArray[position]
        if (holder is OnlyImagesViewHolder && listTextOrImage is TextOrImageModel.Image) {
            Glide.with(mImageOrTextFragment).load(listTextOrImage.imageResource).into(holder.imageView)
        } else if (holder is OnlyTextViewHolder && listTextOrImage is TextOrImageModel.Text) {
            holder.txtView.text = listTextOrImage.mText
        }

    }

    override fun getItemCount(): Int = imgOrTxtArray.size
}