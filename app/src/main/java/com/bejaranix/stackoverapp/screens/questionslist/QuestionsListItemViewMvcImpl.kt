package com.bejaranix.stackoverapp.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bejaranix.stackoverapp.R
import com.bejaranix.stackoverapp.questions.Question

class QuestionsListItemViewMvcImpl : QuestionsListItemViewMvc{
    override val rootView: View
    private val mListeners = arrayListOf<QuestionsListItemViewMvc.Listener>()
    private var mQuestion:Question? = null
    private val mTxtValue:TextView

    constructor(inflater: LayoutInflater, parent:ViewGroup){
        rootView = inflater.inflate(R.layout.layout_question_list_item, parent, false)
        mTxtValue = findViewById(R.id.txt_title)
        rootView.setOnClickListener {
            mListeners.forEach{listener ->
                mQuestion?.let {
                    listener.onQuestionClicked(it)
                }
            }
        }
    }

    private fun <T:View> findViewById(txtTitle: Int): T = rootView.findViewById(txtTitle)

    override fun bindQuestion(question: Question) {
        mQuestion = question
        mTxtValue.text = question.mTitle
    }

    override fun registerListener(listener: QuestionsListItemViewMvc.Listener) =  mListeners.add(listener)

    override fun unregisterListener(listener: QuestionsListItemViewMvc.Listener) = mListeners.remove(listener)
}