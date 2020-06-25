package com.bejaranix.stackoverapp.networking

import com.google.gson.annotations.SerializedName

data class QuestionsListResponseSchema(
    @SerializedName("items")
    val mQuestions:List<QuestionSchema>
)