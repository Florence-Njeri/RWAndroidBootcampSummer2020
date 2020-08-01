package com.florencenjeri.currentnews

import com.florencenjeri.currentnews.di.networkModule
import com.florencenjeri.currentnews.di.newsModule
import org.junit.After
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class KoinModulesTest : KoinTest {
    @Test
    fun `test koin modules`() {
        startKoin {

            modules(listOf(newsModule, networkModule))
        }.checkModules()
    }

    @After
    fun `Stop Kon`() {
        stopKoin()
    }
}