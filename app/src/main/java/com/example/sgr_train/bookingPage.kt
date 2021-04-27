package com.example.sgr_train

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sgr_train.databinding.ActivityBookingPageBinding
import com.example.sgr_train.databinding.ActivityLoginPageBinding

class bookingPage : AppCompatActivity() {
    private lateinit var binding: ActivityBookingPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_page)
        binding = ActivityBookingPageBinding.inflate(layoutInflater)

        var view = binding.root

        setContentView(view)
    }
}