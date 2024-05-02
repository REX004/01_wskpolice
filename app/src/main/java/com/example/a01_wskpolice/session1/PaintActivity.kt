package com.example.a01_wskpolice.session1

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a01_wskpolice.R
import com.example.a01_wskpolice.databinding.ActivityPaintBinding
import com.mihir.drawingcanvas.drawingView

class PaintActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaintBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backImg.setOnClickListener {
            onBackPressed()
        }

        binding.rubber.setOnClickListener {
            binding.painter.setBrushColor(Color.WHITE)
        }
        binding.apply {
            painter.getBrushAlpha()
            painter.getBrushSize()
            painter.getDrawing()

        }


    }
    private fun getSize(){
        AlertDialog.Builder(this)
            .setMessage("Choose")

    }

}


