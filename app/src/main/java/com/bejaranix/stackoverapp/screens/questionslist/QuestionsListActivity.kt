package com.bejaranix.stackoverapp.screens.questionslist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bejaranix.stackoverapp.screens.common.controller.BaseActivity


class QuestionsListActivity : BaseActivity() {

    companion object{
        fun startClearTop(context: Context){
            val intent = Intent(context, QuestionsListActivity::class.java)
            intent.flags = intent.flags or  Intent.FLAG_ACTIVITY_CLEAR_TOP
            context.startActivity(intent)
        }
    }

    private lateinit var mQuestionListController: QuestionListController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mQuestionListViewMvc =
            getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(null)
        mQuestionListController = getCompositionRoot().getQuestionListController()
        mQuestionListController.bindView(mQuestionListViewMvc)
        setContentView(mQuestionListViewMvc.rootView)
    }

    override fun onStart() {
        super.onStart()
        mQuestionListController.onStart()
    }

    override fun onStop() {
        super.onStop()
        mQuestionListController.onStop()
    }

    override fun onBackPressed() {
        if(!mQuestionListController.onBackPressed()) super.onBackPressed()
    }
}
