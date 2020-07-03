package com.bejaranix.stackoverapp.common.dependencyinjection

import com.bejaranix.stackoverapp.common.Constants
import com.bejaranix.stackoverapp.networking.StackoverflowApi
import com.bejaranix.stackoverapp.screens.common.dialogs.DialogEventsBus
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CompositionRoot {

    private var mRetrofit: Retrofit? = null
    private var mDialogEventsBus: DialogEventsBus? = null

    fun getStackoverflowApi(): StackoverflowApi {
        return getRetrofit()
            .create(StackoverflowApi::class.java)
    }

    private fun getRetrofit():Retrofit{
        if(mRetrofit == null){
            mRetrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return mRetrofit!!
    }

    fun getDialogsEventBus(): DialogEventsBus {
        if(mDialogEventsBus == null){
            mDialogEventsBus = DialogEventsBus()
        }
        return mDialogEventsBus!!
    }
}