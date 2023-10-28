package com.example.tpsharedpref

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class HomePage : AppCompatActivity() {
    private lateinit var loginUser: TextView
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        loginUser =  this.findViewById(R.id.loginUser)
        username = intent.getStringExtra("username").toString()
        loginUser.text = "Welcome to your account $username \uD83D\uDE0A !"
        Toast.makeText(this, "Welcome $username !", Toast.LENGTH_SHORT).show()
    }

    fun logout(view: View) {
        finish()
    }
}