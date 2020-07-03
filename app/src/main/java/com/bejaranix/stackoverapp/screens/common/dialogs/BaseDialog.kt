package com.bejaranix.stackoverapp.screens.common.dialogs

import androidx.fragment.app.DialogFragment
import com.bejaranix.stackoverapp.common.dependencyinjection.ControllerCompositionRoot
import com.bejaranix.stackoverapp.screens.common.main.MainActivity

abstract class BaseDialog: DialogFragment() {
    private var mControllerCompositionRoot: ControllerCompositionRoot? = null

    protected fun getCompositionRoot(): ControllerCompositionRoot {
        if(mControllerCompositionRoot==null){
            mControllerCompositionRoot = ControllerCompositionRoot(
                (requireActivity() as MainActivity).getActivityCompositionRoot()
            )
        }
        return mControllerCompositionRoot!!
    }
}