package br.com.tinoco.models

import br.com.tinoco.util.JSONConvertable
import com.google.gson.annotations.SerializedName
import java.util.*

class User(
        @SerializedName("_id") val id: String,
        @SerializedName("token") val token: String,
        @SerializedName("createdAt") val createdAt: Date,
        @SerializedName("updatedAt") val updatedAt: Date,
        @SerializedName("email") val email: String
) : JSONConvertable