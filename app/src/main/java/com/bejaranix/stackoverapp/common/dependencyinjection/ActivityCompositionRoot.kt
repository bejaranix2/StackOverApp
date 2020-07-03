package com.bejaranix.stackoverapp.common.dependencyinjection;

import androidx.fragment.app.FragmentActivity
import com.bejaranix.stackoverapp.screens.common.permissions.PermissionsHelper

class ActivityCompositionRoot(
        private val mCompositionRoot: CompositionRoot,
        private val mActivity: FragmentActivity
) {
        private var mPermissionsHelper: PermissionsHelper?=null

        fun getActivity() = mActivity
        fun getStackoverflowApi() = mCompositionRoot.getStackoverflowApi()
        fun getDialogsEventBus() = mCompositionRoot.getDialogsEventBus()
        fun getPermissionsHelper():PermissionsHelper{
                if(mPermissionsHelper==null){
                        mPermissionsHelper = PermissionsHelper(getActivity())
                }
                return mPermissionsHelper!!
        }
}
