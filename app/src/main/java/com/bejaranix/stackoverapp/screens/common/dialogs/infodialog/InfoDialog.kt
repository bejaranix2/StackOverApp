package com.bejaranix.stackoverapp.screens.common.dialogs.infodialog

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.bejaranix.stackoverapp.R
import com.bejaranix.stackoverapp.screens.common.dialogs.BaseDialog

class InfoDialog: BaseDialog() {

    companion object{
        private const val ARG_MESSAGE = "ARG_MESSAGE"
        private const val ARG_TITLE = "ARG_TITLE"
        private const val ARG_BUTTON_CAPTION = "ARG_BUTTON_CAPTION"

        fun newInfoDialog(title:String, message:String, buttonCaption:String): InfoDialog {
            val infoDialog = InfoDialog()
            val args = Bundle(3)
            args.putString(ARG_TITLE,title)
            args.putString(ARG_MESSAGE,message)
            args.putString(ARG_BUTTON_CAPTION,buttonCaption)
            infoDialog.arguments = args
            return infoDialog
        }
    }

    private lateinit var mTxtTitle:TextView
    private lateinit var mTxtMessage:TextView
    private lateinit var mBtnPositive:Button

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_info)
        mTxtTitle = dialog.findViewById(R.id.txt_title)
        mTxtMessage = dialog.findViewById(R.id.txt_message)
        mBtnPositive = dialog.findViewById(R.id.btn_positive)
        arguments?.apply {
            mTxtTitle.text = this.getString(ARG_TITLE)
            mTxtMessage.text = this.getString(ARG_MESSAGE)
            mBtnPositive.text = this.getString(ARG_BUTTON_CAPTION)
        }
        mBtnPositive.setOnClickListener {
            onButtonClicked()
        }
        return dialog
    }

    private fun onButtonClicked() = dismiss()

}