package com.bejaranix.stackoverapp.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bejaranix.stackoverapp.R
import com.bejaranix.stackoverapp.questions.FetchQuestionDetailsUseCase
import com.bejaranix.stackoverapp.questions.QuestionDetails
import com.bejaranix.stackoverapp.screens.common.controller.BaseActivity
import com.bejaranix.stackoverapp.screens.common.screensnavigator.ScreensNavigator
import com.bejaranix.stackoverapp.screens.common.toastshelper.ToastsHelper

class QuestionDetailsActivity : BaseActivity(), QuestionDetailsViewMvc.Listener,
    FetchQuestionDetailsUseCase.Listener {

    private lateinit var mQuestionDetailsViewMvc: QuestionDetailsViewMvc
    private lateinit var mFetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase
    private lateinit var mToastsHelper: ToastsHelper
    private lateinit var mScreensNavigator: ScreensNavigator

    companion object{
        private const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"

        fun start(context: Context, questionId:String){
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mQuestionDetailsViewMvc = getCompositionRoot()
            .getViewMvcFactory().getQuestionDetailsViewMvc(null)
        mFetchQuestionDetailsUseCase = getCompositionRoot().getFetchQuestionDetailsUseCase()
        mToastsHelper = getCompositionRoot().getMessagesDisplayer()
        mScreensNavigator = getCompositionRoot().getScreensNavigator()
        setContentView(mQuestionDetailsViewMvc.rootView)
    }

    override fun onStart() {
        super.onStart()
        val questionId = intent.getStringExtra(EXTRA_QUESTION_ID)
        mFetchQuestionDetailsUseCase.registerListener(this)
        mQuestionDetailsViewMvc.registerListener(this)
        fetchDetails(questionId)
    }

    override fun onStop() {
        super.onStop()
        mFetchQuestionDetailsUseCase.unregisterListener(this)
        mQuestionDetailsViewMvc.unregisterListener(this)

    }

    private fun fetchDetails(questionId: String) {
        mQuestionDetailsViewMvc.loading(true)
        mFetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(questionId)
    }

    override fun onQuestionDetailFetched(questionDetails: QuestionDetails) {
        mQuestionDetailsViewMvc.bindQuestionDetails(questionDetails)
        mQuestionDetailsViewMvc.loading(false)
    }

    override fun onQuestionDetailsFetchFailed() {
        mQuestionDetailsViewMvc.loading(false)
        mToastsHelper.showToast(R.string.error_network_call_failed)
    }

    override fun onNavigateUpClicked() {
        super.onBackPressed()
    }

    override fun onQuestionsListClicked() {
        mScreensNavigator.toQuestionsListClearTop()
    }

    override fun onBackPressed() {
        if(mQuestionDetailsViewMvc.showDrawer){
            mQuestionDetailsViewMvc.showDrawer = false
        }else {
            super.onBackPressed()
        }
    }
}
