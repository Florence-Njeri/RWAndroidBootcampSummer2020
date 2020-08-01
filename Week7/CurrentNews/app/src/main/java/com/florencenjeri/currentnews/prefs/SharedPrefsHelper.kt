package com.florencenjeri.currentnews.prefs

interface SharedPrefsHelper {
    fun logInUser(email: String, password: String)

    fun isUserLoggedIn(): Boolean
}