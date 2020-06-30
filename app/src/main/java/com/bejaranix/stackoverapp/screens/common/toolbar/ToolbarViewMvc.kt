package com.bejaranix.stackoverapp.screens.common.toolbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.bejaranix.stackoverapp.R
import com.bejaranix.stackoverapp.screens.common.views.BaseViewMvc

class ToolbarViewMvc :BaseViewMvc {

    interface HamburgerListener{
        fun onHamburgerClicked()
    }

    interface NavigateUpListener{
        fun onNavigateUpClicked()
    }

    interface LocationListener{
        fun onLocationClicked()
    }

    private val mTitle:TextView
    private val mButtonBackView: ImageButton
    private val mHamburgerView:ImageButton
    private val mLocationView:ImageButton

    private var mHamburgerListener:HamburgerListener?=null
    private var mNavigateUpListener:NavigateUpListener?=null
    private var mLocationListener:LocationListener?=null


    constructor(inflater: LayoutInflater, parent: ViewGroup?){
        rootView = inflater.inflate(R.layout.layout_toolbar, parent, false)
        mTitle = findViewById(R.id.txt_toolbar_title)
        mButtonBackView = findViewById(R.id.btn_back)
        mHamburgerView = findViewById(R.id.btn_hamburger)
        mLocationView = findViewById(R.id.btn_location)
        setUpViews()
    }

    fun setText(text:String){
        mTitle.text = text
    }

    private fun setUpViews(){
        mButtonBackView.setOnClickListener { mNavigateUpListener?.onNavigateUpClicked() }
        mHamburgerView.setOnClickListener { mHamburgerListener?.onHamburgerClicked() }
        mLocationView.setOnClickListener { mLocationListener?.onLocationClicked() }
    }

    fun enableBackButtonAndListen(navigateUpListener:NavigateUpListener){
        mNavigateUpListener = navigateUpListener
        visible(mHamburgerView,false)
        visible(mButtonBackView,true)
    }

    fun enableHamburgerButtonAndListen(hamburgerListener: HamburgerListener){
        mHamburgerListener = hamburgerListener
        visible(mHamburgerView,true)
        visible(mButtonBackView,false)
    }

    fun enableLocationButtonAndListen(locationListener: LocationListener){
        mLocationListener = locationListener
        visible(mLocationView,true)
    }


    private fun visible(view: View, isVisible: Boolean){
        val visibility = if(isVisible) View.VISIBLE else View.GONE
        view.visibility = visibility
    }

}