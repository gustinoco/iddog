package br.com.tinoco.ui.login

import br.com.tinoco.api.UserApiClient
import br.com.tinoco.models.request.LoginRequest
import br.com.tinoco.models.response.LoginResponse
import br.com.tinoco.util.Constants
import br.com.tinoco.util.ErrorUtils
import br.com.tinoco.util.mvp.RxPresenter
import br.com.tinoco.util.ValidationUtil
import br.com.tinoco.util.rx.SchedulerProvider
import io.paperdb.Paper

class LoginPresenter(private val api: UserApiClient, private val schedulerProvider: SchedulerProvider) : RxPresenter<LoginContract.View>(), LoginContract.Presenter {

    override var view: LoginContract.View? = null

    var email: String? = ""

    override fun start() {
        email = ""
        view?.showLoading(false)
    }

    init {
        Paper.book().delete(Constants.BD)
    }

    override fun signup() {
        val validation = ValidationUtil.isValidEmail(email.toString())

        view?.showErrorEmail(validation)
        if (!validation || email!!.isEmpty())
            return

        view?.showLoading(true)
        launch {
            api.login(LoginRequest(email!!)).subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui()).subscribe({ loginResponse: LoginResponse ->
                        view?.showLoading(false)
                        advanceScreen(loginResponse.response.token)
                    },
                            { error ->
                                view?.showMessage(ErrorUtils.parseError(error))
                                view?.showLoading(false)

                            })
        }
    }

    fun advanceScreen(token: String) {
        Paper.book().write(Constants.BD, token)
        view?.showSuccess(token)
    }

    override fun emailChanged(text: String) {
        email = text
    }
}