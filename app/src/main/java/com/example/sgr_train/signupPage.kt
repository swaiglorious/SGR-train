package com.example.sgr_train

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sgr_train.databinding.ActivityLoginPageBinding
import com.example.sgr_train.databinding.ActivitySignupPageBinding

class signupPage : AppCompatActivity() {
    private lateinit var binding: ActivitySignupPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
  title="SGRTicket"
        super.onCreate(savedInstanceState)
    binding= ActivitySignupPageBinding.inflate(layoutInflater)


        var view = binding.root

        setContentView(view)

        binding.signup.setOnClickListener {
            val logInpage = Intent(applicationContext, loginPage::class.java)
            startActivity(logInpage)
        }

    }

}