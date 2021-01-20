package com.assignment.ui.home.model

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.assignment.data.db.AppDatabase
import com.assignment.network.NetworkRepository
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel


    @Before
    fun setup() {

        context = ApplicationProvider.getApplicationContext()

        val appDatabase = AppDatabase.getDbInstance(context)
        val networkRepository = NetworkRepository(appDatabase)
        val factory = MainViewModelFactory(networkRepository)
        viewModel = MainViewModel(networkRepository)

    }

    @Test
    fun `insert news item after getting from server`() {

    }
}