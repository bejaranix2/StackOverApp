package com.bejaranix.stackoverapp.screens.common

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.bejaranix.stackoverapp.screens.common.toastshelper.ToastsHelper
import com.bejaranix.stackoverapp.screens.common.toolbar.ToolbarViewMvc
import com.bejaranix.stackoverapp.screens.questiondetails.QuestionDetailsViewMvc
import com.bejaranix.stackoverapp.screens.questiondetails.QuestionDetailsViewMvcImpl
import com.bejaranix.stackoverapp.screens.questionslist.QuestionListViewMvc
import com.bejaranix.stackoverapp.screens.questionslist.QuestionListViewMvcImpl
import com.bejaranix.stackoverapp.screens.questionslist.questionslistitem.QuestionsListItemViewMvc
import com.bejaranix.stackoverapp.screens.questionslist.questionslistitem.QuestionsListItemViewMvcImpl

class ViewMvcFactory(
    private val mLayoutInflater: LayoutInflater,
    private val mMessagesDisplayer: ToastsHelper
) {

    fun getQuestionsListViewMvc(@Nullable parent:ViewGroup?):QuestionListViewMvc{
        return QuestionListViewMvcImpl(mLayoutInflater,parent, this, mMessagesDisplayer)
    }

    fun getQuestionsListItemViewMvc(@Nullable parent:ViewGroup?): QuestionsListItemViewMvc {
        return QuestionsListItemViewMvcImpl(
            mLayoutInflater,
            parent
        )
    }

    fun getQuestionDetailsViewMvc(@Nullable parent: ViewGroup?): QuestionDetailsViewMvc {
        return QuestionDetailsViewMvcImpl(mLayoutInflater, parent,this,mMessagesDisplayer)
    }

    fun getToolbarViewMvc(@Nullable parent: ViewGroup?): ToolbarViewMvc {
        return ToolbarViewMvc(mLayoutInflater, parent)
    }

}