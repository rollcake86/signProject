package com.dreamsecurity.signproject.activity

import android.os.Bundle
import com.android.volley.Request
import com.dreamsecurity.signproject.AppApplication.*
import com.dreamsecurity.signproject.BuildConfig
import com.dreamsecurity.signproject.R
import com.dreamsecurity.signproject.utils.ServerNetworking
import com.rollcake.indi.indiplace.utils.AppkeyManager
import kotlinx.android.synthetic.main.activity_pass_word_search.*
import org.json.JSONObject

class PassWordSearchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_word_search)

        val email : String
        if(BuildConfig.DEBUG){
            email = "ikinye86@gmail.com"
        }else{
            email = AppkeyManager.getKey(this, EMAIL, "")
        }

        if (email.trim().isEmpty()) {
            helpText.text = "현재 회원정보가 없습니다. \n\n 회원 가입을 부탁드립니다"
        }

        mailSendBtn.setOnClickListener {
            val url = "/mail"
            val key = arrayOf("email" , "check")
            val value = arrayOf(email , FORGOT)
            ServerNetworking.sendToMobileServer(this, Request.Method.POST, DOMAIN + url, key, value, object : ServerNetworking.getResult {
                override fun getResultText(text: String?) {
                    val result = JSONObject(text)
                    if(result.getBoolean("key")) {
                        showToast("메일을 보냈습니다. 메일 확인 후 로그인 해 주세요")
                    }else{
                        showToast("현재 서버가 불안정합니다. 추후에 다시 시도해주세요")
                    }
                }

                override fun fail(Error: String?) {
                    showToast(Error)
                }
            })
        }

        backBtn.setOnClickListener {
            finish()
        }

    }
}
