package com.assignment.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.R
import com.assignment.data.About
import com.assignment.data.db.AppDatabase
import com.assignment.databinding.ActivityMainBinding
import com.assignment.network.NetworkRepository
import com.assignment.ui.home.adapter.AboutListAdapter
import com.assignment.ui.home.model.MainViewModel
import com.assignment.ui.home.model.MainViewModelFactory
import com.assignment.util.CommonUtils

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: AboutListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val appDatabase = AppDatabase.getDbInstance(this)
        val networkRepository = NetworkRepository(appDatabase)
        val factory = MainViewModelFactory(networkRepository)

        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        mainActivityBinding.mainViewModel = mainViewModel

        mainActivityBinding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainActivityBinding.recyclerView.setHasFixedSize(true)

        loadData()
    }

    private fun loadData() {
        if (CommonUtils.isNetworkAvailable(this)) {
            mainViewModel.getData().observe(this, Observer {
                mainActivityBinding.progressBar.visibility = View.GONE
                it?.let {
                    setActionbarTitle(it.title)
                    setAdapter(it.aboutList)
                }
            })
        } else {
            mainViewModel.getAboutListDataFromRoomDb().observe(this, Observer {
                mainActivityBinding.progressBar.visibility = View.GONE
                it?.let {
                    setAdapter(it)
                }
            })
        }

        mainActivityBinding.swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }
    }

    private fun setAdapter(aboutList: List<About>) {
        adapter = AboutListAdapter(aboutList)
        mainActivityBinding.recyclerView.adapter = adapter
        mainActivityBinding.swipeRefreshLayout.isRefreshing = false
    }

    private fun setActionbarTitle(text: String?) {
        title = text
    }
}