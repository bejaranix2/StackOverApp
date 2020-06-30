package com.bejaranix.stackoverapp.screens.common.toastshelper

import android.content.Context
import android.widget.Toast

class ToastsHelper(private val context: Context) {
    fun showToast(resource: Int) {
        Toast.makeText(context, resource, Toast.LENGTH_SHORT).show()
    }


}