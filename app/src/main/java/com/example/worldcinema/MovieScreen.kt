package com.example.worldcinema

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldcinema.model.EModel
import com.example.worldcinema.model.Token
import com.example.worldcinema.retrofit.RetrofitInstance
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieScreen : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        Glide.with(this).load("http://cinema.areas.su/up/images/" + intent.getStringExtra("film_poster").toString()).into(image_poster)
        text_description.text = intent.getStringExtra("film_description")

        val service = RetrofitInstance.api
        val film_id = intent.getStringExtra("film_id")
        val call_ep = service.getEpisodes(film_id.toString())
        call_ep.enqueue(object : Callback<List<EModel>> {
            override fun onResponse(call: Call<List<EModel>>, response: Response<List<EModel>>) {
                val result : List<EModel> = response.body() as List<EModel>
                val adapter1 = EpAdapter(result, this@MovieScreen)
                rv_ep.adapter = adapter1
                rv_ep.layoutManager = LinearLayoutManager(this@MovieScreen, RecyclerView.HORIZONTAL, false)
                e1.text = result[0].year
                e2.text = "-" + result[result.size - 1].year
            }

            override fun onFailure(call: Call<List<EModel>>, t: Throwable) {
            }

        })
    }
}