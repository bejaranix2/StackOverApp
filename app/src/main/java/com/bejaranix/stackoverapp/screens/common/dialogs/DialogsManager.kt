package com.bejaranix.stackoverapp.screens.common.dialogs

import android.content.Context
import androidx.annotation.Nullable
import androidx.fragment.app.FragmentManager
import com.bejaranix.stackoverapp.R
import com.bejaranix.stackoverapp.screens.common.dialogs.infodialog.InfoDialog
import com.bejaranix.stackoverapp.screens.common.dialogs.promptdialog.PromptDialog

class DialogsManager(
    private val context: Context,
    private val fragmentManager: FragmentManager
) {

    fun showUseCaseErrorDialog(@Nullable tag:String?){
        val dialogFragment = PromptDialog.newPromptDialog(
            getString(R.string.error_network_call_failed_title),
            getString(R.string.error_network_call_failed_message),
            getString(R.string.error_network_call_failed_positive_button_caption),
            getString(R.string.error_network_call_failed_negative_button_caption)
        )
        dialogFragment.show(fragmentManager, tag)
    }

    fun showPermissionGrantedDialog(tag: String?) {
        val dialogFragment = InfoDialog.newInfoDialog(
            getString(R.string.permission_dialog_title),
            getString(R.string.permission_dialog_granted_message),
            getString(R.string.permission_dialog_button_caption)
        )
        dialogFragment.show(fragmentManager, tag)
    }

    fun showPermissionDeclinedCanAskMoreDialog(tag: String?) {
        val dialogFragment = InfoDialog.newInfoDialog(
            getString(R.string.permission_dialog_title),
            getString(R.string.permission_dialog_cant_ask_more_message),
            getString(R.string.permission_dialog_button_caption)
        )
        dialogFragment.show(fragmentManager, tag)
    }

    fun showDeclinedDialog(tag: String?) {
        val dialogFragment = InfoDialog.newInfoDialog(
            getString(R.string.permission_dialog_title),
            getString(R.string.permission_dialog_permission_declined_message),
            getString(R.string.permission_dialog_button_caption)
        )
        dialogFragment.show(fragmentManager, tag)

    }


    private fun getString(res:Int) =  context.getString(res)

}