package com.bejaranix.stackoverapp.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bejaranix.stackoverapp.R
import com.bejaranix.stackoverapp.questions.Question
import com.bejaranix.stackoverapp.screens.common.views.BaseObservableViewMvc
import com.bejaranix.stackoverapp.screens.common.ViewMvcFactory
import com.bejaranix.stackoverapp.screens.common.navdrawer.NavDrawerHelper
import com.bejaranix.stackoverapp.screens.common.toastshelper.ToastsHelper
import com.bejaranix.stackoverapp.screens.common.toolbar.ToolbarViewMvc
import com.google.android.material.appbar.MaterialToolbar

class QuestionListViewMvcImpl : BaseObservableViewMvc<QuestionListViewMvc.Listener>,
    QuestionRecyclerAdapter.Listener, QuestionListViewMvc, ToolbarViewMvc.HamburgerListener,
    ToolbarViewMvc.LocationListener {

    private var mToolbarViewMvc: ToolbarViewMvc
    private val mToolbar:MaterialToolbar
    private var mProgressBar: ProgressBar
    private var mQuestionsListAdapter: QuestionRecyclerAdapter
    private var mListQuestions: RecyclerView
    private val mToastsHelper: ToastsHelper
    private val mNavDrawerHelper: NavDrawerHelper

    constructor(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        viewMvcFactory: ViewMvcFactory,
        toastsHelper: ToastsHelper,
        navDrawerHelper: NavDrawerHelper
    )  {

        rootView = inflater.inflate(R.layout.layout_questions_list, parent, false)
        mListQuestions = findViewById(R.id.lst_questions)
        mProgressBar = findViewById(R.id.progress_bar)
        mQuestionsListAdapter = QuestionRecyclerAdapter( this, viewMvcFactory)
        mListQuestions.layoutManager = LinearLayoutManager(getContext())
        mToastsHelper = toastsHelper
        mListQuestions.adapter = mQuestionsListAdapter
        mToolbar = rootView.findViewById(R.id.app_bar_layout)
        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(mToolbar)
        mNavDrawerHelper = navDrawerHelper
        configureToolbar()
    }

    private fun configureToolbar(){
        mToolbarViewMvc.setText(getString(R.string.questions_list_screen_title))
        mToolbar.addView(mToolbarViewMvc.rootView)
        mToolbarViewMvc.enableHamburgerButtonAndListen(this)
        mToolbarViewMvc.enableLocationButtonAndListen(this)
        mToolbar.navigationIcon = null      // to hide Navigation icon
    }


    override fun onQuestionClicked(question: Question) {
        getListeners().forEach {
            it.onQuestionClicked(question)
        }
    }

    override fun bindQuestions(questions: List<Question>) {
        mQuestionsListAdapter.bindQuestions(questions)
    }

    override fun loading(loading: Boolean) {
        val visibility = if(loading) View.VISIBLE else View.GONE
        mProgressBar.visibility = visibility
    }

    override fun onHamburgerClicked() {
        mNavDrawerHelper.showDrawer(true)
    }

    override fun onLocationClicked() {
        mToastsHelper.showToast(R.string.location_displayed)
    }

}