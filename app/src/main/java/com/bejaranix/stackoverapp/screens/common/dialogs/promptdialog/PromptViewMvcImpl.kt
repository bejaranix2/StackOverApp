package com.bejaranix.stackoverapp.screens.common.dialogs.promptdialog

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.bejaranix.stackoverapp.R
import com.bejaranix.stackoverapp.screens.common.views.BaseObservableViewMvc

class PromptViewMvcImpl: BaseObservableViewMvc<PromptViewMvc.Listener>, PromptViewMvc {

    private val mTxtTitle: TextView
    private val mTxtMessage: TextView
    private val mBtnPositive: Button
    private val mBtnNegative: Button

    constructor(inflater: LayoutInflater, parent: ViewGroup?){
        rootView = inflater.inflate(R.layout.dialog_prompt, parent, false)
        mTxtTitle = findViewById(R.id.txt_title)
        mTxtMessage = findViewById(R.id.txt_message)
        mBtnPositive = findViewById(R.id.btn_positive)
        mBtnNegative = findViewById(R.id.btn_negative)
        mBtnPositive.setOnClickListener {
            onPositiveButtonClicked()
        }
        mBtnNegative.setOnClickListener {
            onNegativeButtonClicked()
        }

    }

    private fun onNegativeButtonClicked() {
        getListeners().forEach{
            it.onNegativeButtonClicked()
        }
    }

    private fun onPositiveButtonClicked() {
        getListeners().forEach{
            it.onPositiveButtonClicked()
        }
    }

    override fun setTitle(title: String) { mTxtTitle.text = title }

    override fun setMessage(message: String) { mTxtMessage.text = message }

    override fun setPositiveButtonCaption(caption: String) { mBtnPositive.text = caption }

    override fun setNegativeButtonCaption(caption: String) { mBtnNegative.text = caption }
}