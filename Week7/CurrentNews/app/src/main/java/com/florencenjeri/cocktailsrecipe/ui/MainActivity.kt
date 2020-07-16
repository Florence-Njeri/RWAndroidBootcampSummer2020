package com.florencenjeri.cocktailsrecipe.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.florencenjeri.cocktailsrecipe.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

}