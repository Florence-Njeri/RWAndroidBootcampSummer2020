package com.florencenjeri.readinglist

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.florencenjeri.readinglist.model.UserPrefs
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
        if (UserPrefs.isUserLoggedIn()) {
            findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToBooksFragment())
        }
        buttonLogIn.setOnClickListener {
            if (validateData()) {
                UserPrefs.logInUser(editTextEmail.text.toString(), editTextPassword.text.toString())
                findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToBooksFragment())
            }

        }
    }

    private fun validateData(): Boolean {
        var isValid = true
        if (TextUtils.isEmpty(editTextEmail.text.toString())) {
            isValid = false
            editTextEmail.requestFocus()
            editTextEmail.error = "Email required!"
        }
        if (TextUtils.isEmpty(editTextPassword.text.toString())) {
            isValid = false
            editTextPassword.requestFocus()
            editTextPassword.error = "Password Required!"
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(editTextEmail.text.toString().trim())
                .matches()
        ) {
            isValid = false
            editTextEmail.requestFocus()
            editTextEmail.error = "Enter a valid email!"
        }

        if (editTextPassword.text.toString().length < 4) {
            isValid = false
            editTextPassword.requestFocus()
            editTextPassword.error = "Weak password. Password should contain at least 4 characters!"
        }

        return isValid

    }
}