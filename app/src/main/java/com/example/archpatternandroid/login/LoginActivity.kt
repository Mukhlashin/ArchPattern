package com.example.archpatternandroid.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.archpatternandroid.R
import com.example.archpatternandroid.networking.Injection
import com.example.archpatternandroid.register.RegisterActiivty
import com.example.archpatternandroid.repository.LoginRepositoryImpl
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity(), LoginContract.View, LoginContract.Presenter {
    override fun login(name: String, password: String) {

    }

    lateinit var presenter: LoginPresenter

    var level: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initListener()
        initRb()
        initPresenter()
    }

    private fun initPresenter() {
        val service = Injection.provideApiService()
        val repository = LoginRepositoryImpl(service)
        presenter = LoginPresenter(repository)
    }

    private fun initListener() {
        rb_admin_login.setOnClickListener { level = "Admin" }
        rb_user_login.setOnClickListener { level = "User" }
        btn_login.setOnClickListener {
            presenter.login(edt_email_login.text.toString(), edt_password_login.text.toString())
        }
        tv_register.setOnClickListener { startActivity<RegisterActiivty>() }
    }

    private fun initRb(){
        level = if (rb_admin_login.isChecked) {
            "Admin"
        } else {
            "User"
        }
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettach()
    }

    override fun onStart() {
        super.onStart()
        onAttachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDettachView()
    }
}
