package com.example.archpatternandroid.networking

import com.example.archpatternandroid.entity.ResponseLogin
import com.example.archpatternandroid.entity.ResponseRegister
import okhttp3.Call
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Field
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST("get-pengguna-add.php")
    fun regis(
        @Part("nama") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("level") level: RequestBody,
        @Part file: MultipartBody.Part
    ): retrofit2.Call<ResponseRegister>


    @Multipart
    @POST("get-pengguna-login.php")
    fun login(
        @Field("nama") name: String,
        @Field("passowrd") password: String
    ): retrofit2.Call<ResponseLogin>

}