package com.dreamsecurity.signproject.activity.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.dreamsecurity.signproject.AppApplication
import com.dreamsecurity.signproject.R
import com.dreamsecurity.signproject.utils.ServerNetworking
import kotlinx.android.synthetic.main.fragment_main_2.*
import org.json.JSONObject
import java.util.*


class SignFragment : BaseFragment(){

    fun newInstance(): SignFragment {
        return SignFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appApplication = context!!.applicationContext as AppApplication
        handler = Handler()

        var list : ArrayList<String> = ArrayList()
        list.add("환경")
        list.add("노동")
        list.add("법률")
        list.add("기타")
        kind.adapter = ArrayAdapter<String>(context ,  android.R.layout.simple_dropdown_item_1line, list)

        signOpenBtn.setOnClickListener {
            val url = "/isnertsign"
            val keys = arrayOf("title", "content" , "author"  , "kind")
            val values = arrayOf(getEditToString(title) , getEditToString(content) , appApplication.id , kind.selectedItemPosition.toString() )

            ServerNetworking.sendToMobileServer(context, Request.Method.POST, AppApplication.DOMAIN + url, keys, values, object : ServerNetworking.getResult {
                override fun getResultText(text: String?) {
                    val result = JSONObject(text)
                    if (result.getBoolean("key")) {
                        showToast("등록되었습니다")
                    } else {
                        showToast("현재 서버가 불안정합니다. 추후에 다시 시도해주세요")
                    }
                }

                override fun fail(Error: String?) {
                    showToast(Error)
                }
            })

        }

    }
}