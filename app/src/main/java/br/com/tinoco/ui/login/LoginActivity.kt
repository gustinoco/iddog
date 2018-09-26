package br.com.tinoco.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.tinoco.R
import br.com.tinoco.ui.home.HomeActivity
import br.com.tinoco.util.showSnack
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    override lateinit var presenter: LoginContract.Presenter
    lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        view = findViewById(android.R.id.content)
        presenter = LoginPresenter(this)
        initComponentsConfigurations()
    }

    fun initComponentsConfigurations() {
        edtEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.emailChanged(p0.toString())
            }
        })

        btnSignup.setOnClickListener { presenter.signup() }
    }

    override fun showErrorEmail(valid: Boolean) {
        if (valid)
            edtEmail.error = null
        else
            edtEmail.error = getString(R.string.invalid_email)
    }

    override fun showSuccess(message: String) {
        val intent = Intent(applicationContext, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun showLoading(active: Boolean) {
        if (active)
            loading.visibility = View.VISIBLE
        else
            loading.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        view.showSnack(message)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }
}