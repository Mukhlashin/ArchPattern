package com.example.archpatternandroid.repository

import com.example.archpatternandroid.entity.ResponseUser
import com.example.archpatternandroid.networking.ApiService
import retrofit2.Call

class UserRepositoryImpl(val service: ApiService) : UserRepository {
    override fun getListUser() = service.getListUser()
}