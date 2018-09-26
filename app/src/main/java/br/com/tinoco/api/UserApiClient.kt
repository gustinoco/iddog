package br.com.tinoco.api

import br.com.tinoco.util.Constants
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface UserApiClient {

    companion object Factory {
        fun create(): UserApiClient {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build()
            return retrofit.create(UserApiClient::class.java)
        }
    }
}