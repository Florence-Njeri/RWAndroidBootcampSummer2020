package com.florencenjeri.businesscard

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.raywenderlich.airlock.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    ;
    lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefs = this.getSharedPreferences(Constants.PREFS_MODE, Context.MODE_PRIVATE)
        when (prefs.getInt(Constants.MODE_KEY, 0)) {
            Mode.LIGHT.ordinal -> switchToMode(AppCompatDelegate.MODE_NIGHT_NO, Mode.LIGHT)
            Mode.DARK.ordinal -> switchToMode(AppCompatDelegate.MODE_NIGHT_YES, Mode.DARK)
            else -> switchToMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, Mode.SYSTEM)
        }

        buttonMode.setOnClickListener {

            if (buttonMode.text == getString(R.string.use_dark_mode)) {
                switchToMode(AppCompatDelegate.MODE_NIGHT_YES, Mode.DARK)
                buttonMode.text = getString(R.string.use_light_mode)


            } else if (buttonMode.text == getString(R.string.use_light_mode)) {
                switchToMode(AppCompatDelegate.MODE_NIGHT_NO, Mode.LIGHT)
                buttonMode.text = getString(R.string.use_dark_mode)

            }

        }

    }

    private fun switchToMode(nightMode: Int, mode: Mode) {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        prefs.edit().putInt(Constants.MODE_KEY, mode.ordinal).apply()
    }

}