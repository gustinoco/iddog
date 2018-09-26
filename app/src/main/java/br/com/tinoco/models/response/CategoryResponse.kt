package br.com.tinoco.models.response

import br.com.tinoco.util.JSONConvertable
import com.google.gson.annotations.SerializedName

class CategoryResponse(
        @SerializedName("category") val category: String,
        @SerializedName("list") val list: List<String>
) : JSONConvertable