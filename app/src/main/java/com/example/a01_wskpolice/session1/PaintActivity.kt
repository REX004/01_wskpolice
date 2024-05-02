package com.example.a01_wskpolice.session1

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.a01_wskpolice.R
import com.example.a01_wskpolice.databinding.ActivityPaintBinding
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import java.io.File
import java.io.FileOutputStream

class PaintActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaintBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            drawingView.setBrushAlpha(120)
            drawingView.setBrushColor(R.color.white)
            drawingView.setSizeForBrush(12)
            drawingView.undo()
            drawingView.redo()
            drawingView.erase(Color.WHITE)
            drawingView.clearDrawingBoard()
        }

        val mDefaultColor = binding.drawingView.getBrushColor()

        binding.pen.setOnClickListener {
            showSeekBarDialog(this) { selectedSize ->
                binding.drawingView.setSizeForBrush(selectedSize)
            }
        }

        binding.color.setOnClickListener {
            ColorPickerDialog
                .Builder(this)
                .setTitle("Pick Theme")
                .setColorShape(ColorShape.SQAURE)
                .setDefaultColor(mDefaultColor)
                .setColorListener { color, colorHex ->
                    binding.drawingView.setBrushColor(color)
                }
                .show()
        }

        binding.rubber.setOnClickListener {
            binding.drawingView.setBrushColor(Color.WHITE)
        }

        binding.shareImg.setOnClickListener {
            sendPhoto()
        }
    }

    private fun sendPhoto() {
        val bitmap = getScreenShotFromView(binding.drawingView)
        bitmap?.let {
            val file = createTempFile()
            saveBitmapToFile(bitmap, file)
            val fileUri = FileProvider.getUriForFile(
                this,
                "${packageName}.provider",
                file
            )
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "image/jpeg"
            shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri)
            startActivity(Intent.createChooser(shareIntent, "Send image"))
        }
    }

    private fun createTempFile(): File {
        return File.createTempFile("temp_image", ".jpg", cacheDir)
    }

    private fun saveBitmapToFile(bitmap: Bitmap, file: File) {
        val fos = FileOutputStream(file)
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fos.close()
        }
    }

    private fun getScreenShotFromView(v: View): Bitmap? {
        var screenshot: Bitmap? = null
        try {
            screenshot = Bitmap.createBitmap(v.measuredWidth, v.measuredHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(screenshot)
            v.draw(canvas)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return screenshot
    }

    private fun showSeekBarDialog(context: Context, onSizeSelected: (Int) -> Unit) {
        val seekBar = SeekBar(context)
        seekBar.max = 100
        seekBar.progress = 1

        val dialog = AlertDialog.Builder(context)
            .setTitle("Chose the size")
            .setView(seekBar)
            .setPositiveButton("OK") { dialog, _ ->
                val selectedValue = seekBar.progress
                onSizeSelected(selectedValue)
                dialog.dismiss()
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }
}