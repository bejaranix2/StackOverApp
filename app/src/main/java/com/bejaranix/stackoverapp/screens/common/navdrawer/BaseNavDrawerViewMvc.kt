package com.bejaranix.stackoverapp.screens.common.navdrawer

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.Nullable
import androidx.drawerlayout.widget.DrawerLayout
import com.bejaranix.stackoverapp.R
import com.bejaranix.stackoverapp.screens.common.views.BaseObservableViewMvc
import com.google.android.material.navigation.NavigationView

abstract class BaseNavDrawerViewMvc<ListenerType>:BaseObservableViewMvc<ListenerType>
, NavDrawerViewMvc, DrawerLayout.DrawerListener {

    private var mNavigationView: NavigationView
    private var mFrameLayout: FrameLayout
    private val mDrawerLayout: DrawerLayout
    private var propertyShowDrawer: Boolean = false

    constructor(inflater: LayoutInflater, @Nullable parent:ViewGroup?){
        super.rootView = inflater.inflate(R.layout.layout_drawer, parent, false)
        mDrawerLayout = findViewById(R.id.drawer_layout)
        mFrameLayout = findViewById(R.id.frame_content)
        mNavigationView = findViewById(R.id.nav_view)
        showDrawer = false
        configureView()
    }

    private fun configureView(){
        mNavigationView.setNavigationItemSelectedListener {
            showDrawer = false
            when(it.itemId){
                R.id.drawer_menu_latest_question -> onDrawerItemClicked(DrawerItems.QUESTIONS_LIST)
            }
            false
        }
        mDrawerLayout.addDrawerListener(this)
    }

    abstract fun onDrawerItemClicked(questionsList: DrawerItems)

    override var showDrawer:Boolean
        get() = propertyShowDrawer
        set(value) {
            when(value){
                true -> mDrawerLayout.openDrawer(Gravity.LEFT)
                false -> mDrawerLayout.closeDrawers()
            }
            propertyShowDrawer = value
        }

    override var rootView: View
        get() = super.rootView
        set(value) = mFrameLayout.addView(value)

    override fun onDrawerOpened(drawerView: View) { propertyShowDrawer = true }

    override fun onDrawerClosed(drawerView: View) { propertyShowDrawer = false }

    override fun onDrawerStateChanged(newState: Int) {
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
    }


}