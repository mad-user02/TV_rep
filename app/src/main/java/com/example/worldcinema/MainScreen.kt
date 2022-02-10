package com.example.worldcinema

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcinema.model.FilmsModel
import com.example.worldcinema.retrofit.RetrofitInstance
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainScreen : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = RetrofitInstance.api
        val call_movies1 = service.getFilms("inTrend")
        call_movies1.enqueue(object : Callback<List<FilmsModel>> {
            override fun onResponse(
                call: Call<List<FilmsModel>>,
                response: Response<List<FilmsModel>>
            ) {
                val result : List<FilmsModel> = response.body() as List<FilmsModel>
                val adapter = MyAdapter(result, this@MainScreen)
                Log.d("RETROFIT", result.toString())
                rv_trend.adapter = adapter
                rv_trend.layoutManager = LinearLayoutManager(this@MainScreen, RecyclerView.HORIZONTAL, false)

                object : CountDownTimer(3000, 1) {
                    override fun onTick(p0: Long) {

                    }

                    override fun onFinish() {
                        val intent = Intent(this@MainScreen, MovieScreen::class.java)
                        intent.putExtra("film_id", result[0].movieId)
                        intent.putExtra("film_name", result[0].name)
                        intent.putExtra("film_age", result[0].age)
                        intent.putExtra("film_poster", result[0].poster)
                        intent.putExtra("film_description", result[0].description)
                        startActivity(intent)
                    }

                }.start()

            }

            override fun onFailure(call: Call<List<FilmsModel>>, t: Throwable) {
            }
        })
        val call_movies2 = service.getFilms("new")
        call_movies2.enqueue(object : Callback<List<FilmsModel>> {
            override fun onResponse(
                call: Call<List<FilmsModel>>,
                response: Response<List<FilmsModel>>
            ) {
                val result : List<FilmsModel> = response.body() as List<FilmsModel>
                val adapter = MyAdapter(result, this@MainScreen)
                Log.d("RETROFIT", result.toString())
                rv_new.adapter = adapter
                rv_new.layoutManager = LinearLayoutManager(this@MainScreen, RecyclerView.HORIZONTAL, false)

            }

            override fun onFailure(call: Call<List<FilmsModel>>, t: Throwable) {
            }
        })
        val call_movies3 = service.getFilms("forMe")
        call_movies3.enqueue(object : Callback<List<FilmsModel>> {
            override fun onResponse(
                call: Call<List<FilmsModel>>,
                response: Response<List<FilmsModel>>
            ) {
                val result : List<FilmsModel> = response.body() as List<FilmsModel>
                val adapter = MyAdapter(result, this@MainScreen)
                Log.d("RETROFIT", result.toString())
                rv_my.adapter = adapter
                rv_my.layoutManager = LinearLayoutManager(this@MainScreen, RecyclerView.HORIZONTAL, false)

            }

            override fun onFailure(call: Call<List<FilmsModel>>, t: Throwable) {
            }
        })
    }
}