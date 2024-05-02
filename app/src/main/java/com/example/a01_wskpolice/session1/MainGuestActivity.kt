package com.example.a01_wskpolice.session1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a01_wskpolice.R
import com.example.a01_wskpolice.databinding.ActivityMainGuestBinding

class MainGuestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainGuestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}