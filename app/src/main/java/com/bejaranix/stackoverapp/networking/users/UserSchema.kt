package com.bejaranix.stackoverapp.networking.users

import com.google.gson.annotations.SerializedName

class UserSchema (
    @SerializedName("display_name")
    val mUserDisplayName:String,

    @SerializedName("profile_image")
    val mUserAvatarUrl:String
)