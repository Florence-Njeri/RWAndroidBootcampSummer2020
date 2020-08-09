package com.florencenjeri.currentnews

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.florencenjeri.currentnews.di.*
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class KoinModulesTest : KoinTest {
    val appContext = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun `test koin modules`() {
        startKoin {
            //For logging Koin related errors
            androidLogger()
            //Declare my app context
            androidContext(appContext)
            modules(listOf(
                appModule, networkModule, databaseModule, presentationModule,
                repositoryModule
            ))
        }.checkModules()
    }

    @After
    fun `Stop Kon`() {
        stopKoin()
    }
}