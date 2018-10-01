package br.com.tinoco.di

import br.com.tinoco.api.UserApiClient
import br.com.tinoco.util.Constants
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val remoteDatasourceModule = module(createOnStart = true) {
    single { createWebService<UserApiClient>(Constants.BASE_URL) }
}

inline fun <reified T> createWebService(url: String): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}