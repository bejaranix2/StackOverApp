package com.bejaranix.stackoverapp.screens.questionslist

import android.view.View
import com.bejaranix.stackoverapp.questions.Question

interface QuestionListViewMvc {
    var rootView: View

    interface Listener{
        fun onQuestionClicked(question: Question)
    }

    fun bindQuestions(questions: MutableList<Question>)
    fun registerListener(listener: Listener): Boolean
    fun unregisterListener(listener: Listener): Boolean
}