package com.example.archpatternandroid.repository

import android.app.Service
import com.example.archpatternandroid.entity.ResponseLogin
import com.example.archpatternandroid.networking.ApiService
import retrofit2.Call

class LoginRepositoryImpl(val service: ApiService) : LoginRepository {
    override fun login(name: String, password: String) = service .login(name, password)
}