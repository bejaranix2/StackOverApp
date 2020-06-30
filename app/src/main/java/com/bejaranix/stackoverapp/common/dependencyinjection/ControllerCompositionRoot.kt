package com.bejaranix.stackoverapp.common.dependencyinjection

import android.app.Activity
import android.view.LayoutInflater
import com.bejaranix.stackoverapp.questions.FetchLastActiveQuestionsUseCase
import com.bejaranix.stackoverapp.questions.FetchQuestionDetailsUseCase
import com.bejaranix.stackoverapp.screens.common.toastshelper.ToastsHelper
import com.bejaranix.stackoverapp.screens.common.screensnavigator.ScreensNavigator
import com.bejaranix.stackoverapp.screens.common.ViewMvcFactory
import com.bejaranix.stackoverapp.screens.questionslist.QuestionListController

class ControllerCompositionRoot(
    private val mCompositionRoot: CompositionRoot, private val mActivity: Activity) {

    private fun getStackoverflowApi() = mCompositionRoot.getStackoverflowApi()

    private fun getLayoutInflater(): LayoutInflater = LayoutInflater.from(mActivity)

    fun getViewMvcFactory() = ViewMvcFactory(getLayoutInflater(), getMessagesDisplayer())

    fun getFetchQuestionDetailsUseCase() = FetchQuestionDetailsUseCase(getStackoverflowApi())

    private fun getFetchLastActiveQuestionsUseCase() = FetchLastActiveQuestionsUseCase(getStackoverflowApi())

    fun getQuestionListController() =
        QuestionListController(
            getFetchLastActiveQuestionsUseCase(),getScreensNavigator() ,getMessagesDisplayer())

    fun getMessagesDisplayer() =
        ToastsHelper(
            getContext()
        )

    fun getScreensNavigator() =
        ScreensNavigator(
            getContext()
        )

    private fun getContext() = mActivity

    private fun getActivity() = mActivity
}


