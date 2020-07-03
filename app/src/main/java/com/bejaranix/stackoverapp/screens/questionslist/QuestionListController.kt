package com.bejaranix.stackoverapp.screens.questionslist

import com.bejaranix.stackoverapp.questions.FetchLastActiveQuestionsUseCase
import com.bejaranix.stackoverapp.questions.Question
import com.bejaranix.stackoverapp.screens.common.dialogs.DialogEventsBus
import com.bejaranix.stackoverapp.screens.common.dialogs.DialogsManager
import com.bejaranix.stackoverapp.screens.common.dialogs.promptdialog.PromptDialogEvent
import com.bejaranix.stackoverapp.screens.common.screensnavigator.ScreensNavigator
import java.io.Serializable

class QuestionListController(
    private val mFetchLastActiveQuestionsUseCase: FetchLastActiveQuestionsUseCase,
    private val mScreensNavigator: ScreensNavigator,
    private val mDialogsManager: DialogsManager,
    private val mDialogEventsBus: DialogEventsBus
    ):
    QuestionListViewMvc.Listener, FetchLastActiveQuestionsUseCase.Listener,
    DialogEventsBus.Listener {

    enum class ScreenStatus{IDLE, FETCHING_QUESTIONS,QUESTIONS_LIST_SHOWN , NETWORK_ERROR}

    private val DIALOG_ID_NETWORK_ERROR = "DIALOG_ID_NETWORK_ERROR"

    private var mScreenState = ScreenStatus.IDLE

    private var mQuestionListViewMvc:QuestionListViewMvc?=null

    fun bindView(questionListViewMvc: QuestionListViewMvc){
        mQuestionListViewMvc = questionListViewMvc
    }

    fun onStart(){
        mDialogEventsBus.registerListener(this)
        mQuestionListViewMvc?.registerListener(this)
        mFetchLastActiveQuestionsUseCase.registerListener(this)
        if (mScreenState != ScreenStatus.NETWORK_ERROR) {
            fetchQuestionsAndNotify();
        }
    }

    fun getSavedState() = SavedState(mScreenState)

    fun restoreSavedState(savedState: SavedState) {mScreenState = savedState.mScreenState}

    private fun fetchQuestionsAndNotify() {
        mScreenState = ScreenStatus.FETCHING_QUESTIONS
        mQuestionListViewMvc?.loading(true)
        mFetchLastActiveQuestionsUseCase.fetchLastActiveQuestionsAndNotify()
    }

    fun onStop(){
        mDialogEventsBus.unregisterListener(this)
        mFetchLastActiveQuestionsUseCase.unregisterListener(this)
    }

    override fun onQuestionClicked(question: Question) {
        mQuestionListViewMvc?.unregisterListener(this)
        mScreensNavigator.toQuestionDetails(question.mId)
    }

    override fun onQuestionsFetched(questions: List<Question>) {
        mScreenState = ScreenStatus.QUESTIONS_LIST_SHOWN
        mQuestionListViewMvc?.bindQuestions(questions)
        mQuestionListViewMvc?.loading(false)
    }

    override fun onQuestionsFetchFailed() {
        mScreenState = ScreenStatus.NETWORK_ERROR
        mQuestionListViewMvc?.loading(false)
        mDialogsManager.showUseCaseErrorDialog(DIALOG_ID_NETWORK_ERROR)
    }

    override fun onDialogEvent(event: Any) {
        if(event is PromptDialogEvent){
            when(event.clickButton){
                PromptDialogEvent.Button.POSITIVE ->{

                    fetchQuestionsAndNotify()
                }
                PromptDialogEvent.Button.NEGATIVE ->{
                    mScreenState = ScreenStatus.IDLE
                }
            }
        }
    }

    data class SavedState(val mScreenState: ScreenStatus):Serializable

}