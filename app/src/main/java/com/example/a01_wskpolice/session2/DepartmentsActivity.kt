package com.example.a01_wskpolice.session2

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a01_wskpolice.adapters.session2.DepartmentAdapter
import com.example.a01_wskpolice.api.ApiService
import com.example.a01_wskpolice.databinding.DepartmnetsActivityBinding
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DepartmentsActivity : AppCompatActivity() {

    private lateinit var adapter: DepartmentAdapter
    private lateinit var binding: DepartmnetsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DepartmnetsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!isNetworkAvailable()){
            showNoInternetDialog()
            return
        }

        binding.backImg.setOnClickListener {
            onBackPressed()
        }

        adapter = DepartmentAdapter(this)
        binding.departRV.layoutManager = LinearLayoutManager(this)
        binding.departRV.adapter = adapter

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mad2019.hakta.pro/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val departmentApi = retrofit.create(ApiService::class.java)

        lifecycleScope.launch {
            try {
                val response = departmentApi.getDepartments()

                adapter.submitList(response.data)


            } catch (e: Exception) {
                showErrorDialog("Error occurred: ${e.message}")
            }
        }

    }
    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .setCancelable(false)
            .show()
    }

    private fun showNoInternetDialog(){
        AlertDialog.Builder(this)
            .setMessage("No internet")
            .setPositiveButton("Go Back") { dialog, _ ->
                onBackPressed()
            }
            .setNegativeButton("Exit app") { dialog, _ ->
                finishAffinity()
            }
            .setNeutralButton("Try again") { _, _ ->

                fun isInternetAvailable(): Boolean {
                    val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
                    val networkInfo = connectivityManager.activeNetworkInfo
                    return networkInfo != null && networkInfo.isConnected
                }
                if (!isInternetAvailable()) {
                    showNoInternetDialog()
                }else {
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.level = HttpLoggingInterceptor.Level.BODY

                    val client = OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .build()

                    val retrofit = Retrofit.Builder()
                        .baseUrl("http://mad2019.hakta.pro/")
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                    val departmentApi = retrofit.create(ApiService::class.java)

                    lifecycleScope.launch {
                        try {
                            val response = departmentApi.getDepartments()

                            adapter.submitList(response.data)


                        } catch (e: Exception) {
                            showErrorDialog("Error occurred: ${e.message}")
                        }
                    }
                }
            }
            .setCancelable(false)
            .show()
    }
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}