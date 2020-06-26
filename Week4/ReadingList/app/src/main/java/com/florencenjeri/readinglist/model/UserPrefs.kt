package com.florencenjeri.readinglist.model

import android.preference.PreferenceManager
import com.florencenjeri.readinglist.ReadingListApplication

object UserPrefs {
    private const val EMAIL = "email"
    private const val PASSWORD = "password"

    private fun sharedPrefs() =
        PreferenceManager.getDefaultSharedPreferences(ReadingListApplication.getAppContext())

    fun logInUser(email: String, password: String) {
        val editor = sharedPrefs().edit()
        editor.putString(EMAIL, email)
        editor.putString(PASSWORD, password)
    }

    fun isUserLoggedIn(): Boolean {
        val email = sharedPrefs().getString(EMAIL, "flonjeri@mail.com")
        val password = sharedPrefs().getString(PASSWORD, "flonjeri")
        return email.isNullOrBlank() || password.isNullOrBlank()
    }
}