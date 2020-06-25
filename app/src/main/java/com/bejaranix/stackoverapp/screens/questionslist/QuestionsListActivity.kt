package com.bejaranix.stackoverapp.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ListView
import android.widget.Toast
import com.bejaranix.stackoverapp.R
import com.bejaranix.stackoverapp.common.Constants
import com.bejaranix.stackoverapp.networking.QuestionSchema
import com.bejaranix.stackoverapp.networking.QuestionsListResponseSchema
import com.bejaranix.stackoverapp.networking.StackoverflowApi
import com.bejaranix.stackoverapp.questions.Question
import com.bejaranix.stackoverapp.screens.common.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class QuestionsListActivity : BaseActivity(), QuestionListViewMvc.Listener {

    private lateinit var mStackoverflowApi: StackoverflowApi
    private lateinit var mQuestionListViewMvc: QuestionListViewMvc


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mQuestionListViewMvc = QuestionListViewMvcImpl(LayoutInflater.from(this),null)
        mQuestionListViewMvc.registerListener(this)
        mStackoverflowApi = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StackoverflowApi::class.java)
        fetchQuestions()
        setContentView(mQuestionListViewMvc.rootView)
    }

    private fun fetchQuestions() {
        mStackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
            ?.enqueue(object : Callback<QuestionsListResponseSchema?> {
                override fun onFailure(call: Call<QuestionsListResponseSchema?>, t: Throwable) {
                    networkCallFailed()
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<QuestionsListResponseSchema?>,
                    response: Response<QuestionsListResponseSchema?>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            bindQuestions(it.mQuestions)
                        }
                    } else {
                        networkCallFailed()
                    }
                }
            })
    }

    private fun bindQuestions(questionSchemas: List<QuestionSchema>) {
        val questions: MutableList<Question> = ArrayList(questionSchemas.size)
        for (questionSchema in questionSchemas) {
            questions.add(Question(questionSchema.mId, questionSchema.mTitle))
        }
        mQuestionListViewMvc.bindQuestions(questions)
    }

    private fun networkCallFailed() {
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
    }

    override fun onQuestionClicked(question: Question) {
        Toast.makeText(this, question.mTitle, Toast.LENGTH_SHORT).show();

    }
}
