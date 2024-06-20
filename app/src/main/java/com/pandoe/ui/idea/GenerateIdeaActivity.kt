package com.pandoe.ui.idea

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandoe.databinding.ActivityGenerateIdeaBinding
import com.pandoe.ui.main.MainActivity

class GenerateIdeaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGenerateIdeaBinding
    private lateinit var ideaViewModel: IdeaViewModel
    private lateinit var ideaAdapter: IdeaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenerateIdeaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupViews()
        setupRecyclerView()

        binding.saveButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupViewModel() {
        val factory = IdeaViewModelFactory.getInstance(this)
        ideaViewModel = ViewModelProvider(this, factory)[IdeaViewModel::class.java]
    }

    private fun setupViews() {
        ideaAdapter = IdeaAdapter { selectedIdea ->
            println("Selected idea: $selectedIdea")
        }

        setupRecyclerView()

        binding.rvIdea.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = ideaAdapter
        }
    }

    private fun setupRecyclerView() {
        ideaViewModel.generateIdeas().observe(this) {
            showLoading(true)
            ideaAdapter.submitList(it.data)
            showLoading(false)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}