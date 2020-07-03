package com.bejaranix.stackoverapp.screens.common.navdrawer

import android.widget.FrameLayout
import com.bejaranix.stackoverapp.screens.common.views.ObservableViewMvc


interface NavDrawerViewMvc: ObservableViewMvc<NavDrawerViewMvc.Listener> {

    interface Listener{
        fun onQuestionsListClicked()
    }

    fun showDrawer(showDrawer: Boolean)

    fun isDrawerShowed():Boolean

    fun getFragmentFrame(): FrameLayout


}