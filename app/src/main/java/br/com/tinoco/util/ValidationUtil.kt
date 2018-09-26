package br.com.tinoco.util

import android.util.Patterns

object ValidationUtil {
    fun isValidEmail(email: String): Boolean = email.isNotEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(email).matches()

}
