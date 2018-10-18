package com.dreamsecurity.signproject.activity.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter


class PagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    var mNumberOfTabs : Int = 0

    constructor(fm : FragmentManager , numOfTabs : Int) : this(fm){
        mNumberOfTabs = numOfTabs
    }

    override fun getItem(p0: Int): Fragment? {

        when (p0) {
            0 -> {
                return TabFragment()
            }
            1 -> {
                return TabFragment()
            }
            2 -> {
                return TabFragment()
            }
            else -> return TabFragment()
        }
    }

    override fun getCount(): Int {
        return mNumberOfTabs
    }

}