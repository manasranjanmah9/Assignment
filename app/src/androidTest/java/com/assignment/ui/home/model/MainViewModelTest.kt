package com.assignment.ui.home.model

import android.content.Context
import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.assignment.data.db.AppDatabase
import com.assignment.network.NetworkRepository
import junit.framework.TestCase
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
public class MainViewModelTest /*: TestCase()*/ {

/*    public override fun setUp() {
        super.setUp()
    }

    public override fun tearDown() {}*/


    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel
    private lateinit var networkRepository: NetworkRepository


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        context = InstrumentationRegistry.getTargetContext()

        val appDatabase = AppDatabase.getDbInstance(context)
        val networkRepository = NetworkRepository(appDatabase)
        val factory = MainViewModelFactory(networkRepository)
        viewModel = MainViewModel(networkRepository)

    }

    /* private fun createViewModel(
         networkRepository: NetworkRepository<> =
     ) = MainViewModel(

     )*/

//    @Test
//    fun `insert news item after getting from server`() {
//
//    }
}