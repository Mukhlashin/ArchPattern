package com.example.archpatternandroid.listuser

import android.util.Log
import com.example.archpatternandroid.base.BasePresenter
import com.example.archpatternandroid.entity.ResponseUser
import com.example.archpatternandroid.repository.UserRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserPresenter(val repo:UserRepositoryImpl,var userView: UserContract.View? = null)
    : BasePresenter<UserContract.View>, UserContract.Presenter {
    override fun onAttach(view: UserContract.View) {
        userView = view
    }

    override fun onDettach() {
        userView = null
    }

    override fun getListUser() {
        repo.getListUser().enqueue(object : Callback<ResponseUser> {

            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful) {
                    val isSuccess = response.body()?.isSuccess
                    val dataUser = response.body()?.data

                    if (isSuccess == true) {
                        Log.d("TAG", dataUser.toString())
                    } else {
                        Log.d("TAG", isSuccess.toString())
                    }
                } else {
                    Log.d("TAG", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })

    }
}
