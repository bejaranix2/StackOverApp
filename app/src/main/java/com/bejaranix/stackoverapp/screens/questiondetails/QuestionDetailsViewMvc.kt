package com.bejaranix.stackoverapp.screens.questiondetails

import com.bejaranix.stackoverapp.questions.QuestionDetails
import com.bejaranix.stackoverapp.screens.common.navdrawer.NavDrawerViewMvc
import com.bejaranix.stackoverapp.screens.common.views.ObservableViewMvc

interface QuestionDetailsViewMvc:
    ObservableViewMvc<QuestionDetailsViewMvc.Listener>, NavDrawerViewMvc {

    interface Listener{
        fun onNavigateUpClicked()
        fun onQuestionsListClicked()
    }

    fun bindQuestionDetails(questionDetails:QuestionDetails)

    fun loading(loading:Boolean)

}