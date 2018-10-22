package com.dreamsecurity.signproject.activity

import android.content.Intent
import android.os.Bundle
import com.android.volley.Request
import com.dreamsecurity.signproject.AppApplication
import com.dreamsecurity.signproject.AppApplication.PUSH_TOKEN
import com.dreamsecurity.signproject.R
import com.dreamsecurity.signproject.utils.HashMaker
import com.dreamsecurity.signproject.utils.ServerNetworking
import com.rollcake.indi.indiplace.utils.AppkeyManager
import kotlinx.android.synthetic.main.activity_sign_ativity.*
import org.json.JSONObject

class SignAtivity : BaseActivity() {

    var overlapCheck = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_ativity)

        overlapBtn.setOnClickListener {
            if (idEdit.text.isEmpty()) {
                showToast("빈칸입니다")
            } else {
                val url = "/overlap"
                val key = arrayOf("id")
                val value = arrayOf(idEdit.text.toString())
                ServerNetworking.sendToMobileServer(this, Request.Method.POST, AppApplication.DOMAIN + url, key, value, object : ServerNetworking.getResult {
                    override fun getResultText(text: String?) {
                        val result = JSONObject(text)
                        if (result.getBoolean("key")) {
                            if (result.getInt("msg") == 0) {
                                showToast("확인 되었습니다 체크되었습니다")
                                overlapCheck = true
                                overlapBtn.isEnabled = false
                                idEdit.isEnabled = false
                            } else {
                                showToast("중복된 아이디가 있습니다. 다시 검색해 주세요")
                            }
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

        signBtn.setOnClickListener {
            if (emptyCheck()) {
                showToast("빈칸이 있습니다")
            } else if (passwordCheck()) {
                showToast("비밀번호가 같지 않습니다. 다시 입력해주세요")
            } else if (!overlapCheck) {
                showToast("중복 체크를 하지 않았습니다")
            } else {
                val url = "/signcheck"
                val key = arrayOf("email")
                val value = arrayOf(emailEdit.text.toString())
                ServerNetworking.sendToMobileServer(this, Request.Method.POST, AppApplication.DOMAIN + url, key, value, object : ServerNetworking.getResult {
                    override fun getResultText(text: String?) {
                        val result = JSONObject(text)
                        if (result.getBoolean("key")) {
                            if(result.getInt("msg") == checkEdit.text.toString().toInt()){
                                signUser()
                            }else{
                                showToast("인증 번호가 다릅니다")
                            }

                        }
                    }
                    override fun fail(Error: String?) {
                        showToast(Error)
                    }
                })
            }
        }

        sendEmailBtn.setOnClickListener {
            if (emailEdit.text.isEmpty()) {
                showToast("이메일이 빈칸입니다")
            } else {
                sendEmailBtn.isEnabled = false
                val url = "/auth"
                val key = arrayOf("email", "check")
                val value = arrayOf(emailEdit.text.toString(), AppApplication.SIGN)
                ServerNetworking.sendToMobileServer(this, Request.Method.POST, AppApplication.DOMAIN + url, key, value, object : ServerNetworking.getResult {
                    override fun getResultText(text: String?) {
                        val result = JSONObject(text)
                        if (result.getBoolean("key")) {
                            showToast("메일을 보냈습니다. 메일 확인 후 로그인 해 주세요")
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


    private fun signUser() {
        val url = "/sign"
        val key = arrayOf("id", "password", "email", "birth", "name", "token")
        val value = arrayOf(idEdit.text.toString(),
                HashMaker.SHA256(pwEdit.text.toString()),
                emailEdit.text.toString(),
                birthEdit.text.toString(),
                nameEdit.text.toString(),
                AppkeyManager.getKey(this@SignAtivity, PUSH_TOKEN, "")
        )
        ServerNetworking.sendToMobileServer(this, Request.Method.POST, AppApplication.DOMAIN + url, key, value, object : ServerNetworking.getResult {
            override fun getResultText(text: String?) {
                val result = JSONObject(text)
                if (result.getBoolean("key")) {
                    showToast("회원 가입 완료")
                    finish()
                } else {
                    showToast("서버 인증 실패 다시 시도해 주세요")
                }
            }

            override fun fail(Error: String?) {
                showToast(Error)
            }
        })
    }

    private fun passwordCheck(): Boolean {
        return pwEdit.text.toString() != pwCheckEdit.text.toString()
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
