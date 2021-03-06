package com.florencenjeri.currentnews.ui.view

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.florencenjeri.currentnews.R
import com.florencenjeri.currentnews.prefs.UserPrefs
import kotlinx.android.synthetic.main.fragment_log_in.*
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent.getKoin

class LogInFragment : Fragment() {
    //Create a scope instance of the Shared Prefs
    private val scope: Scope = getKoin().createScope("prefs", named("UserPrefs"))
    private val prefs: UserPrefs = scope.get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(false)
        //Check if the user is logged in
        isUserLoggedIn()
        logInUser()
    }

    private fun logInUser() {
        buttonLogIn.setOnClickListener {
            validateData()

            prefs.logInUser(
                editTextEmail.text.toString(),
                editTextPassword.text.toString()
            )
            findNavController().navigate(R.id.action_logInFragment_to_FirstFragment)
            //Now that the user is logged in, we no longer need the SharedPrefs object so we close its scope
            scope.close()
        }
    }

    private fun isUserLoggedIn() {
        if (prefs.isUserLoggedIn()) {
            findNavController().navigate(R.id.action_logInFragment_to_FirstFragment)
        }
    }

    private fun validateData() {
        isValidEmail()
        isValidPassword()
    }

    private fun isValidEmail() {
        if (TextUtils.isEmpty(editTextEmail.text.toString())) {
            editTextEmail.requestFocus()
            editTextEmail.error = "Email required!"
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(editTextEmail.text.toString().trim())
                .matches()
        ) {
            editTextEmail.requestFocus()
            editTextEmail.error = "Enter a valid email!"
        }
    }

    private fun isValidPassword() {
        if (TextUtils.isEmpty(editTextPassword.text.toString())) {
            editTextPassword.requestFocus()
            editTextPassword.error = "Password Required!"
        }

        if (editTextPassword.text.toString().length < 4) {
            editTextPassword.requestFocus()
            editTextPassword.error = "Weak password. Password should contain at least 4 characters!"
        }
    }
}
