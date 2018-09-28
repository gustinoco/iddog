package br.com.tinoco.ui.login

import br.com.tinoco.util.mvp.BasePresenter
import br.com.tinoco.util.mvp.BaseView

interface LoginContract {

    interface View : BaseView<Presenter> {
        fun showMessage(message: String)
        fun showLoading(active: Boolean)
        fun showErrorEmail(valid: Boolean)
        fun showSuccess(message: String)
    }

    interface Presenter : BasePresenter<View> {
        fun signup()
        fun emailChanged(text: String)
    }

}
