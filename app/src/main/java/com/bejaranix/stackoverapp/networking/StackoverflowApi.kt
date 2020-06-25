package com.bejaranix.stackoverapp.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface StackoverflowApi {
    @GET("questions?sort=activity&order=desc&site=stackoverflow&filter=withbody")
    fun fetchLastActiveQuestions(@Query("pagesize") pageSize: Int?): Call<QuestionsListResponseSchema?>?
}