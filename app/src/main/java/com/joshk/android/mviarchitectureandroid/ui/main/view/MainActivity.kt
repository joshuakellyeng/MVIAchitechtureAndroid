package com.joshk.android.mviarchitectureandroid.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.joshk.android.mviarchitectureandroid.R
import com.joshk.android.mviarchitectureandroid.data.api.ApiHelperImpl
import com.joshk.android.mviarchitectureandroid.data.api.RetrofitBuilder
import com.joshk.android.mviarchitectureandroid.data.model.User
import com.joshk.android.mviarchitectureandroid.databinding.ActivityMainBinding
import com.joshk.android.mviarchitectureandroid.ui.main.adapter.MainAdapter
import com.joshk.android.mviarchitectureandroid.ui.main.intent.MainIntent
import com.joshk.android.mviarchitectureandroid.ui.main.viewmodel.MainViewModel
import com.joshk.android.mviarchitectureandroid.ui.main.viewstate.MainState
import com.joshk.android.mviarchitectureandroid.util.ViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var adapter = MainAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()
    }

    private fun setupClicks() {
        binding.buttonFetchUser.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }
        }
    }

    private fun setupUI() {
        val recyclerView = binding.recyclerView
       recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        recyclerView.adapter
    }
    
    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.apiService
                )
            )
        ).get(MainViewModel::class.java)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {
                        binding.buttonFetchUser.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                    }
                    is MainState.Users -> {
                        binding.progressBar.visibility = View.GONE
                        binding.buttonFetchUser.visibility = View.GONE
                        renderList(it.user)
                    }
                    is MainState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.buttonFetchUser.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.error,Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun renderList(users: List<User>) {
        binding.recyclerView.visibility = View.VISIBLE
        users.let {listOfUsers -> listOfUsers.let { adapter.addData(it) }}
        adapter.notifyDataSetChanged()
    }
}