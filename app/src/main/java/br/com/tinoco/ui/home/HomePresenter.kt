package br.com.tinoco.ui.home

import br.com.tinoco.api.UserApiClient
import br.com.tinoco.models.response.CategoryResponse
import br.com.tinoco.util.Constants
import io.paperdb.Paper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(val homeView: HomeContract.View) : HomeContract.Presenter {

    private val api: UserApiClient = UserApiClient.create()
    private val subscriptions = CompositeDisposable()
    var continueAdd: Boolean = false
    private var index: Int = 0
    lateinit var originalResponse: CategoryResponse
    var vehicles: List<String> = ArrayList()

    override fun start() {
        homeView.showLoading(false)
        fillDrawerMenu()
    }


    fun fillDrawerMenu() {
        homeView.getCategory().forEach {
            homeView.newCategory(it)
        }
    }

    override fun addCategory(category: String) {
        homeView.newCategory(category)
    }

    init {
        homeView.presenter = this
    }

    override fun loadFeed(category: String) {

        homeView.showLoading(true)
        var subscribe = api.dogs(category.toLowerCase(), Paper.book().read(Constants.BD)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ response: CategoryResponse ->
                    homeView.showLoading(false)
                    showResult(response)

                },
                        { error ->
                            homeView.showLoading(false)
                            homeView.showMessage(error.message!!)
                        })
        subscriptions.add(subscribe)
    }

    fun showResult(response: CategoryResponse) {
        index = 0
        continueAdd = true
        originalResponse = response
        addItens()
    }


    override fun addItens() {
        if (continueAdd) {
            if (index + 10 > originalResponse.list.size) {
                continueAdd = false
                vehicles = originalResponse.list.subList(0, originalResponse.list.size)
                homeView.showSuccess(originalResponse.category, vehicles)
            } else {
                vehicles = originalResponse.list.subList(0, index + 10)
                homeView.showSuccess(originalResponse.category, vehicles)
            }
            index += 10
        }
    }
}