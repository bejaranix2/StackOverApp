package com.bejaranix.stackoverapp.screens.questionslist.questionslistitem

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.bejaranix.stackoverapp.R
import com.bejaranix.stackoverapp.questions.Question
import com.bejaranix.stackoverapp.screens.common.views.BaseObservableViewMvc

class QuestionsListItemViewMvcImpl : BaseObservableViewMvc<QuestionsListItemViewMvc.Listener>,
    QuestionsListItemViewMvc {
    private var mQuestion:Question? = null
    private val mTxtValue:TextView

    constructor(inflater: LayoutInflater, parent:ViewGroup?){
        rootView = inflater.inflate(R.layout.layout_question_list_item, parent, false)
        mTxtValue = findViewById(R.id.txt_title)
        rootView.setOnClickListener {
            getListeners().forEach{listener ->
                mQuestion?.let {
                    listener.onQuestionClicked(it)
                }
            }
        }
    }

    override fun bindQuestion(question: Question) {
        mQuestion = question
        mTxtValue.text = question.mTitle
    }
}