package com.example.a01_wskpolice.session1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a01_wskpolice.R
import com.example.a01_wskpolice.databinding.ActivityMainGuestBinding
import com.example.a01_wskpolice.session2.DepartmentsActivity
import com.example.a01_wskpolice.session2.WantedActivity

class MainGuestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainGuestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainGuestBinding.inflate(layoutInflater)
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
        binding.paintLL.setOnClickListener {
            startActivity(Intent(this, PaintActivity::class.java))
        }
        binding.wantedLL.setOnClickListener {
            startActivity(Intent(this, WantedActivity::class.java))
        }
        binding.departmntsLL.setOnClickListener {
            startActivity(Intent(this, DepartmentsActivity::class.java))
        }
    }
}