package com.example.worldcinema.retrofit

import com.example.worldcinema.LoginModel
import com.example.worldcinema.model.EModel
import com.example.worldcinema.model.FilmsModel
import com.example.worldcinema.model.Token
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {
    @POST("auth/login")
    fun getLogin(@Body body : LoginModel) : Call<Token>

    @GET("movies")
    fun getFilms(@Query("filter") filter : String) : Call<List<FilmsModel>>

    @GET("movies/{movieId}/episodes")
    fun getEpisodes(@Path("movieId") movieId : String) : Call<List<EModel>>
}