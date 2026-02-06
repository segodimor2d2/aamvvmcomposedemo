package com.rec.aamvvmcomposedemo.data.remote

import com.rec.aamvvmcomposedemo.data.model.LedRequest
import com.rec.aamvvmcomposedemo.data.model.LedResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("led/{id}")
    suspend fun getLed(
        @Path("id") id: Int
    ): LedResponse

    @POST("led/{id}")
    suspend fun setLed(
        @Path("id") id: Int,
        @Body request: LedRequest
    ): LedResponse

}
