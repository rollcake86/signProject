package com.dreamsecurity.signproject.activity.view

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.dreamsecurity.signproject.R


class SignFragment : Fragment(){

    fun newInstance(): SignFragment {
        return SignFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_2, container, false) // 여기서 UI를 생성해서 View를 return
    }
}