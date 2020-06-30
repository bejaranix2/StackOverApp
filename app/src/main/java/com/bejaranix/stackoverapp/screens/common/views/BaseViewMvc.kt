package com.bejaranix.stackoverapp.screens.common.views

import android.content.Context
import android.view.View

abstract class BaseViewMvc:
    ViewMvc {
    override lateinit var rootView: View

    protected fun <T:View> findViewById(txtTitle: Int): T = rootView.findViewById(txtTitle)

    protected fun getContext(): Context = rootView.context

    protected fun getString(res:Int) = getContext().getString(res)

}