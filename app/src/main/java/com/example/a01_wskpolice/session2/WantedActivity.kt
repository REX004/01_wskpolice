package com.example.a01_wskpolice.session2

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.PixelCopy.request
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a01_wskpolice.R
import com.example.a01_wskpolice.adapters.session2.DepartmentAdapter
import com.example.a01_wskpolice.adapters.session2.WantedAdapter
import com.example.a01_wskpolice.api.ApiService
import com.example.a01_wskpolice.data.Wanted
import com.example.a01_wskpolice.databinding.ActivityWantedBinding
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WantedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWantedBinding
    private lateinit var adapterr: WantedAdapter
    private val wantedList = mutableListOf<Wanted>()
    private var isCheckboxVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWantedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backImg.setOnClickListener {
            onBackPressed()
        }
        adapterr = WantedAdapter(this)
        binding.wantedRV.layoutManager = LinearLayoutManager(this)
        binding.wantedRV.adapter = adapterr

        if (!isNetworkAvailable()){
            showNoInternetDialog()
        }else{
            request()
        }

    }




    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    private fun showNoInternetDialog() {
        AlertDialog.Builder(this)
            .setMessage("No internet")
            .setPositiveButton("Go Back") { dialog, _ ->
                onBackPressed()
            }
            .setNegativeButton("Exit app") { dialog, _ ->
                finishAffinity()
            }
            .setNeutralButton("Try again") { _, _ ->
                if (!isNetworkAvailable()) {
                    showNoInternetDialog()
                } else {
                    request()
                }
            }
            .setCancelable(false)
            .show()
    }

    private fun request(){
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

        val wantedApi = retrofit.create(ApiService::class.java)
        lifecycleScope.launch {
            val list = wantedApi.getWanted()
            wantedList.clear()
            wantedList.addAll(list.data)
            adapterr.clearItems()
            adapterr.addItems(wantedList)
        }
    }
}
