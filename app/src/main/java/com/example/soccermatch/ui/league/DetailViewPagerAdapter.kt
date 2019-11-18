package com.example.soccermatch.ui.league

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class DetailViewPagerAdapter (fragmentManager: FragmentManager, val leagueId:Int): FragmentPagerAdapter(fragmentManager){

    private val mFragmentList = mutableListOf<Fragment>()
    private val pageTitle = mutableListOf<String>()

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putInt("id",leagueId)
        mFragmentList[position].arguments = bundle
        return mFragmentList[position]
    }

    override fun getCount(): Int = mFragmentList.size

    override fun getPageTitle(position: Int): CharSequence? = pageTitle[position]

    fun addFragment(fragment: Fragment){
        mFragmentList.add(fragment)
    }

    fun addTitle(title:String){
        pageTitle.add(title)
    }

}