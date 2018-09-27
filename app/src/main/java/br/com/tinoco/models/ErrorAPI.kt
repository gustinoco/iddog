package br.com.tinoco.models

import br.com.tinoco.util.JSONConvertable
import com.google.gson.annotations.SerializedName

class ErrorAPI(
        @SerializedName("message") val message: String
) : JSONConvertable