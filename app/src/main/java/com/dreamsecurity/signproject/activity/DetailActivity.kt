package com.dreamsecurity.signproject.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dreamsecurity.signproject.R
import com.dreamsecurity.signproject.activity.view.TextDialog
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        signSendBtn.setOnClickListener {
            val dialog = TextDialog(this@DetailActivity , "서명 확인" , "서명을 하시겠습니다. 한번 기록된 서명은 취소가 되지 않습니다" , object : TextDialog.getReuslt{
                override fun ok() {
                    showToast("서명이 되었습니다")
                }

                override fun no() {
                    showToast("서명을 취소하였습니다")
                }
            })
            dialog.show()
        }


    }
}
