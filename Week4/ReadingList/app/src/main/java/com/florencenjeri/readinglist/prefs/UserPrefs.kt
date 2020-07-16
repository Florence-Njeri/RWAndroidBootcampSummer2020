package com.florencenjeri.readinglist.prefs

import android.preference.PreferenceManager
import com.florencenjeri.readinglist.ReadingListApplication

class UserPrefs : SharedPrefsHelper {
    companion object {
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }

    private fun sharedPrefs() =
        PreferenceManager.getDefaultSharedPreferences(ReadingListApplication.getAppContext())

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

    override fun logOut() {
        sharedPrefs().edit().clear().apply()
    }

}