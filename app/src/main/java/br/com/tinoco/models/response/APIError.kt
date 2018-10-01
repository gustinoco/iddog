package br.com.tinoco.models.response

import br.com.tinoco.models.ErrorAPI
import br.com.tinoco.util.JSONConvertable
import com.google.gson.annotations.SerializedName

class APIError(
        @SerializedName("error") val error: ErrorAPI
) : JSONConvertable