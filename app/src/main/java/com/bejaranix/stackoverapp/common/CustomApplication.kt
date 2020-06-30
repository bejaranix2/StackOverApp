package com.bejaranix.stackoverapp.common

import android.app.Application
import com.bejaranix.stackoverapp.common.dependencyinjection.CompositionRoot

class CustomApplication: Application() {
    lateinit var mCompositionRoot: CompositionRoot

    override fun onCreate() {
        super.onCreate()
        mCompositionRoot = CompositionRoot()
    }
}