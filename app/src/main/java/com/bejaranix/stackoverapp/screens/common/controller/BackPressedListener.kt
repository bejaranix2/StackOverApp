package com.bejaranix.stackoverapp.screens.common.controller

import com.bejaranix.stackoverapp.screens.common.views.BaseObservableViewMvc

interface BackPressedListener {

    /**
     * @return true if the user handled the back press; false otherwise
     */
    fun onBackPressed():Boolean
}