package com.example.a01_wskpolice.session1

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a01_wskpolice.databinding.SignActivityBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class SignActivity : AppCompatActivity() {

    private lateinit var binding: SignActivityBinding
    private var isAttempted = 0
    private val TAG = "SignActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signBT.setOnClickListener {
            if (isAttempted > 1){
                binding.signBT.visibility = View.GONE
                binding.gustBT.visibility = View.GONE

                binding.captchaET.visibility = View.VISIBLE
                binding.captchaContainer.visibility = View.VISIBLE
                binding.captchaEnterTXT.visibility = View.VISIBLE
                binding.okBT.visibility = View.VISIBLE
            }else {
                val login = binding.loginET.text.toString()
                val password = binding.passwordET.text.toString()
                authorizeUser(login, password)
            }

            var captcha = generateCaptcha()

            binding.captcha1.apply {
                rotation = (-40..40).random().toFloat()
                scaleY = (1..2).random().toFloat()
                scaleX = (1..2).random().toFloat()
                text = captcha

            }

            binding.okBT.setOnClickListener {
                val enteredCaptcha = binding.captchaET.text.toString()
                if (enteredCaptcha == captcha){
                    val login = binding.loginET.text.toString()
                    val password = binding.passwordET.text.toString()
                    authorizeUser(login, password)
                } else {
                    captcha = generateCaptcha()
                    binding.captchaET.text.clear()

                    binding.captcha1.apply {
                        rotation = (-40..40).random().toFloat()
                        scaleY = (1..2).random().toFloat()
                        scaleX = (1..2).random().toFloat()
                        text = captcha
                    }
                }
            }


        }
        binding.gustBT.setOnClickListener {
            Log.e(TAG, "Activity switched")
            Toast.makeText(this@SignActivity, "Authorizated by guest", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainGuestActivity::class.java))
        }

    }
    private fun generateCaptcha() : String{
        val charset = "qwertyuiopasdfghjklzxcvbnm1234567890"
        return (1..3)
            .map { charset.random() }
            .joinToString("")
    }
    private fun authorizeUser(login: String, password: String){
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://mad2019.hakta.pro/api/login/?login=$login&password=$password")
            .build()

        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "Failed to make request: ${e.message}")
                runOnUiThread{
                    Toast.makeText(this@SignActivity, "Failed to make request", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                response.body?.close()
                responseData?.let {
                    runCatching {
                        val jsonObject = JSONObject(it)
                        val success = jsonObject.getBoolean("success")
                        val message = if (success) "Authorization success" else "Authorization failed"
                        Log.d(TAG, message)
                        runOnUiThread{
                            Toast.makeText(this@SignActivity, message, Toast.LENGTH_SHORT).show()
                        }
                        if (success){
                            startActivity(Intent(this@SignActivity, MainSignedActivity::class.java))
                            finish()
                        } else{
                            isAttempted += 1
                        }
                    }.onFailure {
                        Log.e(TAG, "Failed to make response")
                        runOnUiThread{
                            Toast.makeText(this@SignActivity, "Failed to make response", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        })
    }

}