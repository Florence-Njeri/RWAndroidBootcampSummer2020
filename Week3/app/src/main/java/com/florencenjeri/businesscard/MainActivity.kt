package com.florencenjeri.businesscard

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
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
            val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate)
            it.startAnimation(rotateAnimation)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.share) {
            shareWithFriends()
        } else if (item.itemId == R.id.about) {
            showInfo()
        }
        return true
    }

    private fun showInfo() {
        val dialogTitle = getString(R.string.aboutTitle)
        val dialogMessage = getString(R.string.aboutMessage)
        val builder = AlertDialog.Builder(this)
        builder.setTitle(dialogTitle)
        builder.setMessage(dialogMessage)
        builder.create().show()
    }

    private fun shareWithFriends() {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.stringExtra))
        shareIntent.type = "text/plain"
        startActivity(shareIntent)

    }
}