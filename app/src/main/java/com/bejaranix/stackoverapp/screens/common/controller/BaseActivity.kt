package com.bejaranix.stackoverapp.screens.common.controller

import androidx.appcompat.app.AppCompatActivity
import com.bejaranix.stackoverapp.common.CustomApplication
import com.bejaranix.stackoverapp.common.dependencyinjection.ControllerCompositionRoot

abstract class BaseActivity: AppCompatActivity(){

    private var mControllerCompositionRoot:ControllerCompositionRoot? = null

    protected fun getCompositionRoot(): ControllerCompositionRoot {
        if(mControllerCompositionRoot==null){
            mControllerCompositionRoot = ControllerCompositionRoot(
                (application as CustomApplication).mCompositionRoot,
                this
            )
        }
        return mControllerCompositionRoot!!
    }
}