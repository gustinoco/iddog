package br.com.tinoco.util

import br.com.tinoco.models.response.APIError
import com.crashlytics.android.Crashlytics
import com.google.gson.Gson
import retrofit2.HttpException
import java.lang.Exception

object ErrorUtils {

    fun parseError(response: Throwable): String {
        var erro = "Erro desconhecido, por favor contacte o suporte."
        try {
            if (response is HttpException) {
                val json = response.response().errorBody()!!.string()
                val person1 = Gson().fromJson(json, APIError::class.java)
                erro = person1.error.message
            }
        } catch (e: Exception) {
            Crashlytics.logException(e)
        }

        return erro
    }
}
