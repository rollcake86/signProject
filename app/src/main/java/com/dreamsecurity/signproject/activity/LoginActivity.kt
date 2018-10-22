package com.dreamsecurity.signproject.activity

import android.content.Intent
import android.os.Bundle
import com.android.volley.Request
import com.dreamsecurity.signproject.AppApplication.*
import com.dreamsecurity.signproject.R
import com.dreamsecurity.signproject.utils.HashMaker
import com.dreamsecurity.signproject.utils.Logs
import com.dreamsecurity.signproject.utils.ServerNetworking
import com.rollcake.indi.indiplace.utils.AppkeyManager
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    val TAG = LoginActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        pwsave.isChecked = AppkeyManager.getKey(this@LoginActivity, PASSWORD_SAVE, false)
        if(pwsave.isChecked){
            idEdit.setText(AppkeyManager.getKey(this@LoginActivity , ID , ""))
            pwEdit.setText("********")
        }

        searchPwd.setOnClickListener {
            startActivity(Intent(this@LoginActivity, PassWordSearchActivity::class.java))
        }

        signBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignAtivity::class.java))
        }

        pwsave.setOnCheckedChangeListener { buttonView, isChecked ->
            AppkeyManager.setKey(this@LoginActivity, PASSWORD_SAVE, isChecked)
            if(!isChecked){
                AppkeyManager.setKey(this@LoginActivity , ID , "")
                AppkeyManager.setKey(this@LoginActivity , PW , "")
                idEdit.setText("")
                pwEdit.setText("")
            }
        }

        loginBtn.setOnClickListener {
            val url = "/loginuser"
            val keys = arrayOf("id", "password")
            var pwKey = ""

            if (pwsave.isChecked) {
                pwKey = AppkeyManager.getKey(this@LoginActivity, PW, "")
                Logs.e(TAG , pwKey)
                if(pwKey.isEmpty()){
                    pwKey = HashMaker.SHA256(pwEdit.text.toString())
                }
            } else {
                pwKey = HashMaker.SHA256(pwEdit.text.toString())
            }

            val values = arrayOf(getEditToString(idEdit), pwKey)

            ServerNetworking.sendToMobileServer(this@LoginActivity, Request.Method.POST, DOMAIN + url, keys, values, object : ServerNetworking.getResult {
                override fun getResultText(text: String?) {
                    val result = JSONObject(text)
                    if (result.getBoolean("key")) {
                        if (result.getString("msg") == "fail") {
                            showToast("아이디와 비번이 동일하지 않습니다 다시 시도해 주세요")
                        } else {
                            showToast("로그인 되었습니다")
                            appApplication.id = getEditToString(idEdit)
                            if (pwsave.isChecked) {
                                AppkeyManager.setKey(this@LoginActivity, ID, appApplication.id)
                                AppkeyManager.setKey(this@LoginActivity, PW, pwKey)
                            }
                            startActivity(Intent(this@LoginActivity, MainIndexActivity::class.java))
                            finish()
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


}
