package com.bcaf.bcafretrofit.service

import com.bcaf.bcafretrofit.model.PostDummyData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface DummyApiInterface {

    @Headers("app-id:63bce1b6ac8257b92d263d5c")
    @POST("post/create/")
    fun postData(@Body dummyData: PostDummyData): Call<PostDummyDataResponse>

}