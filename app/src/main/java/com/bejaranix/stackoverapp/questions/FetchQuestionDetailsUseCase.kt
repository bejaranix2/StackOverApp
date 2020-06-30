package com.bejaranix.stackoverapp.questions

import com.bejaranix.stackoverapp.common.BaseObservable
import com.bejaranix.stackoverapp.networking.questions.QuestionDetailsResponseSchema
import com.bejaranix.stackoverapp.networking.questions.QuestionSchema
import com.bejaranix.stackoverapp.networking.StackoverflowApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchQuestionDetailsUseCase(val mStackoverflowApi: StackoverflowApi): BaseObservable<FetchQuestionDetailsUseCase.Listener>() {
    interface Listener {
        fun onQuestionDetailFetched(questionDetails: QuestionDetails)
        fun onQuestionDetailsFetchFailed()
    }

    fun fetchQuestionDetailsAndNotify(questionId:String){
        mStackoverflowApi.fetchQuestionDetails(questionId)
            ?.enqueue(object: Callback<QuestionDetailsResponseSchema?> {
                override fun onFailure(call: Call<QuestionDetailsResponseSchema?>, t: Throwable) {
                    notifyFailure()
                    t.printStackTrace()
                }
                override fun onResponse(
                    call: Call<QuestionDetailsResponseSchema?>,
                    response: Response<QuestionDetailsResponseSchema?>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            notifySuccess(it.mQuestions)
                        }
                    }else{
                        notifyFailure()
                    }
                }
            })

    }

    private fun notifyFailure(){
        getListeners().forEach {
            it.onQuestionDetailsFetchFailed()
        }
    }

    private fun notifySuccess(questionSchema: List<QuestionSchema>){
        val question = questionSchema[0]
        getListeners().forEach {
            it.onQuestionDetailFetched(
                QuestionDetails(
                    question.mId,
                    question.mTitle,
                    question.mBody
                )
            )
        }
    }

}