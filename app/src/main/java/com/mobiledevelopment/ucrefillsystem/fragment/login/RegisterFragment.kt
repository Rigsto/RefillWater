package com.mobiledevelopment.ucrefillsystem.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.helper.invisible
import com.mobiledevelopment.ucrefillsystem.helper.visible
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.presenter.LoginPresenter
import com.mobiledevelopment.ucrefillsystem.presenter.PostPresenter
import com.mobiledevelopment.ucrefillsystem.viewinterface.PostInterface
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.pb_login

class RegisterFragment : Fragment(), View.OnClickListener, PostInterface {
    override fun hideLoading() {
        pb_login.invisible()
        btn_sign_up.visible()
    }

    override fun showLoading() {
        pb_login.visible()
        btn_sign_up.invisible()
    }

    private lateinit var presenter: PostPresenter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = PostPresenter(this)

        btn_sign_up.setOnClickListener(this)
        tv_register_to_login.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_register_to_login -> {
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.fl_container_login, LoginFragment())?.commit()
            }

            R.id.btn_sign_up->{
                if (edt_register_password.text.toString() != edt_register_password2.text.toString()){
                    Toast.makeText(context, "Password Mismatch", Toast.LENGTH_LONG )
                } else
                presenter.RegisterUser(edt_register_email.text.toString(), edt_register_password.text.toString())
            }
        }
    }
}
