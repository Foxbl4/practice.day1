package com.example.involtaday1.ui.view_pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.involtaday1.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_view_pager.*


class ViewPagerFragment : Fragment() {

    private val viewPagerAdapter: ViewPagerAdapter by lazy {
        ViewPagerAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_view_pager, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            viewPager2.adapter = viewPagerAdapter

        TabLayoutMediator(tlPagination, viewPager2) { _: TabLayout.Tab, _: Int -> }.attach()

        bPrev.setOnClickListener { viewPager2.scrollIndefinitelyBack() }
        bNext.setOnClickListener { viewPager2.scrollIndefinitelyNext()}
    }

    private  fun ViewPager2.scrollIndefinitelyNext() {
        val numberOfItems = adapter?.itemCount ?: 0
        val lastIndex = if (numberOfItems > 0) numberOfItems - 1 else 0
        val nextItem = if (currentItem == lastIndex) 0 else currentItem + 1
        setCurrentItem(nextItem, true)
    }

    private  fun ViewPager2.scrollIndefinitelyBack() {
        val nextItem = if (currentItem == 0) 9 else currentItem - 1
        setCurrentItem(nextItem, true)
    }
}