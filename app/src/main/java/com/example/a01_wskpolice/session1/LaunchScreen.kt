package com.example.a01_wskpolice.session1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.a01_wskpolice.R
import com.example.a01_wskpolice.databinding.ActivityLaunchScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LaunchScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLaunchScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            delay(1800)
            startActivity(Intent(this@LaunchScreen, SignActivity::class.java))
            finish()
        }
    }
}