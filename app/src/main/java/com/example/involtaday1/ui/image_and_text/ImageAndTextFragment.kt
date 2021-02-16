package com.example.involtaday1.ui.image_and_text

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.involtaday1.R
import kotlinx.android.synthetic.main.fragment_text_and_image.*


class ImageAndTextFragment : Fragment() {

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
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_text_and_image, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_and_image_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(imgAndTxtArray)
        }
    }
}