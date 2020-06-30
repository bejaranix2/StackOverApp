package com.bejaranix.stackoverapp.screens.questionslist

import com.bejaranix.stackoverapp.questions.Question
import com.bejaranix.stackoverapp.screens.common.navdrawer.NavDrawerViewMvc
import com.bejaranix.stackoverapp.screens.common.views.ObservableViewMvc

interface QuestionListViewMvc:
    ObservableViewMvc<QuestionListViewMvc.Listener>, NavDrawerViewMvc {

    interface Listener{
        fun onQuestionClicked(question: Question)
        fun onQuestionsListClicked()
    }
    fun bindQuestions(questions: List<Question>)
    fun loading(loading:Boolean)

}