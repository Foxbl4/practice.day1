package com.example.involtaday1.ui.image_or_text


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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

class ListAdapter(private val list: List<TextOrImageModel>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (val model = list[position]) {
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
        val listTextOrImage = list[position]
        if (holder is OnlyImagesViewHolder && listTextOrImage is TextOrImageModel.Image) {
            holder.imageView.setImageResource(listTextOrImage.imageResource)
        } else if (holder is OnlyTextViewHolder && listTextOrImage is TextOrImageModel.Text) {
            holder.txtView.text = listTextOrImage.mText
        }

    }

    override fun getItemCount(): Int = list.size
}