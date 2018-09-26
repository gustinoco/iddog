package br.com.tinoco.api

import br.com.tinoco.models.request.LoginRequest
import br.com.tinoco.models.response.LoginResponse
import br.com.tinoco.util.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiClient {

    @POST("signup")
    fun login(@Body request: LoginRequest): Observable<LoginResponse>

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