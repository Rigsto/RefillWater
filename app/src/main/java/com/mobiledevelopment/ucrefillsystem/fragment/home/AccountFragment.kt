package com.mobiledevelopment.ucrefillsystem.fragment.home


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.mobiledevelopment.ucrefillsystem.HomeActivity
import com.mobiledevelopment.ucrefillsystem.LoginActivity
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.helper.SharedPreferenceKey
import com.mobiledevelopment.ucrefillsystem.helper.reset
import com.mobiledevelopment.ucrefillsystem.helper.sharePref
import kotlinx.android.synthetic.main.fragment_account.*

/**
 * A simple [Fragment] subclass.
 */
class AccountFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cl_account_edit.setOnClickListener(this)
        rl_qr_code.setOnClickListener(this)
        rl_change_pass.setOnClickListener(this)
        rl_change_lang.setOnClickListener(this)
        btn_logout.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tv_account_name.text = context?.sharePref()?.getString(SharedPreferenceKey.NAME_KEY, "")
        tv_account_email.text = context?.sharePref()?.getString(SharedPreferenceKey.EMAIL_KEY, "")

        activity?.title = "Account"
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.cl_account_edit -> {

            }
            R.id.rl_qr_code -> {
                qrCode()
            }
            R.id.rl_change_lang -> {
                changeLang()
            }
            R.id.rl_change_pass -> {

            }
            R.id.btn_logout ->
            {
                context?.sharePref()?.reset()
                startActivity(Intent(context, LoginActivity::class.java))
                activity?.finish()
            }
        }
    }

    private fun qrCode(){

    }

    private fun changeLang(){
        val checked = context?.sharePref()?.getInt(SharedPreferenceKey.LANG_KEY, 0)!!
        val lang = arrayOf(resources.getString(R.string.english), resources.getString(R.string.indonesia))
        val pil = IntArray(1)

        val builder = AlertDialog.Builder(context!!)
        builder.setTitle(getString(R.string.language))
        builder.setSingleChoiceItems(lang, checked){ dialog, which ->
            pil[0] = which
        }
        builder.setPositiveButton(resources.getString(R.string.change)){ dialog, which ->
            setLocale(pil[0])
            context?.sharePref()?.edit()?.putInt(SharedPreferenceKey.LANG_KEY, pil[0])?.apply()
            dialog.dismiss()
//            refresh()
        }
        builder.setNegativeButton(resources.getString(R.string.cancel)){ dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun refresh(){
        startActivity(Intent(context, HomeActivity(AccountFragment())::class.java))
        activity?.finish()
    }

    private fun setLocale(x: Int){
        val lang = arrayOf("en", "in")
        val language = lang[x]
    }
}
