package com.example.archpatternandroid.repository

import com.example.archpatternandroid.networking.ApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ProfileRepositoryImpl(override val service: ApiService) :RegisterRepository {

        override fun regis(
            name: RequestBody,
            email: RequestBody,
            password: RequestBody,
            level: RequestBody,
            images: MultipartBody.Part
        ) = service.regis(name, email, password, level, images)

        override fun login(name: String, password: String) = service.login(name, password)

}