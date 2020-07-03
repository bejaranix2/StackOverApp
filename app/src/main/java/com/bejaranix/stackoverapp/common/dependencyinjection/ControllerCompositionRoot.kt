package com.bejaranix.stackoverapp.common.dependencyinjection

import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import com.bejaranix.stackoverapp.questions.FetchLastActiveQuestionsUseCase
import com.bejaranix.stackoverapp.questions.FetchQuestionDetailsUseCase
import com.bejaranix.stackoverapp.screens.common.ViewMvcFactory
import com.bejaranix.stackoverapp.screens.common.controller.BackPressDispatcher
import com.bejaranix.stackoverapp.screens.common.controller.FragmentFrameWrapper
import com.bejaranix.stackoverapp.screens.common.dialogs.DialogEventsBus
import com.bejaranix.stackoverapp.screens.common.dialogs.DialogsManager
import com.bejaranix.stackoverapp.screens.common.fragmentframehelper.FragmentFrameHelper
import com.bejaranix.stackoverapp.screens.common.navdrawer.NavDrawerHelper
import com.bejaranix.stackoverapp.screens.common.permissions.PermissionsHelper
import com.bejaranix.stackoverapp.screens.common.screensnavigator.ScreensNavigator
import com.bejaranix.stackoverapp.screens.common.toastshelper.ToastsHelper
import com.bejaranix.stackoverapp.screens.questionslist.QuestionListController

class ControllerCompositionRoot(private val mActivityCompositionRoot: ActivityCompositionRoot) {

    private fun getStackoverflowApi() = mActivityCompositionRoot.getStackoverflowApi()

    private fun getLayoutInflater(): LayoutInflater = LayoutInflater.from(getActivity())

    fun getViewMvcFactory() = ViewMvcFactory(getLayoutInflater(), getMessagesDisplayer(), getNavDrawerHelper())

    fun getFetchQuestionDetailsUseCase() = FetchQuestionDetailsUseCase(getStackoverflowApi())

    private fun getFetchLastActiveQuestionsUseCase() = FetchLastActiveQuestionsUseCase(getStackoverflowApi())

    fun getQuestionListController() =
        QuestionListController(
            getFetchLastActiveQuestionsUseCase(),
            getScreensNavigator() ,
            getDialogsManager(),
            getDialogsEventBus()
        )

    private fun getNavDrawerHelper()  = getActivity() as NavDrawerHelper

    fun getMessagesDisplayer() = ToastsHelper(getContext())

    fun getScreensNavigator() = ScreensNavigator(getFragmentFragmentHelper())

    private fun getFragmentFragmentHelper() = FragmentFrameHelper(getActivity(),
        getFragmentFragmentWrapper(),
        getFragmentManager())

    private fun getFragmentFragmentWrapper() = getActivity() as FragmentFrameWrapper
    private fun getContext() = mActivityCompositionRoot.getActivity()

    private fun getActivity() = mActivityCompositionRoot.getActivity()

    private fun getFragmentManager() = getActivity().supportFragmentManager

    private fun getBackPressDispatcher() = getActivity() as BackPressDispatcher

    fun getDialogsManager() = DialogsManager(getContext(), getFragmentManager())
    fun getDialogsEventBus() = mActivityCompositionRoot.getDialogsEventBus()
    fun getPermissionsHelper() = mActivityCompositionRoot.getPermissionsHelper()
}


