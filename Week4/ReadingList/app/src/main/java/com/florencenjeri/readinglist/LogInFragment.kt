package com.florencenjeri.readinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()
        UserPrefs.logInUser(email, password)
    }
}