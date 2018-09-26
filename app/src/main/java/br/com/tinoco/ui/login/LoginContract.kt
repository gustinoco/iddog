package br.com.tinoco.ui.login

import br.com.tinoco.util.BasePresenter
import br.com.tinoco.util.BaseView

interface LoginContract {

    interface View : BaseView<Presenter> {
        fun showMessage(message: String)
        fun showLoading(active: Boolean)
        fun showErrorEmail(valid: Boolean)
        fun showSuccess(message: String)
    }

    interface Presenter : BasePresenter {
        fun signup()
        fun emailChanged(text: String)
    }

}
