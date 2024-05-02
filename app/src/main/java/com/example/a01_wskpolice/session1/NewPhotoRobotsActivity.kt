package com.example.a01_wskpolice.session1
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import com.example.a01_wskpolice.adapters.PhotoRobotAdapter
import com.example.a01_wskpolice.databinding.ActivityNewPhotoRobotsBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.UUID


class NewPhotoRobotsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewPhotoRobotsBinding
    private lateinit var topAdapter: PhotoRobotAdapter
    private lateinit var middleAdapter: PhotoRobotAdapter
    private lateinit var bottomAdapter: PhotoRobotAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPhotoRobotsBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val topDrawables = getTopImages()
        val middleDrawables = getMiddleImages()
        val bottomDrawables = getBottomImages()

        topAdapter = PhotoRobotAdapter(topDrawables)
        middleAdapter = PhotoRobotAdapter(middleDrawables)
        bottomAdapter = PhotoRobotAdapter(bottomDrawables)


    }

    private fun getImages(folderName: String): List<Drawable> {
        val list = assets.list("fotorobot")?.filter { it.contains(folderName) }
        val drawables = mutableListOf<Drawable>()
        try {
            list?.forEach { fileName ->
                assets.open("fotorobot/$fileName").use { inputStream ->
                    Drawable.createFromStream(inputStream, null)?.let { drawables.add(it) }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return drawables
    }
    private fun getTopImages(): List<Drawable> = getImages("top")

    private fun getMiddleImages(): List<Drawable> = getImages("middle")

    private fun getBottomImages(): List<Drawable> = getImages("bottom")

}