package com.example.involtaday1.ui.image_or_text

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.involtaday1.R
import kotlinx.android.synthetic.main.fragment_text_or_image.*


class ImageOrTextFragment : Fragment() {

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
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_text_or_image, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_or_image_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(imgOrTxtArray)
        }
    }
}