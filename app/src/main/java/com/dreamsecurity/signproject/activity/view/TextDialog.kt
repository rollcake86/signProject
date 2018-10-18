package com.dreamsecurity.signproject.activity.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.dreamsecurity.signproject.R
import kotlinx.android.synthetic.main.dialog_text.*

class TextDialog(context: Context, title: String, content: String , result : getReuslt) : Dialog(context) {

    val titleText : String = title
    val contentText : String = content
    val resultInterface : getReuslt = result

    interface getReuslt {
        fun ok()
        fun no()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_text)

        title.text = titleText
        content.text = contentText

        okBtn.setOnClickListener {
            resultInterface.ok()
        }

        cancelBtn.setOnClickListener {
            resultInterface.no()
            dismiss()
        }
    }


}