package com.example.worldcinema

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.worldcinema.model.Token
import com.example.worldcinema.retrofit.RetrofitInstance
import kotlinx.android.synthetic.main.activity_sign_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Loads [MainFragment].
 */
class SignInScreen : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }

    fun goMainScreen(view: View) {
        if (email.text.isEmpty() || password.text.isEmpty()) {
            Toast.makeText(this, "Поля не заполнены", Toast.LENGTH_LONG).show()
        }
        else {
            val service = RetrofitInstance.api
            val email = email.text
            val password = password.text
            val call_login = service.getLogin(LoginModel(email.toString(), password.toString()))
            call_login.enqueue(object : Callback<Token> {
                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    Log.d("RETROFIT", "ok")
                    val intent = Intent(this@SignInScreen, MainScreen::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<Token>, t: Throwable) {
                    Toast.makeText(this@SignInScreen, "Ошибка на сервере", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@SignInScreen, MainScreen::class.java)
                    startActivity(intent)
                }

            })
        }
    }
}