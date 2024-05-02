package com.example.a01_wskpolice.session2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a01_wskpolice.databinding.ShowWantedActivityBinding

class ShowWantedActivity : AppCompatActivity() {
    private lateinit var binding: ShowWantedActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShowWantedActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}