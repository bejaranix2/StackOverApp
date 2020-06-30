package com.bejaranix.stackoverapp.screens.questiondetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.bejaranix.stackoverapp.R
import com.bejaranix.stackoverapp.questions.QuestionDetails
import com.bejaranix.stackoverapp.screens.common.ViewMvcFactory
import com.bejaranix.stackoverapp.screens.common.navdrawer.BaseNavDrawerViewMvc
import com.bejaranix.stackoverapp.screens.common.navdrawer.DrawerItems
import com.bejaranix.stackoverapp.screens.common.toastshelper.ToastsHelper
import com.bejaranix.stackoverapp.screens.common.toolbar.ToolbarViewMvc
import com.google.android.material.appbar.MaterialToolbar

class QuestionDetailsViewMvcImpl: BaseNavDrawerViewMvc<QuestionDetailsViewMvc.Listener>, QuestionDetailsViewMvc,
    ToolbarViewMvc.NavigateUpListener {

    private val titleDetails: TextView
    private val bodyDetails: TextView
    private val progressBar: ProgressBar
    private var mToolbarViewMvc: ToolbarViewMvc
    private val mToolbar: MaterialToolbar
    private val mToastsHelper: ToastsHelper

    constructor(inflater: LayoutInflater,
                parent: ViewGroup?,
                viewMvcFactory: ViewMvcFactory,
                toastsHelper: ToastsHelper) : super(inflater, parent) {
        rootView = inflater.inflate(R.layout.activity_question_details, parent, false)
        titleDetails = findViewById(R.id.title_details)
        bodyDetails = findViewById(R.id.body_details)
        progressBar = findViewById(R.id.progress_bar)
        mToolbar = findViewById(R.id.app_bar_layout)
        mToastsHelper = toastsHelper
        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(mToolbar)
        configureToolbar()
    }

    private fun configureToolbar(){
        mToolbarViewMvc.setText(getString(R.string.questions_list_screen_title))
        mToolbar.addView(mToolbarViewMvc.rootView)
        mToolbarViewMvc.enableBackButtonAndListen(this)
    }


    override fun bindQuestionDetails(questionDetails: QuestionDetails) {
        titleDetails.text = questionDetails.title
        bodyDetails.text = questionDetails.body
    }

    override fun loading(loading: Boolean) {
        val visibility = if(loading) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
    }

    override fun onNavigateUpClicked() {
        for (listener in getListeners()) {
            listener.onNavigateUpClicked()
        }
    }

    override fun onDrawerItemClicked(questionsList: DrawerItems) {
        getListeners().forEach {
            when(questionsList){
                DrawerItems.QUESTIONS_LIST -> it.onQuestionsListClicked()
            }
        }
    }

}