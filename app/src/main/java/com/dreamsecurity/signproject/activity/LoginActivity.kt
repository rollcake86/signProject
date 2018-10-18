package com.dreamsecurity.signproject.activity

import android.content.Intent
import android.os.Bundle
import com.android.volley.Request
import com.dreamsecurity.signproject.AppApplication.DOMAIN
import com.dreamsecurity.signproject.R
import com.dreamsecurity.signproject.utils.HashMaker
import com.dreamsecurity.signproject.utils.ServerNetworking
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        searchPwd.setOnClickListener {
//            startActivity(Intent(this@LoginActivity, PassWordSearchActivity::class.java))
            startActivity(Intent(this@LoginActivity, MainIndexActivity::class.java))
        }

        signBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignAtivity::class.java))
        }

        loginBtn.setOnClickListener {
            val url = "/login"
            val keys = arrayOf("id" , "password")
            val values = arrayOf( idEdit.text.toString() , HashMaker.SHA256(pwEdit.text.toString()))

            ServerNetworking.sendToMobileServer(this@LoginActivity, Request.Method.POST, DOMAIN + url, keys , values , object : ServerNetworking.getResult{

                override fun getResultText(text: String?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun fail(Error: String?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
        }
    }


}
