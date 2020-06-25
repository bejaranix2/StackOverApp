package com.bejaranix.stackoverapp.networking

import com.google.gson.annotations.SerializedName

data class QuestionSchema(

    @SerializedName("title")
    val mTitle:String,

    @SerializedName("question_id")
    val mId:String,

    @SerializedName("body")
    val mBody:String,

    @SerializedName("owner")
    val mOwner:UserSchema

)