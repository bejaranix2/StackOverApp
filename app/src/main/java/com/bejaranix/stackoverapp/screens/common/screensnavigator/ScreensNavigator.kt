package com.bejaranix.stackoverapp.screens.common.screensnavigator

import androidx.fragment.app.FragmentManager
import com.bejaranix.stackoverapp.screens.common.controller.FragmentFrameWrapper
import com.bejaranix.stackoverapp.screens.common.fragmentframehelper.FragmentFrameHelper
import com.bejaranix.stackoverapp.screens.questiondetails.QuestionDetailsFragment
import com.bejaranix.stackoverapp.screens.questionslist.QuestionsListFragment

class ScreensNavigator(
    private val mFragmentFrameHelper: FragmentFrameHelper
) {
    fun toQuestionDetails(questionId: String) =
        mFragmentFrameHelper.replaceFragment(QuestionDetailsFragment.newInstance(questionId))

    fun toQuestionsList() =
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(QuestionsListFragment.newInstance())

    fun navigateUp() =
        mFragmentFrameHelper.navigateUp()
}