package br.com.tinoco.models.request

import br.com.tinoco.util.JSONConvertable
import com.google.gson.annotations.SerializedName

data class LoginRequest(
        @SerializedName("email") val email: String
) : JSONConvertable