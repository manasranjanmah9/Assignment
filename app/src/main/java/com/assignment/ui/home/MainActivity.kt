package com.assignment.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.R
import com.assignment.databinding.ActivityMainBinding
import com.assignment.network.NetworkRepository
import com.assignment.ui.home.adapter.AboutListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val networkRepository = NetworkRepository()
        val factory = MainViewModelFactory(networkRepository)

        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        mainActivityBinding.mainViewModel = mainViewModel

        //set layoutManager to recyclerView
        mainActivityBinding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainActivityBinding.recyclerView.setHasFixedSize(true)


        loadData()


    }

    private fun loadData() {
        mainViewModel.getData().observe(this, Observer {
            if (it != null) {
                mainActivityBinding.progressBar.visibility = View.GONE
                setActionbarTitle(it.title)
                val adapter = AboutListAdapter(this, it.aboutList)
                mainActivityBinding.recyclerView.adapter = adapter
                mainActivityBinding.swipeRefreshLayout.isRefreshing = false
            }
        })

        mainActivityBinding.swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }
    }

    private fun setActionbarTitle(text: String?) {
        title = text
    }
}