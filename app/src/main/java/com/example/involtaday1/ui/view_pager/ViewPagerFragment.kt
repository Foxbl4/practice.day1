package com.example.involtaday1.ui.view_pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.involtaday1.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_view_pager.*


class ViewPagerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_view_pager, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            viewPager2.adapter = ViewPagerAdapter()

        TabLayoutMediator(tlPagination, viewPager2) { _: TabLayout.Tab, _: Int -> }.attach()

        bPrev.setOnClickListener { viewPager2.currentItem -= 1 }
        bNext.setOnClickListener { viewPager2.currentItem += 1 }
    }

}