package com.dreamsecurity.signproject.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.dreamsecurity.signproject.AppApplication.FIRST_START
import com.dreamsecurity.signproject.R
import com.dreamsecurity.signproject.activity.view.PagerAdapter
import com.rollcake.indi.indiplace.utils.AppkeyManager
import kotlinx.android.synthetic.main.activity_help.*

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        val adapter  = PagerAdapter(supportFragmentManager , 3)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object  : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                if(p0 == 2){
                    startBtn.visibility = View.VISIBLE
                }else{
                    startBtn.visibility = View.GONE
                }
            }

        })

        skipBtn.setOnClickListener {
            startActivity(Intent(this@HelpActivity , LoginActivity::class.java))
            AppkeyManager.setKey(this@HelpActivity , FIRST_START , false)
            finish()
        }

        startBtn.setOnClickListener {
            startActivity(Intent(this@HelpActivity , LoginActivity::class.java))
            AppkeyManager.setKey(this@HelpActivity , FIRST_START , false)
            finish()
        }

    }

}
