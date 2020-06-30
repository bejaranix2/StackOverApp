package com.bejaranix.stackoverapp.screens.questionslist.questionslistitem;

import com.bejaranix.stackoverapp.questions.Question
import com.bejaranix.stackoverapp.screens.common.views.ObservableViewMvc

interface QuestionsListItemViewMvc:
    ObservableViewMvc<QuestionsListItemViewMvc.Listener> {

    interface Listener{
        fun onQuestionClicked(question: Question)
    }
    fun bindQuestion(question:Question)
}
