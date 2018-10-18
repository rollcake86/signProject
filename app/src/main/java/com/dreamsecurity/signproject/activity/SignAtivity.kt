package com.dreamsecurity.signproject.activity

import android.os.Bundle
import com.android.volley.Request
import com.dreamsecurity.signproject.AppApplication
import com.dreamsecurity.signproject.R
import com.dreamsecurity.signproject.utils.ServerNetworking
import kotlinx.android.synthetic.main.activity_sign_ativity.*
import org.json.JSONObject

class SignAtivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_ativity)

        signBtn.setOnClickListener {
            if (emptyCheck()) {
                showToast("빈칸이 있습니다")
            } else{

            }
        }

        sendEmailBtn.setOnClickListener {
            if (emailEdit.text.isEmpty()) {
                showToast("이메일이 빈칸입니다")
            } else {
                val url = "/mail"
                val key = arrayOf("email" , "check")
                val value = arrayOf(emailEdit.text.toString() , AppApplication.SIGN)
                ServerNetworking.sendToMobileServer(this, Request.Method.POST, AppApplication.DOMAIN + url, key, value, object : ServerNetworking.getResult {
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
        }


    }

    private fun emptyCheck(): Boolean {
        if (idEdit.text.isEmpty()) {
            return true
        }
        if (pwEdit.text.isEmpty()) {
            return true
        }
        if (pwCheckEdit.text.isEmpty()) {
            return true
        }
        if (emailEdit.text.isEmpty()) {
            return true
        }
        if (nameEdit.text.isEmpty()) {
            return true
        }
        if (birthEdit.text.isEmpty()) {
            return true
        }
        return false
    }


}
