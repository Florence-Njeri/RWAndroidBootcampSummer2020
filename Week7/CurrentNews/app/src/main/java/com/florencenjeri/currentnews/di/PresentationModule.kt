package com.florencenjeri.currentnews.di

import com.florencenjeri.currentnews.prefs.UserPrefs
import com.florencenjeri.currentnews.ui.viewmodel.NewsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presentationModule = module {
    viewModel { NewsViewModel(get()) }
    /**
     * I only need UserPrefs to be created if the user is logged out else, don't instantiate it as we don't need it
     */
    scope(named("UserPrefs")) {
        scoped {
            UserPrefs()
        }
    }
}