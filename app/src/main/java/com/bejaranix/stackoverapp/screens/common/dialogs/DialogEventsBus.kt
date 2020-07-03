package com.bejaranix.stackoverapp.screens.common.dialogs

import com.bejaranix.stackoverapp.common.BaseObservable

class DialogEventsBus: BaseObservable<DialogEventsBus.Listener>() {
    interface Listener {
        fun onDialogEvent(event: Any)
    }

    fun postEvent(event:Any){
        getListeners().forEach{
            it.onDialogEvent(event)
        }
    }

}