package br.com.tinoco.ui.home

import br.com.tinoco.api.UserApiClient
import br.com.tinoco.models.response.CategoryResponse
import br.com.tinoco.util.Constants
import br.com.tinoco.util.ErrorUtils
import br.com.tinoco.util.mvp.RxPresenter
import br.com.tinoco.util.rx.SchedulerProvider
import io.paperdb.Paper

class HomePresenter(val api: UserApiClient, private val schedulerProvider: SchedulerProvider) : RxPresenter<HomeContract.View>(), HomeContract.Presenter {

    override var view: HomeContract.View? = null

    override fun start() {
        view?.showLoading(false)
        fillDrawerMenu()
    }

    fun fillDrawerMenu() {
        view?.getCategory()?.forEach {
            view?.newCategory(it)
        }
    }

    override fun addCategory(category: String) {
        view?.newCategory(category)
    }

    override fun loadFeed(category: String) {

        view?.showLoading(true)
        launch {
            api.dogs(category.toLowerCase(), Paper.book().read(Constants.BD)).subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui()).subscribe({ response: CategoryResponse ->
                        view?.showLoading(false)
                        showResult(response)
                    },
                            { error ->
                                view?.showLoading(false)
                                view?.showMessage(ErrorUtils.parseError(error))
                            })

        }
    }

    fun showResult(response: CategoryResponse) {
        view?.showSuccess(response.category, response.list)
    }
}