package br.com.tinoco.ui.login

import br.com.tinoco.api.UserApiClient
import br.com.tinoco.models.request.LoginRequest
import br.com.tinoco.models.response.LoginResponse
import br.com.tinoco.util.Constants
import br.com.tinoco.util.ErrorUtils
import br.com.tinoco.util.ValidationUtil
import io.paperdb.Paper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginPresenter(val loginView: LoginContract.View) : LoginContract.Presenter {

    private val api: UserApiClient = UserApiClient.create()
    private val subscriptions = CompositeDisposable()
   var email: String? = ""

    override fun start() {
        email = ""
        loginView.showLoading(false)
    }

    init {
        loginView.presenter = this
        Paper.book().delete(Constants.BD)
    }

    override fun signup() {
        val validation = ValidationUtil.isValidEmail(email.toString())

        loginView.showErrorEmail(validation)
        if (!validation || email!!.isEmpty())
            return

        loginView.showLoading(true)
        var subscribe = api.login(LoginRequest(email!!)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ loginResponse: LoginResponse ->
                    loginView.showLoading(false)
                    advanceScreen(loginResponse.response.token)
                },
                        { error ->
                            loginView.showMessage(ErrorUtils.parseError(error))
                            loginView.showLoading(false)

                        })
        subscriptions.add(subscribe)
    }

    fun advanceScreen(token: String) {
        Paper.book().write(Constants.BD, token)
        loginView.showSuccess(token)
    }

    override fun emailChanged(text: String) {
        email = text
    }
}