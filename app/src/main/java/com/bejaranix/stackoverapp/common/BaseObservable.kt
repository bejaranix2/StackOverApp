package com.bejaranix.stackoverapp.common

import java.util.*

abstract class BaseObservable<ListenerType>{

    private val mListeners = hashSetOf<ListenerType>()

    fun registerListener(listener: ListenerType) = mListeners.add(listener)
    fun unregisterListener(listener: ListenerType) = mListeners.remove(listener)

    protected fun getListeners() = Collections.unmodifiableCollection(mListeners)

}