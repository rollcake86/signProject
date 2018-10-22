package com.dreamsecurity.signproject.activity.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.dreamsecurity.signproject.AppApplication
import com.dreamsecurity.signproject.R
import com.dreamsecurity.signproject.utils.Logs
import com.dreamsecurity.signproject.utils.ServerNetworking
import kotlinx.android.synthetic.main.fragment_main_1.*
import org.json.JSONObject


class MainListFragment : BaseFragment() {

    val TAG = MainListFragment::class.java.simpleName

    fun newInstance(): MainListFragment {
        return MainListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var list: ArrayList<String> = ArrayList()
        list.add("환경")
        list.add("노동")
        list.add("법률")
        list.add("기타")
        kind.adapter = ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, list)


        signList.setHasFixedSize(true)
        signList.layoutManager = GridLayoutManager(context, 2)


        loadingList()

    }

    private fun loadingList() {
        val url = "/selectsign"
        ServerNetworking.sendToMobileServerWithLoading(context, loginProgress, Request.Method.POST, AppApplication.DOMAIN + url, null, null, object : ServerNetworking.getResult {
            override fun getResultText(text: String?) {
                val result = JSONObject(text)
                if (result.getBoolean("key")) {
                    val array  = result.getJSONArray("msg")
                    val listdata = ArrayList<SignItem>()
                    for(i in 0 until array.length()){
                        listdata.add(SignItem(JSONObject(array.get(i).toString()).getString("title"),
                                JSONObject(array.get(i).toString()).getString("content"),
                                JSONObject(array.get(i).toString()).getInt("kind"),
                                JSONObject(array.get(i).toString()).getString("signcount"),
                                JSONObject(array.get(i).toString()).getString("author"),
                                JSONObject(array.get(i).toString()).getString("signture")))
                    }

                    signList.adapter = SignAdapter(listdata, object : SignAdapter.onClickListener {
                        override fun click(id: String) {
                            showToast(id)
                        }

                    })
                } else {

                }
            }

            override fun fail(Error: String?) {
                showToast(Error)
            }
        })

    }

}