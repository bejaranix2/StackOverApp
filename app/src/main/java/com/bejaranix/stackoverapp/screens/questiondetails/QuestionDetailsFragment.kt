package com.bejaranix.stackoverapp.screens.questiondetails

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bejaranix.stackoverapp.questions.FetchQuestionDetailsUseCase
import com.bejaranix.stackoverapp.questions.QuestionDetails
import com.bejaranix.stackoverapp.screens.common.controller.BaseFragment
import com.bejaranix.stackoverapp.screens.common.dialogs.DialogEventsBus
import com.bejaranix.stackoverapp.screens.common.dialogs.DialogsManager
import com.bejaranix.stackoverapp.screens.common.dialogs.promptdialog.PromptDialogEvent
import com.bejaranix.stackoverapp.screens.common.permissions.PermissionsHelper
import com.bejaranix.stackoverapp.screens.common.screensnavigator.ScreensNavigator
import java.lang.RuntimeException

class QuestionDetailsFragment:BaseFragment(),QuestionDetailsViewMvc.Listener,
    FetchQuestionDetailsUseCase.Listener, DialogEventsBus.Listener, PermissionsHelper.Listener {
    companion object{
        private const val ARG_QUESTION_ID = "ARG_QUESTION_ID"
        private const val SAVE_STATE_SCREEN_STATE = "SAVE_STATE_SCREEN_STATE"
        private const val DIALOG_ID_NETWORK_ERROR = "DIALOG_ID_NETWORK_ERROR"
        private const val REQUEST_CODE = 1001

        fun newInstance(questionId: String):QuestionDetailsFragment{
            val args = Bundle()
            args.putString(ARG_QUESTION_ID, questionId)
            val fragment = QuestionDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private enum class ScreenState{ IDLE, DETAILS_SHOWN, NETWORK_ERROR }

    private lateinit var mQuestionDetailsViewMvc: QuestionDetailsViewMvc
    private lateinit var mFetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase
    private lateinit var mScreensNavigator: ScreensNavigator
    private lateinit var mDialogsManager: DialogsManager
    private lateinit var mDialogEventsBus: DialogEventsBus
    private lateinit var mPermissionsHelper: PermissionsHelper
    private var questionId: String? = null
    private var mScreenState = ScreenState.IDLE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.apply {
            mScreenState = this.getSerializable(SAVE_STATE_SCREEN_STATE) as ScreenState
        }
        mFetchQuestionDetailsUseCase = getCompositionRoot().getFetchQuestionDetailsUseCase()
        mScreensNavigator = getCompositionRoot().getScreensNavigator()
        mDialogsManager = getCompositionRoot().getDialogsManager()
        mDialogEventsBus = getCompositionRoot().getDialogsEventBus()
        mPermissionsHelper = getCompositionRoot().getPermissionsHelper()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mQuestionDetailsViewMvc = getCompositionRoot()
            .getViewMvcFactory().getQuestionDetailsViewMvc(container)
        arguments?.apply {
            questionId = this.getString(ARG_QUESTION_ID)
        }
        return mQuestionDetailsViewMvc.rootView
    }

    override fun onStart() {
        super.onStart()
        mFetchQuestionDetailsUseCase.registerListener(this)
        mQuestionDetailsViewMvc.registerListener(this)
        mDialogEventsBus.registerListener(this)
        mPermissionsHelper.registerListener(this)
        questionId?.apply {
            fetchDetails(this)
        }
        if(mScreenState != ScreenState.NETWORK_ERROR){
            questionId?.let { mFetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(it) }
        }
    }

    override fun onStop() {
        super.onStop()
        mFetchQuestionDetailsUseCase.unregisterListener(this)
        mQuestionDetailsViewMvc.unregisterListener(this)
        mDialogEventsBus.unregisterListener(this)
        mPermissionsHelper.unregisterListener(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(SAVE_STATE_SCREEN_STATE, mScreenState)
    }

    private fun fetchDetails(questionId: String) {
        mQuestionDetailsViewMvc.loading(true)
        mFetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(questionId)
    }

    override fun onQuestionDetailFetched(questionDetails: QuestionDetails) {
        mScreenState = ScreenState.DETAILS_SHOWN
        mQuestionDetailsViewMvc.bindQuestionDetails(questionDetails)
        mQuestionDetailsViewMvc.loading(false)
    }

    override fun onQuestionDetailsFetchFailed() {
        mQuestionDetailsViewMvc.loading(false)
        if(mScreenState != ScreenState.NETWORK_ERROR) {
            mDialogsManager.showUseCaseErrorDialog(DIALOG_ID_NETWORK_ERROR)
        }
        mScreenState = ScreenState.NETWORK_ERROR

    }

    override fun onNavigateUpClicked() = mScreensNavigator.navigateUp()

    override fun onLocationClicked() {
        if(mPermissionsHelper.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)){
            mDialogsManager.showPermissionGrantedDialog(null)
        }else{
            mPermissionsHelper.requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, REQUEST_CODE)
        }
    }

    override fun onDialogEvent(event: Any) {
        if(event is PromptDialogEvent){
            when(event.clickButton){
                PromptDialogEvent.Button.POSITIVE ->{
                    mScreenState = ScreenState.IDLE
                    questionId?.let { mFetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(it) }
                }
                PromptDialogEvent.Button.NEGATIVE ->{
                    mScreenState = ScreenState.IDLE
                }
            }
        }
    }

    override fun onPermissionGranted(permission: String, requestCode: Int) {
        if(REQUEST_CODE == requestCode)
            mDialogsManager.showPermissionGrantedDialog(null)
    }
    override fun onPermissionDeclined(permission: String, requestCode: Int) {
        if(REQUEST_CODE == requestCode)
            mDialogsManager.showDeclinedDialog(null)
    }
    override fun onPermissionDeclinedDontAskAgain(permission: String, requestCode: Int){
        if(REQUEST_CODE == requestCode)
            mDialogsManager.showPermissionDeclinedCanAskMoreDialog(null)
    }
}