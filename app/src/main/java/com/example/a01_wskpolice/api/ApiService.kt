package com.example.a01_wskpolice.api

import com.example.a01_wskpolice.data.Department
import com.example.a01_wskpolice.data.Departments
import com.example.a01_wskpolice.data.Wanteds
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(" /api/department/")
    suspend fun getDepartments() : Departments
    @GET(" /api/wanted/")
    suspend fun getWanted() : Wanteds
}