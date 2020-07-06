package com.florencenjeri.readinglist.prefs

interface SharedPrefsHelper {

    fun logInUser(email: String, password: String)


    fun isUserLoggedIn(): Boolean


    fun logOut()

}