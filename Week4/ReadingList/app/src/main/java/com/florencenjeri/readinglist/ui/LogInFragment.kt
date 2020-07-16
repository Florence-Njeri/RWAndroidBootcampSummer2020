package com.florencenjeri.readinglist.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.florencenjeri.readinglist.R
import com.florencenjeri.readinglist.ReadingListApplication
import kotlinx.android.synthetic.main.fragment_log_in.*

class LogInFragment : Fragment() {

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

            ReadingListApplication.prefsHelper.logInUser(
                editTextEmail.text.toString(),
                editTextPassword.text.toString()
            )
            findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToBooksFragment())

        }
    }

    private fun isUserLoggedIn() {
        if (ReadingListApplication.prefsHelper.isUserLoggedIn()) {
            findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToBooksFragment())
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
