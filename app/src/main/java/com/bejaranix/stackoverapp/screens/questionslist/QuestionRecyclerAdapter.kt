package com.bejaranix.stackoverapp.screens.questionslist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bejaranix.stackoverapp.questions.Question
import com.bejaranix.stackoverapp.screens.common.ViewMvcFactory
import com.bejaranix.stackoverapp.screens.questionslist.questionslistitem.QuestionsListItemViewMvc

class QuestionRecyclerAdapter(
    private val mOnQuestionClickListener: Listener,
    private val viewMvcFactory: ViewMvcFactory
) :
    RecyclerView.Adapter<QuestionRecyclerAdapter.MyViewHolder>(), QuestionsListItemViewMvc.Listener {

    interface Listener {
        fun onQuestionClicked(question: Question)
    }

    class MyViewHolder(val mViewMvc: QuestionsListItemViewMvc):RecyclerView.ViewHolder(mViewMvc.rootView)

    private var mQuestions = listOf<Question>()

    override fun onQuestionClicked(question: Question) {
        mOnQuestionClickListener.onQuestionClicked(question)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewMvc = viewMvcFactory.getQuestionsListItemViewMvc(parent)
        viewMvc.registerListener(this)
        return MyViewHolder(viewMvc)
    }

    override fun getItemCount():Int {
        return mQuestions.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)  {
        holder.mViewMvc.bindQuestion(mQuestions[position])
    }

    fun bindQuestions(questions:List<Question>){
        mQuestions = questions
        notifyDataSetChanged()
    }

}