package br.com.tinoco.di

import br.com.tinoco.ui.home.HomeContract
import br.com.tinoco.ui.home.HomePresenter
import br.com.tinoco.ui.login.LoginContract
import br.com.tinoco.ui.login.LoginPresenter
import br.com.tinoco.util.rx.ApplicationSchedulerProvider
import br.com.tinoco.util.rx.SchedulerProvider
import org.koin.dsl.module.module

val appModule = module {

    // Presenter for View
    factory<LoginContract.Presenter> { LoginPresenter(get(), get()) }
    factory<HomeContract.Presenter> { HomePresenter(get(), get()) }

    // Rx Schedulers
    single<SchedulerProvider> { ApplicationSchedulerProvider() }

}