package com.bejaranix.stackoverapp.screens.common.controller

interface BackPressDispatcher {
    fun registerListener(listener: BackPressedListener)
    fun unregisterListener(listener: BackPressedListener)
}