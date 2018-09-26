package br.com.tinoco.models.response

import br.com.tinoco.util.JSONConvertable
import br.com.tinoco.models.User
import com.google.gson.annotations.SerializedName

class LoginResponse(
        @SerializedName("user") val response: User
) : JSONConvertable