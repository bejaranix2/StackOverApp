package com.bejaranix.stackoverapp.screens.questionslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.bejaranix.stackoverapp.R
import com.bejaranix.stackoverapp.questions.Question

class QuestionListViewMvcImpl : QuestionListAdapter.OnQuestionClickListener, QuestionListViewMvc {

    private var mQuestionsListAdapter: QuestionListAdapter
    private var mListQuestions: ListView
    override var rootView: View
    private val mListeners = arrayListOf<QuestionListViewMvc.Listener>()

    constructor(inflater: LayoutInflater, parent: ViewGroup?){
        rootView = inflater.inflate(R.layout.layout_questions_list, parent, false)
        mListQuestions = findViewById(R.id.lst_questions)
        mQuestionsListAdapter = QuestionListAdapter(getContext(), this)
        mListQuestions.adapter = mQuestionsListAdapter
    }

    private fun getContext(): Context = rootView.context

    private fun <T:View> findViewById(listQuestions: Int):T = rootView.findViewById(listQuestions)

    override fun onQuestionClicked(question: Question) {
        mListeners.forEach {
            it.onQuestionClicked(question)
        }
    }

    override fun bindQuestions(questions: MutableList<Question>) {
        mQuestionsListAdapter.clear()
        mQuestionsListAdapter.addAll(questions)
        mQuestionsListAdapter.notifyDataSetChanged()
    }

    override fun registerListener(listener: QuestionListViewMvc.Listener) = mListeners.add(listener)

    override fun unregisterListener(listener: QuestionListViewMvc.Listener) = mListeners.remove(listener)
}