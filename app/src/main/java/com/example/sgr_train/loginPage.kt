package com.example.sgr_train

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sgr_train.databinding.ActivityLoginPageBinding

class loginPage : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        title="SGRTicket"
        var view = binding.root

        setContentView(view)

        binding.login.setOnClickListener {
            val mainScreenPage = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainScreenPage)
        }
        binding.signin.setOnClickListener {
            val logInpage = Intent(applicationContext, signupPage::class.java)
            startActivity(logInpage)
        }
    }


}