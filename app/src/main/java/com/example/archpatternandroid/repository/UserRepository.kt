package com.example.archpatternandroid.repository

import com.example.archpatternandroid.entity.ResponseUser
import retrofit2.Call

interface UserRepository {
    fun getListUser() : Call<ResponseUser>
}