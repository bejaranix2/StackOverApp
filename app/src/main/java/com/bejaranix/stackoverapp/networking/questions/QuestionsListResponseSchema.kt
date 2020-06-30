package com.bejaranix.stackoverapp.networking.questions

import com.bejaranix.stackoverapp.networking.questions.QuestionSchema
import com.google.gson.annotations.SerializedName

data class QuestionsListResponseSchema(
    @SerializedName("items")
    val mQuestions:List<QuestionSchema>
)