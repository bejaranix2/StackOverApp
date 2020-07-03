package com.bejaranix.stackoverapp.screens.common.views

import java.util.*

abstract class BaseObservableViewMvc<ListenerType>:
    BaseViewMvc(),
    ObservableViewMvc<ListenerType> {

    private val mListeners = hashSetOf<ListenerType>()

    override fun registerListener(listener: ListenerType) = mListeners.add(listener)
    override fun unregisterListener(listener: ListenerType) = mListeners.remove(listener)

    protected fun getListeners(): MutableCollection<ListenerType> = Collections.unmodifiableCollection(mListeners)
}