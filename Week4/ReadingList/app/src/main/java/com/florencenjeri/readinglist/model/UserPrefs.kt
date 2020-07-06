package com.florencenjeri.readinglist.model

import android.preference.PreferenceManager
import com.florencenjeri.readinglist.ReadingListApplication

class UserPrefs {

    private fun sharedPrefs() =
        PreferenceManager.getDefaultSharedPreferences(ReadingListApplication.getAppContext())

    fun logInUser(email: String, password: String) {
        val editor = sharedPrefs().edit()
        editor.putString(EMAIL, email)
        editor.putString(PASSWORD, password)
        editor.apply()
    }

    fun isUserLoggedIn(): Boolean {
        var isLoggedIn = false
        val email = sharedPrefs().getString(EMAIL, "")
        val password = sharedPrefs().getString(PASSWORD, "")
        isLoggedIn = !email.isNullOrBlank() && !password.isNullOrBlank()
        return isLoggedIn
    }

    fun logOut() {
        sharedPrefs().edit().clear().apply()
    }

    companion object {
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }


}