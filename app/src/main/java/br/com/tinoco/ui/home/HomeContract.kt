package br.com.tinoco.ui.home

import br.com.tinoco.util.mvp.BasePresenter
import br.com.tinoco.util.mvp.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun showMessage(message: String)
        fun showLoading(active: Boolean)
        fun showSuccess(message: String, list: List<String>)
        fun newCategory(category: String)
        fun getCategory(): List<String>
    }


    interface Presenter : BasePresenter<View> {
        fun loadFeed(category: String)
        fun addCategory(category: String)
    }

}
