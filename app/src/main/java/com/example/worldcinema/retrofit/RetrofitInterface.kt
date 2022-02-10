package com.example.worldcinema.retrofit

import com.example.worldcinema.LoginModel
import com.example.worldcinema.model.FilmsModel
import com.example.worldcinema.model.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitInterface {
    @POST("auth/login")
    fun getLogin(@Body body : LoginModel) : Call<Token>

    @GET("movies")
    fun getFilms(@Query("filter") filter : String) : Call<List<FilmsModel>>
}