package com.example.archpatternandroid.register

import android.util.Log
import com.example.archpatternandroid.base.BasePresenter
import com.example.archpatternandroid.entity.ResponseRegister
import com.example.archpatternandroid.repository.RegisterRepositoryImpl
import com.example.archpatternandroid.utils.createMultipartBody
import com.example.archpatternandroid.utils.createPartFromString
import okhttp3.Callback
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

class RegisterPresenter(val repo: RegisterRepositoryImpl, var regisView: RegisterContract.View? = null) :
    BasePresenter<RegisterContract.View>, RegisterContract.Presenter {

    override fun onAttach(view: RegisterContract.View) {
        regisView = view
    }

    override fun onDettach() {
        regisView = null
    }

    override fun regis(name: String, email: String, password: String, level: String, file: String) {

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {

            // SAMPAIKAN DATA KOSONG KE VIEW
            regisView?.isEmpty()

        } else {

            // KITA REQUEST KE SERVER
            val reqName: RequestBody = createPartFromString(name)
            val reqEmail: RequestBody = createPartFromString(email)
            val reqPass: RequestBody = createPartFromString(password)
            val reqLevel: RequestBody = createPartFromString(level)
            repo.regis(
                reqName,
                reqEmail,
                reqPass,
                reqLevel,
                createMultipartBody(file)
            ).enqueue(object : retrofit2.Callback<ResponseRegister> {

                override fun onResponse(call: Call<ResponseRegister>, response: Response<ResponseRegister>) {

                    if (response.isSuccessful && response != null) {
                        val isSuccess = response.body()?.isIsSuccess
                        val message = response.body()?.msg

                        if (isSuccess == true) {
                            Log.d("TAG", message)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {

                }
            })
        }
    }

}