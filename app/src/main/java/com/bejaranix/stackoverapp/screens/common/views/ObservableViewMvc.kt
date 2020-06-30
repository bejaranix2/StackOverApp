package com.bejaranix.stackoverapp.screens.common.views

interface ObservableViewMvc<ListenerType>:
    ViewMvc {
    fun registerListener(listener: ListenerType): Boolean
    fun unregisterListener(listener: ListenerType): Boolean
}