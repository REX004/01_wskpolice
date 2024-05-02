package com.example.a01_wskpolice.session1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.a01_wskpolice.databinding.SignActivityBinding

class SignActivity : AppCompatActivity() {

    private lateinit var binding: SignActivityBinding
    private var isAttempted = 0

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



        }

    }
    private fun authorizeUser(login: String, password: String){

    }

}