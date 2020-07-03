package com.bejaranix.stackoverapp.screens.common.main

import android.os.Bundle
import android.widget.FrameLayout
import com.bejaranix.stackoverapp.screens.common.controller.BackPressDispatcher
import com.bejaranix.stackoverapp.screens.common.controller.BackPressedListener
import com.bejaranix.stackoverapp.screens.common.controller.BaseActivity
import com.bejaranix.stackoverapp.screens.common.controller.FragmentFrameWrapper
import com.bejaranix.stackoverapp.screens.common.navdrawer.NavDrawerHelper
import com.bejaranix.stackoverapp.screens.common.navdrawer.NavDrawerViewMvc
import com.bejaranix.stackoverapp.screens.common.permissions.PermissionsHelper
import com.bejaranix.stackoverapp.screens.common.screensnavigator.ScreensNavigator


class MainActivity : BaseActivity(), BackPressDispatcher, FragmentFrameWrapper,
    NavDrawerViewMvc.Listener,NavDrawerHelper {

    private val mBackPressedListeners:HashSet<BackPressedListener> = hashSetOf()
    private lateinit var mScreensNavigator: ScreensNavigator
    private lateinit var mNavDrawerViewMvc: NavDrawerViewMvc
    private lateinit var mPermissionsHelper: PermissionsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mScreensNavigator = getCompositionRoot().getScreensNavigator()
        mNavDrawerViewMvc = getCompositionRoot().getViewMvcFactory().getNavDrawerViewMvc(null)
        mPermissionsHelper = getCompositionRoot().getPermissionsHelper()
        setContentView(mNavDrawerViewMvc.rootView)
        if (savedInstanceState == null){
            mScreensNavigator.toQuestionsList()
        }
    }

    override fun onStart() {
        super.onStart()
        mNavDrawerViewMvc.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        mNavDrawerViewMvc.unregisterListener(this)
    }

    override fun onBackPressed(){
        var isBackPressConsumed = false
        mBackPressedListeners.forEach{
            if(it.onBackPressed()) isBackPressConsumed = true
        }
        if(!isBackPressConsumed) super.onBackPressed()
    }

    override fun registerListener(listener: BackPressedListener) {
        mBackPressedListeners.add(listener)
    }

    override fun unregisterListener(listener: BackPressedListener) {
        mBackPressedListeners.remove(listener)
    }

    override fun getFragmentFrame(): FrameLayout = mNavDrawerViewMvc.getFragmentFrame()

    override fun onQuestionsListClicked() = mScreensNavigator.toQuestionsList()

    override fun showDrawer(showDrawer: Boolean) = mNavDrawerViewMvc.showDrawer(showDrawer)

    override fun isDrawerShowed() = mNavDrawerViewMvc.isDrawerShowed()

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        mPermissionsHelper.onRequestPermissionsResult(requestCode,permissions,grantResults)
    }
}
