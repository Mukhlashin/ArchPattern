package com.example.archpatternandroid.login

import android.util.Log
import com.example.archpatternandroid.base.BasePresenter
import com.example.archpatternandroid.entity.ResponseLogin
import com.example.archpatternandroid.repository.LoginRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val repo: LoginRepositoryImpl, var loginView: LoginContract.View? = null) : BasePresenter<LoginContract.View>, LoginContract.Presenter {

    override fun onAttach(view: LoginContract.View) {
        loginView = view
    }

    override fun onDettach() {
        loginView = null
    }

    override fun login(name: String, password:String) {
        repo.login(name, password)
            .enqueue(object :Callback<ResponseLogin> {
                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    Log.d("TAG", t.toString())
                }

                override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {

                    if (response.isSuccessful && response != null) {
                        val isSuccess = response.body()?.isIsSuccess
                        val message = response.body()?.msg
                        val data = response.body()?.data

                        if (isSuccess == true) {
                            Log.d("TAG", data.toString())
                        } else {
                            Log.d("TAG", message.toString())
                        }
                    } else {
                        Log.d("TAG", response.errorBody().toString())
                    }
                }
            })
    }
}