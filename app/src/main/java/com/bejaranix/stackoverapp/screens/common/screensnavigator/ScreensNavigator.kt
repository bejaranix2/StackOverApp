package com.bejaranix.stackoverapp.screens.common.screensnavigator

import android.content.Context
import com.bejaranix.stackoverapp.screens.questiondetails.QuestionDetailsActivity
import com.bejaranix.stackoverapp.screens.questionslist.QuestionsListActivity

class ScreensNavigator(private val context: Context) {
    fun toDialogDetails(questionId: String) {
        QuestionDetailsActivity.start(context,questionId)
    }

    fun toQuestionsListClearTop() {
        QuestionsListActivity.startClearTop(context)
    }
}