package com.bejaranix.stackoverapp.screens.common.controller

import androidx.appcompat.app.AppCompatActivity
import com.bejaranix.stackoverapp.common.CustomApplication
import com.bejaranix.stackoverapp.common.dependencyinjection.ActivityCompositionRoot
import com.bejaranix.stackoverapp.common.dependencyinjection.ControllerCompositionRoot

abstract class BaseActivity: AppCompatActivity(){

    private var mControllerCompositionRoot:ControllerCompositionRoot? = null

    private var  mActivityCompositionRoot: ActivityCompositionRoot?=null

    fun getActivityCompositionRoot():ActivityCompositionRoot{
        if(mActivityCompositionRoot==null){
            mActivityCompositionRoot = ActivityCompositionRoot(
                (application as CustomApplication).mCompositionRoot,
                this
            )
        }
        return mActivityCompositionRoot!!
    }

    protected fun getCompositionRoot(): ControllerCompositionRoot {
        if(mControllerCompositionRoot==null){
            mControllerCompositionRoot = ControllerCompositionRoot(getActivityCompositionRoot())
        }
        return mControllerCompositionRoot!!
    }
}