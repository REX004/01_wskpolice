package com.example.a01_wskpolice.session1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a01_wskpolice.R
import com.example.a01_wskpolice.databinding.ActivityPhotoRobotsBinding


class PhotoRobotsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhotoRobotsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoRobotsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backImg.setOnClickListener {
            onBackPressed()
        }
        binding.newphotoRobotImg.setOnClickListener {
            startActivity(Intent(this, NewPhotoRobotsActivity::class.java))
        }

    }
}