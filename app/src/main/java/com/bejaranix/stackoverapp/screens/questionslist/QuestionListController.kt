package com.bejaranix.stackoverapp.screens.questionslist

import com.bejaranix.stackoverapp.R
import com.bejaranix.stackoverapp.questions.FetchLastActiveQuestionsUseCase
import com.bejaranix.stackoverapp.questions.Question
import com.bejaranix.stackoverapp.screens.common.toastshelper.ToastsHelper
import com.bejaranix.stackoverapp.screens.common.screensnavigator.ScreensNavigator

class QuestionListController(
    private val mFetchLastActiveQuestionsUseCase: FetchLastActiveQuestionsUseCase,
    private val mScreensNavigator: ScreensNavigator,
    private val mToastsHelper: ToastsHelper
    ):
    QuestionListViewMvc.Listener, FetchLastActiveQuestionsUseCase.Listener {

    private var mQuestionListViewMvc:QuestionListViewMvc?=null

    fun bindView(questionListViewMvc: QuestionListViewMvc){
        mQuestionListViewMvc = questionListViewMvc
    }

    fun onStart(){
        mQuestionListViewMvc?.registerListener(this)
        mFetchLastActiveQuestionsUseCase.registerListener(this)
        mQuestionListViewMvc?.loading(true)
        mFetchLastActiveQuestionsUseCase.fetchLastActiveQuestionsAndNotify()
    }

    fun onStop(){
        mFetchLastActiveQuestionsUseCase.unregisterListener(this)
    }

    override fun onQuestionClicked(question: Question) {
        mQuestionListViewMvc?.unregisterListener(this)
        mScreensNavigator.toDialogDetails(question.mId)
    }

    override fun onQuestionsListClicked() {
        //THIS IS THE QUESTIONS LIST SCREEN - NO-OP
    }

    override fun onQuestionsFetched(questions: List<Question>) {
        mQuestionListViewMvc?.bindQuestions(questions)
        mQuestionListViewMvc?.loading(false)
    }

    override fun onQuestionsFetchFailed() {
        mQuestionListViewMvc?.loading(false)
        mToastsHelper.showToast(R.string.error_network_call_failed)
    }

    fun onBackPressed():Boolean {
        mQuestionListViewMvc?.apply {
            if(this.showDrawer){
                this.showDrawer = false
                return true
            }
        }
        return false
    }

}