package com.example.archpatternandroid.listuser


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.archpatternandroid.R
import com.example.archpatternandroid.networking.Injection
import com.example.archpatternandroid.repository.LoginRepositoryImpl
import com.example.archpatternandroid.repository.UserRepositoryImpl


/**
 * A simple [Fragment] subclass.
 *
 */
class UserFragment : Fragment(), UserContract.View {

    lateinit var presenter: UserPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPresenter()
    }

    private fun initPresenter() {
        val service = Injection.provideApiService()
        val repository = UserRepositoryImpl(service)
        presenter = UserPresenter(repository)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onAttachView() {
        presenter.onAttach(this)

    }

    override fun onDettachView() {
        presenter.onDettach()
    }
}
