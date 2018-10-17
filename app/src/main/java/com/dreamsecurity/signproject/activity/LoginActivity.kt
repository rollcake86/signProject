package com.dreamsecurity.signproject.activity

import android.content.Intent
import android.os.Bundle
import com.dreamsecurity.signproject.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        searchPwd.setOnClickListener {
            startActivity(Intent(this@LoginActivity , PassWordSearchActivity::class.java))
        }

        signBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity , SignAtivity::class.java))
        }

    }
}
