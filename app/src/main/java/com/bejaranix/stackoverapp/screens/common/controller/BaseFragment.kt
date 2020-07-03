package com.bejaranix.stackoverapp.screens.common.controller

import androidx.fragment.app.Fragment
import com.bejaranix.stackoverapp.common.CustomApplication
import com.bejaranix.stackoverapp.common.dependencyinjection.ControllerCompositionRoot
import com.bejaranix.stackoverapp.screens.common.main.MainActivity

abstract class BaseFragment:Fragment() {
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