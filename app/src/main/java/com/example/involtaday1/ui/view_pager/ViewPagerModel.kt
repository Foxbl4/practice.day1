package com.example.involtaday1.ui.view_pager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.involtaday1.R
import kotlinx.android.synthetic.main.item_view_page.view.*

class ViewPagerAdapter(viewPagerFragment: ViewPagerFragment) : RecyclerView.Adapter<PagerVH>() {

     private val mViewPagerFragment = viewPagerFragment
     private val imgArray = listOf(R.drawable.image_1, R.drawable.image_2, R.drawable.image_3,
        R.drawable.image_4, R.drawable.image_5, R.drawable.image_6,R.drawable.image_7,
        R.drawable.image_8, R.drawable.image_9, R.drawable.image_10)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.item_view_page, parent, false))

    override fun getItemCount(): Int = imgArray.size

    override fun onBindViewHolder(holder: PagerVH, position: Int): Unit = holder.itemView.run {
        val listImage: Int = imgArray[position]
        Glide.with(mViewPagerFragment).load(listImage).into(tvImage)
    }

}
class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)