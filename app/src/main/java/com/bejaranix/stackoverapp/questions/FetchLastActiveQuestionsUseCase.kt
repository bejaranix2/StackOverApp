package com.bejaranix.stackoverapp.questions

import com.bejaranix.stackoverapp.common.BaseObservable
import com.bejaranix.stackoverapp.common.Constants
import com.bejaranix.stackoverapp.networking.questions.QuestionSchema
import com.bejaranix.stackoverapp.networking.questions.QuestionsListResponseSchema
import com.bejaranix.stackoverapp.networking.StackoverflowApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchLastActiveQuestionsUseCase (val mStackoverflowApi: StackoverflowApi): BaseObservable<FetchLastActiveQuestionsUseCase.Listener>() {
    interface Listener {
        fun onQuestionsFetched(questions: List<Question>)
        fun onQuestionsFetchFailed()
    }

    fun fetchLastActiveQuestionsAndNotify(){
        mStackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
            ?.enqueue(object : Callback<QuestionsListResponseSchema?> {
                override fun onFailure(call: Call<QuestionsListResponseSchema?>, t: Throwable) {
                    notifyFailure()
                }

                override fun onResponse(
                    call: Call<QuestionsListResponseSchema?>,
                    response: Response<QuestionsListResponseSchema?>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            notifySuccess(it.mQuestions)
                        }
                    } else {
                        notifyFailure()
                    }
                }
            })
    }

    private fun notifyFailure(){
        getListeners().forEach {
            it.onQuestionsFetchFailed()
        }
    }

    private fun notifySuccess(mQuestions: List<QuestionSchema>) {
        val questions: MutableList<Question> = ArrayList(mQuestions.size)
        for (questionSchema in mQuestions) {
            questions.add(Question(questionSchema.mId, questionSchema.mTitle))
        }
        getListeners().forEach {
            it.onQuestionsFetched(questions)
        }
    }
}