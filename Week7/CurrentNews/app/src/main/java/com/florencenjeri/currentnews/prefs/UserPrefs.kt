package com.florencenjeri.currentnews.prefs

import android.content.Context
import com.florencenjeri.currentnews.App

class UserPrefs : SharedPrefsHelper {
    companion object {
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
        private const val NEWS_SHARED_PREFS = ""
    }

    private fun sharedPrefs() =
        App.getAppContext().getSharedPreferences(NEWS_SHARED_PREFS, Context.MODE_PRIVATE)

    override fun logInUser(email: String, password: String) {
        val editor = sharedPrefs().edit()
        editor.putString(EMAIL, email)
        editor.putString(PASSWORD, password)
        editor.apply()
    }

    override fun isUserLoggedIn(): Boolean {
        var isLoggedIn = false
        val email = sharedPrefs().getString(EMAIL, "")
        val password = sharedPrefs().getString(PASSWORD, "")
        isLoggedIn = !email.isNullOrBlank() && !password.isNullOrBlank()
        return isLoggedIn
    }
}