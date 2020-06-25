package com.bejaranix.stackoverapp.screens.questionslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bejaranix.stackoverapp.questions.Question

class QuestionListAdapter(context: Context, private val mOnQuestionClickListener: OnQuestionClickListener) :
    ArrayAdapter<Question>(context, 0), QuestionsListItemViewMvc.Listener {

    interface OnQuestionClickListener {
        fun onQuestionClicked(question: Question)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var cView =
            if(convertView !=null) convertView else {
                val viewMvc = QuestionsListItemViewMvcImpl(LayoutInflater.from(parent.context), parent)
                viewMvc.registerListener(this@QuestionListAdapter)
                viewMvc.rootView.tag = viewMvc
                viewMvc.rootView
            }
        getItem(position)?.let{question ->
            // bind the data to views
            val viewMvc = cView.tag as QuestionsListItemViewMvc
            viewMvc.bindQuestion(question)
        }
        return cView
    }

    override fun onQuestionClicked(question: Question) {
        mOnQuestionClickListener.onQuestionClicked(question)
    }

}