package com.example.archpatternandroid.networking

import com.example.archpatternandroid.entity.ResponseLogin
import com.example.archpatternandroid.entity.ResponseRegister
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

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


    @FormUrlEncoded
    @POST("get-pengguna-login.php")
    fun login(
        @Field("nama") name: String,
        @Field("password") password: String
    ): retrofit2.Call<ResponseLogin>

    @GET("get-pengguna-view.php")
    fun getListUser() : retrofit2.Call<ResponseUser>

}