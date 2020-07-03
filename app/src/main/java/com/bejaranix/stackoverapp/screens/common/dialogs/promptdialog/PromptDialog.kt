package com.bejaranix.stackoverapp.screens.common.dialogs.promptdialog

import android.app.Dialog
import android.os.Bundle
import com.bejaranix.stackoverapp.screens.common.dialogs.BaseDialog
import com.bejaranix.stackoverapp.screens.common.dialogs.DialogEventsBus

class PromptDialog:BaseDialog(), PromptViewMvc.Listener {

    companion object{
        private const val ARG_MESSAGE = "ARG_MESSAGE"
        private const val ARG_TITLE = "ARG_TITLE"
        private const val ARG_POSITIVE_BUTTON_CAPTION = "ARG_POSITIVE_BUTTON_CAPTION"
        private const val ARG_NEGATIVE_BUTTON_CAPTION = "ARG_NEGATIVE_BUTTON_CAPTION"

        fun newPromptDialog(title:String, message:String, positiveButtonCaption:String, negativeButtonCaption:String): PromptDialog {
            val promptDialog = PromptDialog()
            val args = Bundle(4)
            args.putString(ARG_TITLE,title)
            args.putString(ARG_MESSAGE,message)
            args.putString(ARG_POSITIVE_BUTTON_CAPTION,positiveButtonCaption)
            args.putString(ARG_NEGATIVE_BUTTON_CAPTION,negativeButtonCaption)
            promptDialog.arguments = args
            return promptDialog
        }
    }

    private lateinit var mDialogsEventsBus: DialogEventsBus
    private lateinit var mViewMvc: PromptViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDialogsEventsBus = getCompositionRoot().getDialogsEventBus()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        mViewMvc = getCompositionRoot().getViewMvcFactory().getPromptViewMvc(null)
        arguments?.let {
            it.getString(ARG_TITLE)?.let { it1 -> mViewMvc.setTitle(it1) }
            it.getString(ARG_MESSAGE)?.let { it1 -> mViewMvc.setMessage(it1) }
            it.getString(ARG_POSITIVE_BUTTON_CAPTION)?.let { it1 -> mViewMvc.setPositiveButtonCaption(it1) }
            it.getString(ARG_NEGATIVE_BUTTON_CAPTION)?.let { it1 -> mViewMvc.setNegativeButtonCaption(it1) }
        }
        dialog.setContentView(mViewMvc.rootView)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        mViewMvc.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        mViewMvc.unregisterListener(this)
    }

    override fun onPositiveButtonClicked() {
        dismiss()
        mDialogsEventsBus.postEvent(PromptDialogEvent(PromptDialogEvent.Button.POSITIVE))
    }

    override fun onNegativeButtonClicked() {
        dismiss()
        mDialogsEventsBus.postEvent(PromptDialogEvent(PromptDialogEvent.Button.NEGATIVE))
    }

}