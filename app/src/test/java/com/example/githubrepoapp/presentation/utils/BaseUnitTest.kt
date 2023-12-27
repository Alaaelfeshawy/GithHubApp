package com.example.githubrepoapp.presentation.utils

import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

open class BaseUnitTest {

    val testDispatcher = StandardTestDispatcher()

    val errorMessage = "something wrong happened"

    val networkError = "connection error"
    @Before
    open fun setUp(){
        Dispatchers.setMain(testDispatcher)

        MockKAnnotations.init(this)
    }

    @After
    open fun cleanUp(){
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }
}