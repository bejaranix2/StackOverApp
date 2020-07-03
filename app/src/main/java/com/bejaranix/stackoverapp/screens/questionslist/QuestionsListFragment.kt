package com.bejaranix.stackoverapp.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bejaranix.stackoverapp.screens.common.controller.BaseFragment

class QuestionsListFragment : BaseFragment(){

    companion object{
        private const val SAVE_STATE_SCREEN_STATE = "SAVE_STATE_SCREEN_STATE"

        fun newInstance():QuestionsListFragment{
            return QuestionsListFragment()
        }
    }

    private lateinit var mQuestionListController: QuestionListController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mQuestionListViewMvc =
            getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(container)
        mQuestionListController = getCompositionRoot().getQuestionListController()
        mQuestionListController.bindView(mQuestionListViewMvc)
        savedInstanceState?.apply { restoreControllerState(this) }
        return mQuestionListViewMvc.rootView
    }

    private fun restoreControllerState(bundle: Bundle) {
        mQuestionListController.restoreSavedState(bundle.getSerializable(SAVE_STATE_SCREEN_STATE)
                as QuestionListController.SavedState)
    }

    override fun onStart() {
        super.onStart()
        mQuestionListController.onStart()
    }

    override fun onStop() {
        super.onStop()
        mQuestionListController.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val screenState = mQuestionListController.getSavedState()
        outState.putSerializable(SAVE_STATE_SCREEN_STATE, screenState)
    }
}