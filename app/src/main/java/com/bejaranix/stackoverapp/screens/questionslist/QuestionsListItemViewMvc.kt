package com.bejaranix.stackoverapp.screens.questionslist;

import android.view.View
import com.bejaranix.stackoverapp.questions.Question

interface QuestionsListItemViewMvc {
    val rootView: View

    interface Listener{
        fun onQuestionClicked(question: Question)
    }

    fun bindQuestion(question:Question)
    fun registerListener(listener: Listener): Boolean
    fun unregisterListener(listener: Listener): Boolean
}
