package com.rec.aamvvmcomposedemo.data.remote

import com.rec.aamvvmcomposedemo.data.model.LedRequest
import com.rec.aamvvmcomposedemo.data.model.LedResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("led/{id}")
    suspend fun setLed(
        @Path("id") id: Int,
        @Body request: LedRequest
    ): LedResponse
}
