package com.example.a01_wskpolice.session1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a01_wskpolice.R
import com.example.a01_wskpolice.databinding.ActivityMainSignedBinding

class MainSignedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainSignedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainSignedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.aboutUsLL.setOnClickListener {
            startActivity(Intent(this, AboutUsActivity::class.java))
        }
        binding.backImg.setOnClickListener {
            onBackPressed()
        }
        binding.photorobotLL.setOnClickListener {
            startActivity(Intent(this, PhotoRobotsActivity::class.java))
        }
        binding.logoutLL.setOnClickListener {
            startActivity(Intent(this, SignActivity::class.java))
            finish()
        }
    }
}