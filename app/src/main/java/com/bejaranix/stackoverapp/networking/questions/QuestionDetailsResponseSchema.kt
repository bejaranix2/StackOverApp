package com.bejaranix.stackoverapp.networking.questions

import com.google.gson.annotations.SerializedName

class QuestionDetailsResponseSchema (
    @SerializedName("items")
    val mQuestions:List<QuestionSchema>
)